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


public  class MenuItemService {
    Scanner scanner = new Scanner(System.in);
    Client client;
    public MenuItemService(Client client) {
        this.client = client;
    }
    
    public void addMenuItem() {
        System.out.println("Add Item name");
        String itemName = scanner.nextLine();
        System.out.println("Add Item price");
        int price = scanner.nextInt();
        System.out.println("Add Meal type");
        System.out.println("Available meal types");
        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.println("Enter meal type id");
        int mealTypeId = scanner.nextInt();
        System.out.println("Add Item availbility");
        scanner.nextLine();
        String availbilityStatus = scanner.nextLine();
        MenuItem menuItem = new MenuItem(0,itemName,price,availbilityStatus,mealTypeId);
        client.sendRequest("addMenuItem", menuItem);
        String response = (String) client.receiveResponse();
        System.out.println("Server Response: " + response);
    }
    
    public void viewAllItems(){
        client.sendRequest("viewAllItems", "");
        List<MenuItem> menuItems;
        try {
            menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                String mealType = menuItem.mealTypeId == 1? "Breakfast" : menuItem.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }

        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateItem() {
        List<MenuItem> menuItems;
        try {
            client.sendRequest("viewAllItems", "");
            menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                String mealType = menuItem.mealTypeId == 1? "Breakfast" : menuItem.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }
            
            System.out.println("Enter the item id you want to update");
            int id = scanner.nextInt();
            System.out.println("Which property you want to update");
            System.out.println("1) Item name");
            System.out.println("2) Item price");
            System.out.println("3) Item status");
            System.out.println("4) Item meal type");
            System.out.print("Enter the id");
            int propertyId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter value");
            if(propertyId == 1) {
                menuItems.get(id - 1).itemName = scanner.nextLine();
            } else if(propertyId == 2) {
                menuItems.get(id - 1).price = scanner.nextInt();
                scanner.nextLine();
            } else if(propertyId == 3) {
                menuItems.get(id -1).availbilityStatus = scanner.nextLine();
            } else if(propertyId == 4) {
                menuItems.get(id - 1).mealTypeId = scanner.nextInt();
                scanner.nextLine();
            }
            client.sendRequest("updateMenuItem", menuItems.get(id -1));
            String response = (String) client.receiveResponse();
            System.out.println("Server Response: " + response);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteMenuItem() {
        List<MenuItem> menuItems;
        try {
            client.sendRequest("viewAllItems", "");
            menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                String mealType = menuItem.mealTypeId == 1? "Breakfast" : menuItem.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }
            
            System.out.println("Enter the item id you want to delete");
            int id = scanner.nextInt();
            client.sendRequest("deleteMenuItem", menuItems.get(id -1));
            String response = (String) client.receiveResponse();
            System.out.println("Server Response: " + response);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
