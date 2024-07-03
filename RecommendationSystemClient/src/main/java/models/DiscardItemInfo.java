/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;


public class DiscardItemInfo implements Serializable{
    public int menuItemId;
    public String itemName;
    public String rating;
    public String sentiment;
    
    public DiscardItemInfo(int menuItemId, String itemName,String rating, String sentiment) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.rating = rating;
        this.sentiment = sentiment;
    }
}
