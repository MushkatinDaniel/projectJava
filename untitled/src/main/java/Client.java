import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8080;

        Socket socket = new Socket(host, port);
        System.out.println("Подключились к серверу " + host + ":" + port);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        out.println("Daniil");

        String response = in.readLine();
        System.out.println("Ответ от сервера: " + response);

        in.close();
        out.close();
        socket.close();
    }
}