/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Feedback;
import models.MenuItem;
import models.Role;
import models.User;


public class Database {
    String url = "jdbc:mysql://localhost:3306/recommendationsystem";
    String username = "root";
    String password = "root";
    Connection conn = null;
    Statement stmt = null;
    
    public Database() {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        }  catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public User getUserByIdAndName(String userId, String name) {
        String sql = "SELECT * " +
                     "FROM users u "+
                     "WHERE u.UserID = ? AND u.Name = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = Integer.parseInt(rs.getString("RoleId"));
                return new User(id, rs.getString("Name"), rs.getInt("RoleId"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }
    
    public Role getRoleById(int roleId) throws SQLException {
        String sql = "Select * from Roles where RoleID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roleId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Role(rs.getInt("roleID"), rs.getString("Role"));
        }
        
        return null;
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
    
    public List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException {
        String sql = "Select * from feedback where menuItemId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, menuItemId);
        ResultSet rs = pstmt.executeQuery();
        List<Feedback> feedbacks= new ArrayList<>();
        while(rs.next()) {
            feedbacks.add(new Feedback(rs.getInt("FeedbackId"),rs.getString("Comment"),
                    rs.getString("Rating"),rs.getString("Date"),rs.getInt("MenuItemId"),rs.getInt("UserId")));
        }
        
        return feedbacks;
    }
}
