/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import authentication.Authentication;
import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Notification;
import services.Interfaces.IUserNotificationService;

/**
 *
 * @author ria.mishra
 */
public class UserNotifications implements IUserNotificationService{
    Client client;
    public UserNotifications(Client client) {
        this.client = client;
    }
    
    @Override
    public void viewNotifications() {
        List<Notification> notifications;
        
        try {
            client.sendRequest("usernotification", Authentication.userId);
            notifications = (List<Notification>) client.receiveObjectResponse().readObject();
            System.out.println("Notification ID\tMessage\tNotification Type ID\tDate Time");
            System.out.println("-------------------------------------------------------------");

            for (Notification notification : notifications) {
                System.out.println(notification.notificationId + "\t" 
                                 + notification.message + "\t" 
                                 + notification.notificationTypeId + "\t" 
                                 + notification.dateTime);
        }
        } catch (IOException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
