/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import models.Notification;
import repositories.Interfaces.INotificationRepository;

/**
 *
 * @author ria.mishra
 */
public class NotificationRepository implements INotificationRepository{
    private final Database database = new Database();
    @Override
    public int addNotification(Notification notification) throws SQLException {
        return database.addNotification(notification);
    }
    
}
