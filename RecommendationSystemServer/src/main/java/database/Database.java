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
import models.DiscardItem;
import models.DiscardItemInfo;
import models.Feedback;
import models.MenuItem;
import models.Notification;
import models.Role;
import models.User;
import models.UserActivities;
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
    
    public List<Integer> getLowRatingMenuItemIds() throws SQLException {
        String sql = "SELECT * FROM feedback WHERE LENGTH(Rating) <= 2";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> menuItemIds = new ArrayList<>();
        while (rs.next()) {
            menuItemIds.add(rs.getInt("MenuItemId"));
        }

        return menuItemIds;
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
    
    public int addNotification(Notification notification) throws SQLException {
        String sql = "insert into Notification(Message,NotificationTypeId,Datetime) values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,notification.message);
        pstmt.setInt(2,1);
        pstmt.setString(3,notification.dateTime);
        
        int rowInserted = pstmt.executeUpdate();
        
        int generatedId = -1;
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated ID.");
            }
        }

        return generatedId; 
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
    
    public List<UserNotifcation> getRolledOutItemNotifications(String date) throws SQLException {
         String sql = "SELECT un.UserNotificationId, un.NotificationId, un.UserId, un.DateTime, un.MenuItemId " +
                     "FROM UserNotification un " +
                     "JOIN Notification n ON un.NotificationId = n.NotificationId " +
                     "WHERE n.NotificationTypeId = 1 AND DATE(n.DateTime) = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, date);

        ResultSet rs = pstmt.executeQuery();
        List<UserNotifcation> notifications = new ArrayList<>();
        
        while (rs.next()) {
            int userNotificationId = rs.getInt("UserNotificationId");
            int notificationId = rs.getInt("NotificationId");
            int userId = rs.getInt("UserId");
            String dateTime = rs.getString("DateTime");
            int menuItemId = rs.getInt("MenuItemId");

            UserNotifcation notification = new UserNotifcation(userNotificationId, notificationId, userId, dateTime, menuItemId);
            notifications.add(notification);
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
    
    public void addDiscardItem(List<Integer> menuItemIds) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM discardItem WHERE MenuItemId = ?";
        String insertSql = "INSERT INTO discardItem (MenuItemId) VALUES (?)";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        PreparedStatement insertStmt = conn.prepareStatement(insertSql);

        for (int menuItemId : menuItemIds) {
            checkStmt.setInt(1, menuItemId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) { // If the menuItemId does not exist
                insertStmt.setInt(1, menuItemId);
                insertStmt.addBatch();
            }
        }
        insertStmt.executeBatch();
    }
    
    public List<DiscardItemInfo> getDiscardedItemsWithRatings() throws SQLException {
        String sql = "SELECT di.MenuItemId, mi.ItemName " +
                     "FROM discardItem di " +
                     "JOIN menuItem mi ON di.MenuItemId = mi.MenuItemId";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<DiscardItemInfo> discardedItems = new ArrayList<>();
        while (rs.next()) {
            discardedItems.add(new DiscardItemInfo(
                rs.getInt("MenuItemId"),
                rs.getString("ItemName"),
                "",
                    ""
            ));
        }

        return discardedItems;
    }
    
    public void addUserActivities(UserActivities activity) throws SQLException {
        String sql = "INSERT INTO useractivity (userId, activities, logintime, logouttime) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, activity.userId);
            pstmt.setString(2, activity.activities);
            pstmt.setString(3, activity.logintime);
            pstmt.setString(4, activity.logouttime);
            
            pstmt.executeUpdate();
        }
    }
    
    public List<Notification> getUserNotifications(int userId) throws SQLException {
        String sql = "SELECT un.userId, n.datetime, n.notificationId, n.message " +
                     "FROM UserNotification un " +
                     "JOIN Notification n ON un.notificationId = n.notificationId " +
                     "WHERE un.userId = ?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userId);

        ResultSet rs = pstmt.executeQuery();
        List<Notification> notifications = new ArrayList<>();
        while (rs.next()) {
                int uid = rs.getInt("userId");
                int nid = rs.getInt("notificationId");
                String message = rs.getString("message");
                String datetime = rs.getString("datetime");
                notifications.add(new Notification(uid, message,nid,datetime));
       }

        return notifications;
    }
    
    private void deleteDiscardItems(List<Integer> itemIds) throws SQLException {

        String placeholders = String.join(",", new String[itemIds.size()]);
        String sql = "DELETE FROM MenuItem WHERE menuItemId IN (" + placeholders + ")";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < itemIds.size(); i++) {
                pstmt.setInt(i + 1, itemIds.get(i));
            }
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println(rowsDeleted + " items removed from the menu.");
        }
    }

}
