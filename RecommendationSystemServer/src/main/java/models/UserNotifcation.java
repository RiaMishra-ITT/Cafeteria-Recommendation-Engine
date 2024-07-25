/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ria.mishra
 */
public class UserNotifcation {
    public int userNotificationId;
    public int notificationId;
    public int userId;
    public String dateTime;
    public int menuItemId;
    
        public UserNotifcation(int userNotificationId, int notificationId, int userId, String dateTime, int menuItemId) {
        this.userNotificationId = userNotificationId;
        this.notificationId = notificationId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.menuItemId = menuItemId;
    }
}
