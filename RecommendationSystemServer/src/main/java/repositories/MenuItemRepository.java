/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import java.util.List;
import models.MenuItem;
import repositories.Interfaces.IMenuItemRepository;

/**
 *
 * @author ria.mishra
 */
public class MenuItemRepository implements IMenuItemRepository{
    private Database database = new Database();
    @Override
    public int addMenuItem(MenuItem menuItem) throws SQLException {
        return database.addMenuItem(menuItem);
    }

    @Override
    public void removeMenuItem(MenuItem menuItem)throws SQLException {
        database.deleteMenuItem(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItem()throws SQLException {
        return database.getAllMenuItems();
    }

    @Override
    public int updateMenuItem(MenuItem menuItem)throws SQLException {
        return database.updateMenuItem(menuItem);
    }

    @Override
    public MenuItem getMenuItemById(int menuItemId) throws SQLException{
        return database.getMenuItemById(menuItemId);
    }
    
    @Override
    public List<MenuItem> getItemsByMealType(int mealTypeId)throws SQLException {
        return database.getItemsByMealType(mealTypeId);
    }
}
