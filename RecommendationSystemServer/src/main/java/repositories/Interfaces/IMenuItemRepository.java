/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import java.util.List;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public interface IMenuItemRepository {
    public int addMenuItem(MenuItem menuItem) throws SQLException;
    public void removeMenuItem(MenuItem menuItem) throws SQLException;
    public List<MenuItem> getAllMenuItem() throws SQLException;
    public int updateMenuItem(MenuItem menuItem) throws SQLException;
    MenuItem getMenuItemById(int menuItemId) throws SQLException;
    List<MenuItem> getItemsByMealType(int mealTypeId)throws SQLException;
}
