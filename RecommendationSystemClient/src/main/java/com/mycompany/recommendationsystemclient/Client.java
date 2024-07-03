package com.mycompany.recommendationsystemclient;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1234;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static Socket socket;
    
    public Client() {
        try {
            socket = new Socket(HOST, PORT);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Server got connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String action, Object... params) {
        try {
            output.writeUTF(action);
            if(params != null) {
                for (Object param : params) {
                output.writeObject(param);
                }
            }
            
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object receiveResponse() {
        try {
            return input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ObjectInputStream receiveObjectResponse() {
        return input;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
