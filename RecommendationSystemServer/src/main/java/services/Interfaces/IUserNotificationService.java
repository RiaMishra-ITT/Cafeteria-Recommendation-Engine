/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.Notification;
import models.RolledOutItem;

/**
 *
 * @author ria.mishra
 */
public interface IUserNotificationService {
    void addNotification(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException;
    List<RolledOutItem> getRolledOutItemNotifications() throws IOException, ClassNotFoundException, SQLException;
    List<Notification> getUserNotifications(ObjectInputStream input) throws SQLException,IOException,ClassNotFoundException;
}
