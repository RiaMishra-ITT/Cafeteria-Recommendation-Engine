/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.FailedToAddItemException;
import customexception.FailedToUpdateItemException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.MenuItem;
import repositories.Interfaces.IMenuItemRepository;
import repositories.MenuItemRepository;
import services.Interfaces.IMenuItemService;


public class MenuItemService implements IMenuItemService{
    private static final IMenuItemRepository menuItemRepository = new MenuItemRepository();
    
    public void addMenuItem(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException, FailedToAddItemException {
       MenuItem menuItem = (MenuItem) input.readObject();
       int totalRows = menuItemRepository.addMenuItem(menuItem);
       if(totalRows <= 0) {
           throw new FailedToAddItemException("Failed to add item");
       }
    }
    

    @Override
    public MenuItem getMenuItemById(MenuItem menuItem) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeMenuItem(ObjectInputStream input) throws SQLException, FailedToUpdateItemException, IOException, ClassNotFoundException {
        MenuItem menuItem = (MenuItem) input.readObject();
        menuItemRepository.removeMenuItem(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItem() throws SQLException {
        return menuItemRepository.getAllMenuItem();
    }

    @Override
    public void updateMenuItem(ObjectInputStream input) throws SQLException, FailedToUpdateItemException, IOException, ClassNotFoundException {
        MenuItem menuItem = (MenuItem) input.readObject();
        int totalRows = menuItemRepository.updateMenuItem(menuItem);
        if(totalRows <= 0) {
            throw new FailedToUpdateItemException("Failed to update item");
        }
    }
    
    @Override
    public List<MenuItem> getItemsByMealType(int mealTypeId) throws SQLException {
        return menuItemRepository.getItemsByMealType(mealTypeId);
    }
}
