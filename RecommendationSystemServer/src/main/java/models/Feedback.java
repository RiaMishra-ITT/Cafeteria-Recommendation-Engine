/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


public class Feedback {
    public int feedbackId;
    public String comment;
    public String rating;
    public String date;
    public int menuItemId;
    public int userId;
    
    public Feedback(int feedbackId, String comment, String rating, String date, int menuItemId, int userId) {
        this.feedbackId = feedbackId;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.menuItemId = menuItemId;
        this.userId = userId;
    }
}
