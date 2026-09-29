package chat.server;

import chat.common.FileLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(15)
class ChatServerTest {

    @TempDir
    Path tmp;

    private ChatServer server;
    private Path logFile;

    /** Простейший тестовый клиент. */
    private static class TestClient implements AutoCloseable {
        final Socket socket;
        final BufferedReader in;
        final PrintWriter out;

        TestClient(int port, String name) throws IOException {
            socket = new Socket("localhost", port);
            socket.setSoTimeout(5000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
            out.println(name);
        }

        void send(String text) {
            out.println(text);
        }

        /** Читает строки, пока не встретит ожидаемую. */
        void expect(String expected) throws IOException {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals(expected)) {
                    return;
                }
            }
            fail("Не получено сообщение: " + expected);
        }

        @Override
        public void close() throws IOException {
            socket.close();
        }
    }

    @BeforeEach
    void setUp() throws IOException {
        logFile = tmp.resolve("file.log");
        server = new ChatServer(0, new FileLogger(logFile));
        server.start();
    }

    @AfterEach
    void tearDown() {
        server.stop();
    }

    @Test
    void messageIsDeliveredToOtherClient() throws IOException {
        try (TestClient alice = new TestClient(server.getPort(), "alice");
             TestClient bob = new TestClient(server.getPort(), "bob")) {
            alice.expect("* bob присоединился к чату");

            alice.send("привет");

            bob.expect("alice: привет");
        }
    }

    @Test
    void messageGoesToAllOtherClients() throws IOException {
        try (TestClient a = new TestClient(server.getPort(), "a");
             TestClient b = new TestClient(server.getPort(), "b");
             TestClient c = new TestClient(server.getPort(), "c")) {
            a.expect("* c присоединился к чату");
            b.expect("* c присоединился к чату");

            c.send("hello all");

            a.expect("c: hello all");
            b.expect("c: hello all");
        }
    }

    @Test
    void exitCommandNotifiesOthers() throws IOException {
        try (TestClient alice = new TestClient(server.getPort(), "alice");
             TestClient bob = new TestClient(server.getPort(), "bob")) {
            alice.expect("* bob присоединился к чату");

            bob.send("/exit");

            alice.expect("* bob покинул чат");
        }
    }

    @Test
    void messagesAreWrittenToServerLog() throws IOException {
        try (TestClient alice = new TestClient(server.getPort(), "alice");
             TestClient bob = new TestClient(server.getPort(), "bob")) {
            alice.expect("* bob присоединился к чату");
            alice.send("logged message");
            bob.expect("alice: logged message");
        }

        String log = Files.readString(logFile, StandardCharsets.UTF_8);
        assertTrue(log.contains("alice: logged message"));
        assertTrue(log.contains("Сервер запущен"));
    }

    @Test
    void clientCanConnectLater() throws IOException {
        try (TestClient first = new TestClient(server.getPort(), "first")) {
            first.send("anyone here?");
            try (TestClient late = new TestClient(server.getPort(), "late")) {
                first.expect("* late присоединился к чату");
                first.send("welcome");
                late.expect("first: welcome");
            }
        }
    }
}
