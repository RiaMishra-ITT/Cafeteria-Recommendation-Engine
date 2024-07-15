/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import Repositories.NotificationRepository;
import java.io.IOException;
import java.sql.SQLException;
import models.Notification;
import services.Interfaces.INotificationService;

/**
 *
 * @author ria.mishra
 */
public class NotificationService implements INotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService() throws UnableToConnectDatabase {
        this.notificationRepository = new NotificationRepository();
    }
    @Override
    public int addNotification(Notification notification) throws IOException{
        try {
            return notificationRepository.addNotification(notification);
        } catch (SQLException ex) {
            throw new IOException("Failed to add notification");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        }    
    }
    
}
