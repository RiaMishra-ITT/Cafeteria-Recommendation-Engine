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
import models.UserNotifcation;


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
    
    public int submitFeedback(Feedback feedback) throws SQLException{
        String sql = "INSERT INTO feedback (Comment, Rating,Date,MenuItemId,UserId) VALUES (?, ?, ?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, feedback.comment);
        pstmt.setString(2, feedback.rating);
        pstmt.setString(3, feedback.date);
        pstmt.setInt(4,feedback.menuItemId);
        pstmt.setInt(5,feedback.userId);

        int rowInserted = pstmt.executeUpdate();
        return rowInserted;
    }
    
    public int addUserNotification(List<UserNotifcation> userNotifications) throws SQLException {
        String sql = "insert into UserNotification(NotificationId,UserId,Datetime,MenuItemId) values(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for(UserNotifcation notification : userNotifications) {
            System.out.println(notification.menuItemId);
            pstmt.setInt(1,notification.notificationId);
            pstmt.setInt(2,notification.userId);
            pstmt.setString(3,notification.dateTime);
             pstmt.setInt(4,notification.menuItemId);
            pstmt.addBatch();
        }
        
        int[] results = pstmt.executeBatch();
        return results.length;
    }
    
    public List<User> getUserByRole(int roleId) throws SQLException {
        String sql = "Select * from Users where roleId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roleId);
        ResultSet rs = pstmt.executeQuery();
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            users.add(new User(rs.getInt("UserID"), rs.getString("Name"), rs.getInt("RoleId")));
        }
        
        return users;
    }
    
    public List<UserNotifcation> getRolledOutItemNotifications() throws SQLException {
        String sql = "Select * from UserNotification where NotificationId = 8";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<UserNotifcation> notifications = new ArrayList<>();
        while(rs.next()) {
            notifications.add(new UserNotifcation(rs.getInt("UserNotificationId"), rs.getInt("NotificationId"), rs.getInt("UserId"),rs.getString("DateTime"),rs.getInt("MenuItemId")));
        }
        
        return notifications;
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
