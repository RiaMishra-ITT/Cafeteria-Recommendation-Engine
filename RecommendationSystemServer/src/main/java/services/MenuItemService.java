/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import database.Database;
import java.io.IOException;
import java.io.ObjectInputStream;
import models.MenuItem;


public class MenuItemService {
    private static Database database = new Database();
    
    public String addMenuItem(ObjectInputStream input) throws IOException, ClassNotFoundException {
       try
       {
           System.out.println("add menu item");
           System.out.println(input.readObject());
           MenuItem menuItem = (MenuItem) input.readObject();
           database.addMenuItem(menuItem);
           return "Item added successfully";
       } catch (Exception ex) {
           return "Failed to add item";
       }
    }
    
    public static void updateMenuItem() {
        
    }
    
    public static void deleteMenuItem() {
        
    }
    
    public static void getMenuItem() {
        
    }
}
