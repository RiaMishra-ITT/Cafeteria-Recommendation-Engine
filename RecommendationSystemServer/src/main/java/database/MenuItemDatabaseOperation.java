/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import customexception.UnableToConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public class MenuItemDatabaseOperation {
    private final Database database = new Database();
    private static Connection conn;
    
    public MenuItemDatabaseOperation() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public int addMenuItem(MenuItem menuItem) throws SQLException {
        String sql = "INSERT INTO menuItem (ItemName, Price, AvailbilityStatus,MealTypeId) VALUES (?, ?, ?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, menuItem.itemName);
        pstmt.setDouble(2, menuItem.price);
        pstmt.setString(3, menuItem.availbilityStatus);
        pstmt.setInt(4,menuItem.mealTypeId);

        int rowInserted = pstmt.executeUpdate();
        return rowInserted;
    }
    
    public List<MenuItem> getAllMenuItems() throws SQLException{
        String sql = "Select * from menuItem";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<MenuItem> menuItems= new ArrayList<>();
        while(rs.next()) {
            menuItems.add(new MenuItem(rs.getInt("MenuItemId"),rs.getString("ItemName"),
                    rs.getDouble("Price"),rs.getString("AvailbilityStatus"),rs.getInt("MealTypeId")));
        }
        
        return menuItems;
    }
    
    public int updateMenuItem(MenuItem menuItem) throws SQLException {
        String sql = "UPDATE menuItem SET itemName = ?, price = ?, availbilityStatus = ?, mealTypeId = ? WHERE menuItemId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, menuItem.itemName);
        pstmt.setDouble(2, menuItem.price);
        pstmt.setString(3, menuItem.availbilityStatus);
        pstmt.setInt(4, menuItem.mealTypeId);
        pstmt.setInt(5, menuItem.menuItemId);

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected;
    }
    
    public List<MenuItem> getItemsByMealType(int mealTypeId) throws SQLException {
        String sql = "Select * from menuItem where mealTypeId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, mealTypeId);
        ResultSet rs = pstmt.executeQuery();
        List<MenuItem> menuItems= new ArrayList<>();
        while(rs.next()) {
            menuItems.add(new MenuItem(rs.getInt("MenuItemId"),rs.getString("ItemName"),
                    rs.getDouble("Price"),rs.getString("AvailbilityStatus"),rs.getInt("MealTypeId")));
        }
        
        return menuItems;
    }
    
    public void deleteMenuItem(MenuItem menuItem) throws SQLException {
        String sql = "DELETE FROM menuItem WHERE menuItemId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, menuItem.menuItemId);
        pstmt.executeUpdate();
    }
    
    public MenuItem getMenuItemById(int menuItemId) throws SQLException {
        String sql = "Select * from MenuItem where menuItemId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, menuItemId);
        ResultSet rs = pstmt.executeQuery();
        MenuItem menuItem = new MenuItem();
        while(rs.next()) {
            menuItem = new MenuItem(rs.getInt("MenuItemId"),rs.getString("ItemName"),
                    rs.getDouble("Price"),rs.getString("AvailbilityStatus"),rs.getInt("MealTypeId"));
        }
        return menuItem;
    }
}
