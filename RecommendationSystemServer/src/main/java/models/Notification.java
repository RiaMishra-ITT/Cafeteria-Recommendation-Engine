/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author ria.mishra
 */
public class Notification implements Serializable{
    public int notificationId;
    public String message;
    public int notificationTypeId;
    public String dateTime;
    
    public Notification(int notificationId, String message, int notificationTypeId, String datetime) {
        this.notificationId = notificationId;
        this.message = message;
        this.notificationTypeId = notificationTypeId;
        this.dateTime = datetime;
    }
}
