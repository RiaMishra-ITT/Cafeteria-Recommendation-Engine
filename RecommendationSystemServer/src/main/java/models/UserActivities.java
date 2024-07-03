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
public class UserActivities implements Serializable{
    public int activityId;
    public int userId;
    public String activities;
    public String logintime;
    public String logouttime;
    
    public UserActivities(int activityId, int userId, String activities, String logintime, String logouttime) {
        this.activityId = activityId;
        this.userId = userId;
        this.activities = activities;
        this.logintime = logintime;
        this.logouttime = logouttime;
    }

}
