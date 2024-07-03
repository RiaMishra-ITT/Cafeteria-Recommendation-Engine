/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chef;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import services.DiscardItemService;
import services.Interfaces.IDiscardItemService;
import services.Interfaces.IRollOutItemService;
import services.Interfaces.IUserActivityService;
import services.RollOutItemService;
import services.UserActivityService;

/**
 *
 * @author ria.mishra
 */
public class ChefOperation {
   private final Client client;
    public ChefOperation(Client client) {
        this.client = client;
    }
    private final Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        while(true) {
            System.out.println("----Operations---");
            System.out.println("1. Roll out item");
            System.out.println("2. View discard items");
            System.out.println("3. Logout");
            int input = scanner.nextInt();
            this.handleUserInput(input);
        }
    }
    
    private void handleUserInput(int input) {
        IRollOutItemService rollOutItemService = new RollOutItemService(client);
        IDiscardItemService discardItemService = new DiscardItemService(client);
        IUserActivityService userActivityService = new UserActivityService(client);
       switch (input) {
           case 1:
               rollOutItemService.rollOutItem();
               break;
           case 2:
               discardItemService.viewAllDiscardItems();
               break;
           case 3:
               userActivityService.addUserActivity();
               System.exit(1);
           default:
               break;
       }
    } 
}
