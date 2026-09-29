package chat.client;

import chat.common.FileLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ChatClient {

    public static final String EXIT_COMMAND = "/exit";

    private final String host;
    private final int port;
    private final String name;
    private final FileLogger logger;
    private final InputStream consoleIn;
    private final PrintStream consoleOut;

    public ChatClient(String host, int port, String name, FileLogger logger,
                      InputStream consoleIn, PrintStream consoleOut) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.logger = logger;
        this.consoleIn = consoleIn;
        this.consoleOut = consoleOut;
    }

    public void run() throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
             BufferedReader serverIn = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedReader console = new BufferedReader(
                     new InputStreamReader(consoleIn, StandardCharsets.UTF_8))) {

            out.println(name);
            logger.log("Подключен к " + host + ":" + port + " как " + name);

            Thread reader = new Thread(() -> readFromServer(serverIn), "server-reader");
            reader.setDaemon(true);
            reader.start();

            String line;
            while ((line = console.readLine()) != null) {
                if (EXIT_COMMAND.equals(line.trim())) {
                    out.println(EXIT_COMMAND);
                    break;
                }
                if (line.isBlank()) {
                    continue;
                }
                out.println(line);
                logger.log(name + ": " + line);
            }
            logger.log("Отключен от чата");
        }
    }

    private void readFromServer(BufferedReader serverIn) {
        try {
            String message;
            while ((message = serverIn.readLine()) != null) {
                consoleOut.println(message);
                logger.log(message);
            }
            consoleOut.println("Соединение с сервером закрыто. Введите " + EXIT_COMMAND + " для выхода.");
        } catch (IOException ignored) {
          
        }
    }
}
