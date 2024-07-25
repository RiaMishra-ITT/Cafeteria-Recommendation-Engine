/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentication;

import admin.AdminOperation;
import chef.ChefOperation;
import com.mycompany.recommendationsystemclient.Client;
import employee.EmployeeOperation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Authentication {
    private final Scanner scanner = new Scanner(System.in);
    private final Client client;
    public static int userId = 0;
    public static String logintime = "";
    public static String logouttime = "";
    public static List<String> activities = new ArrayList<>();

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
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            String user = (String) client.receiveStringResponse();
            if(!"Unable to login".equals(response)) {
                role = response;
                Authentication.userId = Integer.parseInt(user);
                LocalDateTime currentDateTime = LocalDateTime.now();
                Authentication.logintime = currentDateTime.toString();
                Authentication.activities.add("login");
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
