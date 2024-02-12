package com.main.casinoapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestSender {
    public RequestSender() {
    }

    public static ServerResponse sendRequest(ClientRequest request) {
        try (Socket socket = new Socket("127.0.0.1", 12345);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            outputStream.writeObject(request);
            return (ServerResponse) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
