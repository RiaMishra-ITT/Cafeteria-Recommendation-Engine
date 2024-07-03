/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.SQLException;
import models.Notification;
import repositories.Interfaces.INotificationRepository;
import repositories.NotificationRepository;
import services.Interfaces.INotificationService;

/**
 *
 * @author ria.mishra
 */
public class NotificationService implements INotificationService {
    private final INotificationRepository notificationRepository = new NotificationRepository();
    @Override
    public int addNotification(Notification notification) throws SQLException{
        return notificationRepository.addNotification(notification);
    }
    
}
