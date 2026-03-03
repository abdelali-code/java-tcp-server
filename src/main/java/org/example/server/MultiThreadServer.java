package org.example.server;

import org.example.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {

    static ExecutorService executorService = Executors.newFixedThreadPool(5);
    static int port = 5000;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new ClientHandler(socket));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
