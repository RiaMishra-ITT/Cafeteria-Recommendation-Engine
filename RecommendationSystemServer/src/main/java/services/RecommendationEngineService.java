/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.MenuItem;
import services.Interfaces.IFeedbackService;

/**
 *
 * @author ria.mishra
 */
public class RecommendationEngineService {
    private FeedbackService feedbackService = new FeedbackService();
    public List<MenuItem> getFoodItemForNextDay(int mealTypeId, int noOfItems) throws SQLException{
        List<MenuItem> menuItems = new ArrayList<>();
        List<MenuItem> positiveItems = new ArrayList<>();
        List<MenuItem> negativeItems = new ArrayList<>();
        List<MenuItem> neutralItems = new ArrayList<>();
        for(MenuItem menuItem : menuItems) {
            String result = feedbackService.calculateSentiment(mealTypeId);
            if(result.equals("Positive") ) {
                positiveItems.add(menuItem);
            } else if(result.equals("Negative") ) {
                negativeItems.add(menuItem);
            } else if(result.equals("Negative")) {
                neutralItems.add(menuItem);
            }
        }
        
        List<MenuItem> nextDayItems = new ArrayList<>();
        for(int i=0; i< (positiveItems.size() > noOfItems ? noOfItems : positiveItems.size()); i++) {
            nextDayItems.add(positiveItems.get(i));
        }
        
        if(nextDayItems.size() < noOfItems) {
            for(int i=0; i< (negativeItems.size() > noOfItems- nextDayItems.size() ? noOfItems - nextDayItems.size(): negativeItems.size()); i++) {
            nextDayItems.add(negativeItems.get(i));
            }
            
            if(nextDayItems.size() < noOfItems) {
                for(int i=0; i< (neutralItems.size() > noOfItems- nextDayItems.size() ? noOfItems - nextDayItems.size(): neutralItems.size()); i++) {
                    nextDayItems.add(neutralItems.get(i));
                } 
            } else {
                return nextDayItems;
            }
        } else {
            return nextDayItems;
        }
        
        return nextDayItems;
    }
}
