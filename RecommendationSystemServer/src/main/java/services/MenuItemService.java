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
    private final MenuItemRepository dbOperation;
    
    public MenuItemService() throws UnableToConnectDatabase {
        this.dbOperation = new MenuItemRepository();
    }
    
    @Override
    public void addMenuItem(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException, FailedToAddItemException {
       MenuItem menuItem = (MenuItem) input.readObject();
       int totalRows = dbOperation.addMenuItem(menuItem);
       if(totalRows <= 0) {
           throw new FailedToAddItemException("Failed to add item");
       }
    }
    
    @Override
    public void removeMenuItem(ObjectInputStream input) throws SQLException, FailedToUpdateItemException, IOException, ClassNotFoundException {
        MenuItem menuItem = (MenuItem) input.readObject();
        dbOperation.deleteMenuItem(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItem() throws SQLException {
        return dbOperation.getAllMenuItems();
    }

    @Override
    public void updateMenuItem(ObjectInputStream input) throws SQLException, FailedToUpdateItemException, IOException, ClassNotFoundException {
        MenuItem menuItem = (MenuItem) input.readObject();
        int totalRows = dbOperation.updateMenuItem(menuItem);
        if(totalRows <= 0) {
            throw new FailedToUpdateItemException("Failed to update item");
        }
    }
    
    @Override
    public List<MenuItem> getItemsByMealType(int mealTypeId) throws SQLException {
        return dbOperation.getItemsByMealType(mealTypeId);
    }

    @Override
    public void removeMenuItems(ObjectInputStream input) throws SQLException, FailedToUpdateItemException, IOException, ClassNotFoundException {
        List<Integer> ids = (List<Integer>) input.readObject();
        dbOperation.deleteMenuItems(ids);
    }
}
