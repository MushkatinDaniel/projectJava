package chat.client;

import chat.common.FileLogger;
import chat.server.ChatServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(15)
class ChatClientTest {

    @TempDir
    Path tmp;

    @Test
    void clientSendsMessagesAndWritesLog() throws IOException {
        ChatServer server = new ChatServer(0, new FileLogger(tmp.resolve("server.log")));
        server.start();
        try {
            Path clientLog = tmp.resolve("client.log");
            ByteArrayInputStream input = new ByteArrayInputStream(
                    "hi there\n/exit\n".getBytes(StandardCharsets.UTF_8));
            PrintStream out = new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8);

            new ChatClient("localhost", server.getPort(), "bob",
                    new FileLogger(clientLog), input, out).run();

            String log = Files.readString(clientLog, StandardCharsets.UTF_8);
            assertTrue(log.contains("bob: hi there"));
            assertTrue(log.contains("Подключен"));
            assertTrue(log.contains("Отключен"));
        } finally {
            server.stop();
        }
    }
}
