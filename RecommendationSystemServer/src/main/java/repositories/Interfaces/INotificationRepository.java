/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import models.Notification;

/**
 *
 * @author ria.mishra
 */
public interface INotificationRepository {
    public int addNotification(Notification notification) throws SQLException;
}
