/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentication;

import admin.AdminOperation;
import com.mycompany.recommendationsystemclient.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import models.User;


public class Authentication {
    private Scanner scanner = new Scanner(System.in);
    private Client client;

    public Authentication(Client client) {
        this.client = client;
    }

    public void login() {
        String role = "";
        while (true) {
            System.out.println("Enter user ID:");
            String userId = scanner.nextLine();
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            
            client.sendRequest("login", userId, name);
            String response = (String) client.receiveResponse();
            System.out.println("Server Response: " + response);
            if(response != "Unable to login") {
                role = response;
                break;
            }
        }
        
        if(role.equals("Admin")) {
            AdminOperation adminOperation = new AdminOperation();
            adminOperation.showMenu();
        }
    }
    
}
