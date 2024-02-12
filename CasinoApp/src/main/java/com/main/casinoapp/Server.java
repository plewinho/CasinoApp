package com.main.casinoapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            serverSocket = new ServerSocket(12345);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Callable<ServerResponse> clientTask = new ClientHandlerCallable(clientSocket);
                executorService.submit(clientTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown();
        }
    }
}
