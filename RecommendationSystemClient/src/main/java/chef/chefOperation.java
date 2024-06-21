/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chef;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import services.MenuItemService;
import services.RollOutItemService;

/**
 *
 * @author ria.mishra
 */
public class ChefOperation {
   private Client client;
    public ChefOperation(Client client) {
        this.client = client;
    }
    private Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        while(true) {
            System.out.println("----Operations---");
            System.out.println("1. Roll out item");
            System.out.println("2. Exit");
            int input = scanner.nextInt();
            this.handleUserInput(input);
        }
    }
    
    private void handleUserInput(int input) {
        RollOutItemService rollOutItemService = new RollOutItemService(client);
        if(input == 1) {
            rollOutItemService.rollOutItem();
        } else if(input == 2 ) {
            System.exit(1);
        }
    } 
}
