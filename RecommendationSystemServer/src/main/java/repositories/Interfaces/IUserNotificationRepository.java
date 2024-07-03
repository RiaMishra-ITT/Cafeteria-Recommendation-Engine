/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import java.util.List;
import models.Notification;
import models.UserNotifcation;

/**
 *
 * @author ria.mishra
 */
public interface IUserNotificationRepository {
    void addUserNotification(List<UserNotifcation> userNotifications) throws SQLException;
    List<UserNotifcation> getRolledOutItemNotification(String date) throws SQLException;
    public List<Notification> getUserNotifications(int userId) throws SQLException;
}
