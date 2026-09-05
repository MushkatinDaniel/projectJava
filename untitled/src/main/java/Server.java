import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен, ждём подключения на порту " + port);

        Socket clientSocket = serverSocket.accept();
        System.out.println("New connection accepted");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String receivedLine = in.readLine();
        System.out.println("Получено сообщение: \"" + receivedLine +
                "\" с порта клиента: " + clientSocket.getPort());

        out.println(GreetingFormatter.buildGreeting(receivedLine, clientSocket.getPort()));

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}