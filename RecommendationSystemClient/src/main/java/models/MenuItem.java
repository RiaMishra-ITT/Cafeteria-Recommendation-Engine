/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;


public class MenuItem implements Serializable{
    public int menuItemId;
    public String itemName;
    public double price;
    public String availbilityStatus;
    public int mealTypeId;
    public String itemType;
    public String spiceType;
    public String cuisineType;
    
    public MenuItem(int menuItemId, String itemName, double price, String availbilityStatus, int mealTypeId, String itemType, String spiceType, String cuisineType) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.price = price;
        this.availbilityStatus = availbilityStatus;
        this.mealTypeId = mealTypeId;
        this.itemType = itemType;
        this.cuisineType = cuisineType;
        this.spiceType = spiceType;
    }
    
    public MenuItem() {}
}
