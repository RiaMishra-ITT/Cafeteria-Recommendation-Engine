/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import authentication.Authentication;
import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MenuItem;
import services.Interfaces.IRollOutItemService;

/**
 *
 * @author ria.mishra
 */
public class RollOutItemService implements IRollOutItemService{
    Scanner scanner = new Scanner(System.in);
    Client client;
    public RollOutItemService(Client client) {
        this.client = client;
    }
    
    @Override
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
            int mealTypeId = "1".equals(mealType) ? 1 : ("2".equals(mealType) ? 2 : 3);
            if(mealTypeId == 1) {
                mealType = "Breakfast";
            } else if(mealTypeId == 2) {
                mealType = "Lunch";
            } else if(mealTypeId == 3) {
                mealType = "Dinner";
            }
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                if(menuItem.mealTypeId == mealTypeId) {
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);
                
            }

            }
            
            System.out.println("Enter items id you want to select by comma seperated");
            String input = scanner.nextLine();
            client.sendRequest("addUserNotification", input);
            String response = (String) client.receiveResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("Roll out item");
        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
