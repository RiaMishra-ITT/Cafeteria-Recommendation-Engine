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
public class UserVote implements Serializable{
    public int votingId;
    public int userId;
    public int menuItemId;
    public String date;
    
    public UserVote(int votingId, int userId, int menuItemId, String date) {
        this.votingId = votingId;
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.date = date;
    }
}
