/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentication;

import admin.AdminOperation;
import chef.ChefOperation;
import com.mycompany.recommendationsystemclient.Client;
import employee.EmployeeOperation;
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
            if(!"Unable to login".equals(response)) {
                role = response;
                break;
            }
        }
        
        if(role.equals("Admin")) {
            AdminOperation adminOperation = new AdminOperation(client);
            adminOperation.showMenu();
        } else if(role.equals("Chef")) {
            ChefOperation chefOperation = new ChefOperation(client);
            chefOperation.showMenu();
        }else if(role.equals("Employee")) {
            EmployeeOperation employeeOperation = new EmployeeOperation(client);
            employeeOperation.showMenu();
        }
    }
    
}
