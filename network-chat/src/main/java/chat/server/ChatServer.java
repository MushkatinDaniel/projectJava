package chat.server;

import chat.common.FileLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private final int port;
    private final FileLogger logger;
    private final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();
    private final ExecutorService pool = Executors.newCachedThreadPool();

    private volatile ServerSocket serverSocket;
    private volatile boolean running;

    public ChatServer(int port, FileLogger logger) {
        this.port = port;
        this.logger = logger;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
        logger.log("Сервер запущен, порт " + getPort());
        pool.execute(this::acceptLoop);
    }


    public int getPort() {
        return serverSocket.getLocalPort();
    }

    private void acceptLoop() {
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, this);
                clients.add(handler);
                pool.execute(handler);
            } catch (IOException e) {
                if (running) {
                    logger.log("Ошибка при подключении клиента: " + e.getMessage());
                }
            }
        }
    }


    void broadcast(String message, ClientHandler sender) {
        logger.log(message);
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.send(message);
            }
        }
    }

    void remove(ClientHandler handler) {
        clients.remove(handler);
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException ignored) {
           
        }
        clients.forEach(ClientHandler::close);
        pool.shutdownNow();
        logger.log("Сервер остановлен");
    }
}
