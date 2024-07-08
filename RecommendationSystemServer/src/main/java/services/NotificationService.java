/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import database.NotificationDatabaseOperation;
import java.sql.SQLException;
import models.Notification;
import services.Interfaces.INotificationService;

/**
 *
 * @author ria.mishra
 */
public class NotificationService implements INotificationService {
    private final NotificationDatabaseOperation dbOperation;

    public NotificationService() throws UnableToConnectDatabase {
        this.dbOperation = new NotificationDatabaseOperation();
    }
    @Override
    public int addNotification(Notification notification) throws SQLException{
        return dbOperation.addNotification(notification);
    }
    
}
