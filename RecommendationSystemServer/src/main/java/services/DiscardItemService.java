/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.DiscardItemInfo;
import models.Feedback;
import repositories.DiscardItemRepository;
import repositories.Interfaces.IDiscardItemRepository;
import services.Interfaces.IDiscardItemService;
import services.Interfaces.IFeedbackService;


public class DiscardItemService implements IDiscardItemService {
    private IDiscardItemRepository discardItemRepository = new DiscardItemRepository();
    private IFeedbackService feedbackService = new FeedbackService();
    private SemanticAnalysisService semanticAnalysisService = new SemanticAnalysisService();
    
    @Override
    public void addDiscardItem(List<Integer> menuItemIds) throws IOException, ClassNotFoundException, SQLException {
        discardItemRepository.addDiscardItems(menuItemIds);
    }

    @Override
    public List<DiscardItemInfo> getDiscardedItemsWithRatings() throws SQLException {
        List<DiscardItemInfo> discardItems = discardItemRepository.getDiscardedItemsWithRatings();
        for(int i=0;i<discardItems.size();i++) {
            Set<String> specificSentiments = new HashSet<>();
            List<Feedback> feedbacks = feedbackService.getFeedbackByItemId(discardItems.get(i).menuItemId);
            double rating = 0;
            for(int j = 0; j< feedbacks.size();j++) {
                String sentiment = semanticAnalysisService.calculateAverageSentiment(feedbacks.get(j).comment);
                if (!sentiment.equals("Neutral")) {
                    specificSentiments.add(sentiment);
                }
                
                rating = rating + feedbacks.get(j).rating.length();
            }
            discardItems.get(i).sentiment = String.join(", ", specificSentiments);
            discardItems.get(i).rating = Double.toString(rating / 5);
        }
        
        return discardItems;
    }
    
}
