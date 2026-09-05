package ru.mushkatin;

import java.io.*;
import java.net.*;

public class ClientServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;


        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection accepted");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String name = in.readLine();
                System.out.println("Получено сообщение: \"" + name +
                        "\" с порта клиента: " + clientSocket.getPort());

                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();
        Thread.sleep(500);


        Socket socket = new Socket("localhost", port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("Daniil");
        System.out.println("Ответ от сервера: " + in.readLine());

        in.close();
        out.close();
        socket.close();

        serverThread.join();
    }
}
