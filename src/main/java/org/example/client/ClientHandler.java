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
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String msg = in.readLine();
            System.out.println("recieved message is " + msg);
            if ("hello".equals(msg)) {
                out.println("Bonjour client !");
            }else if ("time".equals(msg)) {
                out.println("time is " + LocalDateTime.now().toString()) ;
            } else if ("bye".equals(msg)) {
                in.close();
                out.close();
                socket.close();
            }
            else {
                out.println("Message reçu : " + msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
