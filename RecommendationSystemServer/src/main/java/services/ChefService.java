/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.MenuItem;
import services.Interfaces.IChefService;

/**
 *
 * @author ria.mishra
 */
public class ChefService implements IChefService{
    private final RecommendationEngineService recommendationEngineService;

    public ChefService() throws UnableToConnectDatabase {
        this.recommendationEngineService = new RecommendationEngineService();
    }
    
    @Override
    public List<MenuItem> getFoodItemForNextDay(ObjectInputStream input)throws IOException {
        try {
            int mealTypeId = Integer.parseInt(input.readObject().toString());
            int noOfItems = Integer.parseInt(input.readObject().toString());
            return recommendationEngineService.getFoodItemForNextDay(mealTypeId, noOfItems);
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        }
    }
}
