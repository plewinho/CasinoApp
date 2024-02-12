package com.main.casinoapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientHandlerCallable implements Callable<ServerResponse> {
    private Socket clientSocket;

    public ClientHandlerCallable(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public ServerResponse call() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();
            ServerResponse serverResponse = new ServerResponse();
            if(clientRequest.getAction().equals("login")) {
                LoginData loginData = (LoginData) clientRequest.getData();
                User user = UserData.getAuthenticatedUser(loginData.getEm(), loginData.getPas());
                serverResponse.setData(user);
            } else if (clientRequest.getAction().equals("register")) {

            }
            objectOutputStream.writeObject(serverResponse);
            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
