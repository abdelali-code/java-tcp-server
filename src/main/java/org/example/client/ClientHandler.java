package org.example.client;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandler implements Runnable{
    Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());
        System.out.println("thread " + Thread.currentThread().getName());
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String msg;
            while ((msg = in.readLine()) != null) {  // loop until client disconnects
                System.out.println("received message: " + msg);

                if ("hello".equals(msg)) {
                    out.println("Bonjour client !");
                } else if ("time".equals(msg)) {
                    out.println("time is " + LocalDateTime.now());
                } else if ("bye".equals(msg)) {
                    out.println("Au revoir !");
                    break;
                } else {
                    out.println("Message reçu : " + msg);
                }
            }

        } catch (IOException e) {
            System.out.println("Client disconnected: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}
