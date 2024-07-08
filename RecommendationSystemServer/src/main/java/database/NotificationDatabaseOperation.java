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
import models.Notification;
import models.UserNotifcation;

/**
 *
 * @author ria.mishra
 */
public class NotificationDatabaseOperation {
    private final Database database = new Database();
    private static Connection conn;
    
    public NotificationDatabaseOperation() throws UnableToConnectDatabase {
        conn = database.getConnection();
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
}
