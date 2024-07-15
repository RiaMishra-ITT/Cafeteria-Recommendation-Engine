/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.MenuItem;
import services.Interfaces.IMenuItemService;

/**
 *
 * @author ria.mishra
 */
public class RecommendationEngineService {
    private final FeedbackService feedbackService;
    private final IMenuItemService menuItemService;

    public RecommendationEngineService() throws UnableToConnectDatabase {
        this.menuItemService = new MenuItemService();
        this.feedbackService = new FeedbackService();
    }
    public List<MenuItem> getFoodItemForNextDay(int mealTypeId, int noOfItems) throws IOException{
        try {
          List<MenuItem> menuItems = menuItemService.getItemsByMealType(mealTypeId);
            List<MenuItem> positiveItems = new ArrayList<>();
            List<MenuItem> negativeItems = new ArrayList<>();
            List<MenuItem> neutralItems = new ArrayList<>();
            for(MenuItem menuItem : menuItems) {
                String result = feedbackService.calculateSentiment(menuItem.menuItemId);
                switch (result) {
                    case "Positive":
                        positiveItems.add(menuItem);
                        break;
                    case "Negative":
                        negativeItems.add(menuItem);
                        break;
                    case "Neutral":
                        neutralItems.add(menuItem);
                        break;
                    default:
                        positiveItems.add(menuItem);
                        break;
                }
            }

            List<MenuItem> nextDayItems = new ArrayList<>();
            int nextDayItemsSize = 0;
            for(int i=0; i< (positiveItems.size() > noOfItems ? noOfItems : positiveItems.size()); i++) {
                nextDayItems.add(positiveItems.get(i));
            }

            nextDayItemsSize = nextDayItems.size();

            if(nextDayItems.size() < noOfItems) {
                for(int i=0; i< (negativeItems.size() > noOfItems- nextDayItemsSize ? noOfItems - nextDayItemsSize: negativeItems.size()); i++) {
                    nextDayItems.add(negativeItems.get(i));
                }

                nextDayItemsSize = nextDayItems.size();

                if(nextDayItems.size() < noOfItems) {
                    for(int i=0; i< (neutralItems.size() > noOfItems- nextDayItemsSize ? noOfItems - nextDayItemsSize: neutralItems.size()); i++) {
                        nextDayItems.add(neutralItems.get(i));
                    } 
                } else {
                    return nextDayItems;
                }
            } else {
                return nextDayItems;
            }

            return nextDayItems;  
        }  catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        }
    }
}
