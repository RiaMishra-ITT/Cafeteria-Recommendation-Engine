/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public class ChefService {
    private RecommendationEngineService recommendationEngineService= new RecommendationEngineService();
    
    public List<MenuItem> getFoodItemForNextDay(ObjectInputStream input)throws SQLException,IOException,ClassNotFoundException {
        int mealTypeId = Integer.parseInt(input.readObject().toString());
        int noOfItems = Integer.parseInt(input.readObject().toString());
        return recommendationEngineService.getFoodItemForNextDay(mealTypeId, noOfItems);
    }
}
