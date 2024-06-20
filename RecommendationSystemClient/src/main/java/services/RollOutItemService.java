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
        System.out.println("Enter meal type");
        String mealType = scanner.nextLine();
        System.out.println("How many items you want to roll out?");
        int noOfItems = scanner.nextInt();
        client.sendRequest("rollOutItem", mealType,noOfItems);
        try {
            List<MenuItem> menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                System.out.printf("%-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }

        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
