/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import java.util.List;
import models.Notification;
import models.UserNotifcation;
import repositories.Interfaces.IUserNotificationRepository;

/**
 *
 * @author ria.mishra
 */
public class UserNotificationRepository implements IUserNotificationRepository{
    private Database database = new Database();
    @Override
    public void addUserNotification(List<UserNotifcation> userNotifications) throws SQLException {
        database.addUserNotification(userNotifications);
    }

    @Override
    public List<UserNotifcation> getRolledOutItemNotification(String date) throws SQLException {
        return database.getRolledOutItemNotifications(date);
    }

    @Override
    public List<Notification> getUserNotifications(int userId) throws SQLException {
        return database.getUserNotifications(userId);
    }
    
    
    
}
