/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recommendationsystemserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final int PORT = 1234;
    private static final int THREAD_POOL_SIZE = 100;
    
    public static void openConnection() {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                
                threadPool.execute(new ServerThread(socket));
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
