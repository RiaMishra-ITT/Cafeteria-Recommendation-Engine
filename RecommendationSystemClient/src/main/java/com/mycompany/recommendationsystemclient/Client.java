package com.mycompany.recommendationsystemclient;


import admin.AdminOperation;
import authentication.Authentication;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String action, Object... params) {
        try {
            output.writeUTF(action);
            for (Object param : params) {
                output.writeObject(param);
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

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
