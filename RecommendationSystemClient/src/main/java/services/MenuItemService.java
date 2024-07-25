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
import services.Interfaces.IMenuItemService;


public  class MenuItemService implements IMenuItemService{
    public Scanner scanner = new Scanner(System.in);
    Client client;
    public MenuItemService(Client client) {
        this.client = client;
    }
    
    @Override
    public void addMenuItem() {
        try {
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
            System.out.println("Enter item type");
            String itemType = scanner.nextLine();
            System.out.println("Enter spice level");
            String spiceLevel = scanner.nextLine();
            System.out.println("Enter cuisine type");
            String cuisineType = scanner.nextLine();
            MenuItem menuItem = new MenuItem(0,itemName,price,availbilityStatus,mealTypeId,itemType,spiceLevel,cuisineType);
            client.sendRequest("addMenuItem", menuItem);
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("Menu item added");
        } catch (Exception ex) {
            System.out.println("Client Response: failed to add menu item");
        }
    }
    
    @Override
    public void viewAllItems(){
        try {
            client.sendRequest("viewAllItems", null);
            List<MenuItem> menuItems;
            menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                String mealType = menuItem.mealTypeId == 1? "Breakfast" : menuItem.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }
            Authentication.activities.add("View all items");
        } catch (IOException ex) {
            System.out.println("Client Response: failed to convert server response");
        } catch (ClassNotFoundException ex) {
            System.out.println("Client Response: failed to view menu item");
        }
        
    }
    
    @Override
    public void updateItem() {
        List<MenuItem> menuItems;
        try {
            client.sendRequest("viewAllItems", null);
            menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                String mealType = menuItem.mealTypeId == 1? "Breakfast" : menuItem.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }
            
            System.out.println("Enter the item id you want to update");
            int id = scanner.nextInt();
            MenuItem menuItem = this.getItemById(menuItems,id );
            System.out.println("Which property you want to update");
            System.out.println("1) Item name");
            System.out.println("2) Item price");
            System.out.println("3) Item status");
            System.out.println("4) Item meal type");
            System.out.println("5) Item type");
            System.out.println("6) Item spice level");
            System.out.println("7) Item cusisine type");
            System.out.print("Enter the id");
            int propertyId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter value");
            if(propertyId == 1) {
                menuItem.itemName = scanner.nextLine();
            } else if(propertyId == 2) {
                menuItem.price = scanner.nextInt();
                scanner.nextLine();
            } else if(propertyId == 3) {
                menuItem.availbilityStatus = scanner.nextLine();
            } else if(propertyId == 4) {
                menuItem.mealTypeId = scanner.nextInt();
                scanner.nextLine();
            } else if(propertyId == 5) {
                menuItem.itemType = scanner.nextLine();
                scanner.nextLine();
            } else if(propertyId == 6) {
                menuItem.spiceType = scanner.nextLine();
                scanner.nextLine();
            } else if(propertyId == 7) {
                menuItem.cuisineType = scanner.nextLine();
                scanner.nextLine();
            } else {
                System.out.println("Invalid choice");
                return;
            }
            client.sendRequest("updateMenuItem", menuItem);
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("updated menu item");
        } catch (IOException ex) {
            System.out.println("Client Response: failed to convert server response");
        } catch (Exception ex) {
            System.out.println("Client Response: failed to update item");
        }
        
    }
    
    @Override
    public MenuItem getItemById(List<MenuItem> items, int id) {
        for (MenuItem item : items) {
            if (item.menuItemId == id) {
                return item; 
            }
        }
        return null;
    }
    
    @Override
    public void deleteMenuItem() {
        List<MenuItem> menuItems;
        try {
            client.sendRequest("viewAllItems", null);
            menuItems = (List<MenuItem>) client.receiveObjectResponse().readObject();
            System.out.println("Id \t Item Name \t Item Price \t Item Status \t Meal Type");
            for(MenuItem menuItem : menuItems) {
                String mealType = menuItem.mealTypeId == 1? "Breakfast" : menuItem.menuItemId == 2 ?  "Lunch" : "Dinner";
                System.out.printf("%-15s \t %-15s \t %-10.2f \t %-12s \t %-10s%n", menuItem.menuItemId,menuItem.itemName, menuItem.price, menuItem.availbilityStatus, mealType);

            }
            
            System.out.println("Enter the item id you want to delete");
            int id = scanner.nextInt();
            MenuItem menuItem = this.getItemById(menuItems, id);
            client.sendRequest("deleteMenuItem", menuItem);
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("Deleted menu item");
        } catch (IOException ex) {
            System.out.println("Client Response: failed to convert server response");
        } catch (ClassNotFoundException ex) {
            System.out.println("Client Response: failed to delete item");
        }
        
    }

    @Override
    public void deleteMultipleItems(List<Integer> ids) {
        try {
            client.sendRequest("deleteMenuItems", ids);
            String response = (String) client.receiveStringResponse();
            System.out.println("Server Response: " + response);
            Authentication.activities.add("Removed discard items");
        } catch (Exception ex) {
            System.out.println("Client Response: failed to delete item");
        }
    }
}
