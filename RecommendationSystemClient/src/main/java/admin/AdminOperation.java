/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import com.mycompany.recommendationsystemclient.Client;
import java.util.Scanner;
import services.DiscardItemService;
import services.Interfaces.IDiscardItemService;
import services.Interfaces.IMenuItemService;
import services.Interfaces.IUserActivityService;
import services.MenuItemService;
import services.UserActivityService;


public class AdminOperation {
    private final Client client;
    public AdminOperation(Client client) {
        this.client = client;
    }
    private final Scanner scanner = new Scanner(System.in);
    public void showMenu() {
        while(true) {
            System.out.println("----Operations---");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Update Menu Item");
            System.out.println("3. Read All Menu Items");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. View Discard Items");
            System.out.println("6. logout");
            int input = scanner.nextInt();
            this.handleUserInput(input);
        }
    }
    
    private void handleUserInput(int input) {
        IMenuItemService menuItemService = new MenuItemService(client);
        IDiscardItemService discardItemService = new DiscardItemService(client);
        IUserActivityService userActivityService  = new UserActivityService(client);
        switch (input) {
            case 1:
                menuItemService.addMenuItem();
                break;
            case 2:
                menuItemService.updateItem();
                break;
            case 3:
                menuItemService.viewAllItems();
                break;
            case 4:
                menuItemService.deleteMenuItem();
                break;
            case 5:
                discardItemService.viewAllDiscardItems();
                break;
            case 6:
                userActivityService.addUserActivity();
                System.exit(0);
            default:
                break;
        }
    }
}
