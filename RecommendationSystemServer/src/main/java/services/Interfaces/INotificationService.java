/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import models.Notification;

/**
 *
 * @author ria.mishra
 */
public interface INotificationService {
    public int addNotification(Notification notification) throws IOException;
}
