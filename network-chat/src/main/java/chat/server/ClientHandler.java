package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class ClientHandler implements Runnable {

    static final String EXIT_COMMAND = "/exit";

    private final Socket socket;
    private final ChatServer server;
    private final PrintWriter out;
    private String name;

    ClientHandler(Socket socket, ChatServer server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            String firstLine = in.readLine();
            if (firstLine == null || firstLine.isBlank()) {
                return;
            }
            name = firstLine.trim();
            server.broadcast("* " + name + " присоединился к чату", this);

            String line;
            while ((line = in.readLine()) != null) {
                if (EXIT_COMMAND.equals(line.trim())) {
                    break;
                }
                if (line.isBlank()) {
                    continue;
                }
                server.broadcast(name + ": " + line, this);
            }
        } catch (IOException ignored) {
            
        } finally {
            server.remove(this);
            close();
            if (name != null) {
                server.broadcast("* " + name + " покинул чат", this);
            }
        }
    }

    void send(String message) {
        out.println(message);
    }

    void close() {
        try {
            socket.close();
        } catch (IOException ignored) {
            // уже закрыт
        }
    }
}
