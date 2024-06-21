/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public class RollOutItemService {
    Scanner scanner = new Scanner(System.in);
    Client client;
    public RollOutItemService(Client client) {
        this.client = client;
    }
    
    public void rollOutItem() {
        System.out.println("Available meal types");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("Enter meal type id");
        String mealType = scanner.nextLine();
        System.out.println("How many items you want to roll out?");
        int noOfItems = scanner.nextInt();
        scanner.nextLine();
        client.sendRequest("rollOutItem", mealType,noOfItems);
        try {
            List<MenuItem> menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();

            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }
            
            System.out.println("Enter items id you want to select by comma seperated");
            String input = scanner.nextLine();
            client.sendRequest("addUserNotification", input);
            String response = (String) client.receiveResponse();
            System.out.println("Server Response: " + response);

        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
