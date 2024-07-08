/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import database.DiscardItemDatabaseOperation;
import database.FeedbackDatabaseOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.Feedback;
import services.Interfaces.IFeedbackService;


public class FeedbackService implements IFeedbackService{
    private final SemanticAnalysisService semanticAnalysisService = new SemanticAnalysisService();
    private final FeedbackDatabaseOperation feedbackDbOperation;
    private final DiscardItemDatabaseOperation discardItemDbOperation;

    public FeedbackService() throws UnableToConnectDatabase {
        this.discardItemDbOperation = new DiscardItemDatabaseOperation();
        this.feedbackDbOperation = new FeedbackDatabaseOperation();
    }
    
    @Override
    public List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException {
        List<Feedback> feedbackList = feedbackDbOperation.getFeedbackByItemId(menuItemId);
        return feedbackList;
    }
    
    @Override
    public void submitFeedback(ObjectInputStream input) throws SQLException, IOException, ClassNotFoundException  {
        Feedback feedback = (Feedback) input.readObject();
        feedbackDbOperation.submitFeedback(feedback);
        List<Integer> discardIds = feedbackDbOperation.getLowRatingMenuItemIds();
        
        discardItemDbOperation.addDiscardItems(discardIds);
    }
    
    @Override
    public String calculateSentiment(int menuItemId) throws SQLException{
        List<Feedback> feedbackList = this.getFeedbackByItemId(menuItemId);
        int positiveCount = 0; 
        int negativeCount = 0; 
        int neutralCount = 0; 
        for (Feedback feedback : feedbackList) {
            String sentiment = semanticAnalysisService.analyzeSentiment(feedback.comment); 
            switch (sentiment) {
                case "Positive": 
                    positiveCount++; 
                    break; 
                case "Negative": 
                    negativeCount++; 
                    break; 
                case "Neutral": 
                    neutralCount++; 
                    break; 
            }
        }
        int totalCount = positiveCount + negativeCount + neutralCount; 
        if (totalCount == 0) 
            return "Neutral"; 
        if (positiveCount > negativeCount && positiveCount > neutralCount) 
            return "Positive"; 
        if (negativeCount > positiveCount && negativeCount > neutralCount) 
            return "Negative"; return "Neutral";
    }
}
