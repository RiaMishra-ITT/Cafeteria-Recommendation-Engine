/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import services.EmployeeService;
import services.MenuItemService;

/**
 *
 * @author ria.mishra
 */
public class EmployeeOperation {
    private Client client;
    public EmployeeOperation(Client client) {
        this.client = client;
    }
    private Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        while(true) {
            System.out.println("----Operations---");
            System.out.println("1. View rolled out items");
            System.out.println("2. Submit feedback");
            System.out.println("3. View notifications");
            System.out.println("4. Exit");
            int input = scanner.nextInt();
            this.handleUserInput(input);
        }
    }
    
    private void handleUserInput(int input) {
        EmployeeService employeeService = new EmployeeService(client);
        if(input == 1) {
            //menuItemService.addMenuItem();
        } else if( input == 2) {
            employeeService.submitFeedback();
        } 
    }
}
