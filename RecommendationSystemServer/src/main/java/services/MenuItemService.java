/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.FailedToAddItemException;
import customexception.FailedToUpdateItemException;
import customexception.UnableToConnectDatabase;
import Repositories.MenuItemRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.MenuItem;
import services.Interfaces.IMenuItemService;


public class MenuItemService implements IMenuItemService{
    private final MenuItemRepository menuItemRepository;
    
    public MenuItemService() throws UnableToConnectDatabase {
        this.menuItemRepository = new MenuItemRepository();
    }
    
    @Override
    public void addMenuItem(ObjectInputStream input) throws IOException, FailedToAddItemException {
       try {
           MenuItem menuItem = (MenuItem) input.readObject();
           int totalRows = menuItemRepository.addMenuItem(menuItem);
           if(totalRows <= 0) {
            throw new FailedToAddItemException("Failed to add item");
           }
        } catch (SQLException ex) {
            throw new IOException("Failed to add menu item");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
    @Override
    public void removeMenuItem(ObjectInputStream input) throws IOException {
        try {
            MenuItem menuItem = (MenuItem) input.readObject();
            menuItemRepository.deleteMenuItem(menuItem);
        } catch (SQLException ex) {
            throw new IOException("Failed to remove menu item");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }

    @Override
    public List<MenuItem> getAllMenuItem() throws IOException {
        try {
            return menuItemRepository.getAllMenuItems();
        } catch (SQLException ex) {
            throw new IOException("Failed to remove menu item");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }

    @Override
    public void updateMenuItem(ObjectInputStream input) throws FailedToUpdateItemException, IOException {
        try {
            MenuItem menuItem = (MenuItem) input.readObject();
            int totalRows = menuItemRepository.updateMenuItem(menuItem);
            if(totalRows <= 0) {
                throw new FailedToUpdateItemException("Failed to update item");
            }
        } catch (SQLException ex) {
            throw new IOException("Failed to updated menu item");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
        
    }
    
    @Override
    public List<MenuItem> getItemsByMealType(int mealTypeId) throws IOException {
        try {
            return menuItemRepository.getItemsByMealType(mealTypeId);
        } catch (SQLException ex) {
            throw new IOException("Failed to get item by meal type");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
        
    }

    @Override
    public void removeMenuItems(ObjectInputStream input) throws IOException {
        try {
            List<Integer> ids = (List<Integer>) input.readObject();
            menuItemRepository.deleteMenuItems(ids);
        } catch (SQLException ex) {
            throw new IOException("Failed to remove items");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
        
    }
}
