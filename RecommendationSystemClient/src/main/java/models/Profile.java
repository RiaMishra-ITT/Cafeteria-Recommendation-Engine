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
public class Profile implements Serializable{
    public int profileId;
    public int userId;
    public String dietaryPreference;
    public String spiceLevel;
    public String cuisinePreference;
    public String sweetTooth;

    public Profile(int userId, String dietaryPreference, String spiceLevel, String cuisinePreference, String sweetTooth) {
        this.userId = userId;
        this.dietaryPreference = dietaryPreference;
        this.spiceLevel = spiceLevel;
        this.cuisinePreference = cuisinePreference;
        this.sweetTooth = sweetTooth;
    }
}
