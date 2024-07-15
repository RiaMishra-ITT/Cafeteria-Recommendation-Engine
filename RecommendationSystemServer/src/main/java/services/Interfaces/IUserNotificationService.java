/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import customexception.FailedToAddNotification;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import models.Notification;
import models.RolledOutItem;

/**
 *
 * @author ria.mishra
 */
public interface IUserNotificationService {
    void addNotification(ObjectInputStream input) throws IOException,FailedToAddNotification;
    List<RolledOutItem> getRolledOutItemNotifications(ObjectInputStream input) throws IOException;
    List<Notification> getUserNotifications(ObjectInputStream input) throws IOException;
    public void sendDetailFeedbackNotification(Set<Integer> menuItemId) throws IOException;
    public int getMenuItemIdByNotification(int notificationId) throws IOException;
}
