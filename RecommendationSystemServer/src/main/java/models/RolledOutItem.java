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
public class RolledOutItem implements Serializable{
    public int menuItemId;
    public String itemName;
    public double price;
    public String availbilityStatus;
    public int mealTypeId;
    public String rating;
    public String sentiment;
    public String itemType;
    public String spiceType;
    public String cuisineType;
    
    public RolledOutItem(int menuItemId, String itemName, double price, String availbilityStatus, int mealTypeId, String rating, String sentiment, String itemType, String spiceType, String cuisineType) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.price = price;
        this.availbilityStatus = availbilityStatus;
        this.mealTypeId = mealTypeId;
        this.rating = rating;
        this.sentiment = sentiment;
        this.itemType = itemType;
        this.cuisineType = cuisineType;
        this.spiceType = spiceType;
    }
}
