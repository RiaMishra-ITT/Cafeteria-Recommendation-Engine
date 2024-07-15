/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import Repositories.DiscardItemRepository;
import Repositories.FeedbackDatabaseRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.Feedback;
import services.Interfaces.IFeedbackService;


public class FeedbackService implements IFeedbackService{
    private final SemanticAnalysisService semanticAnalysisService = new SemanticAnalysisService();
    private final FeedbackDatabaseRepository feedbackRepository;
    private final DiscardItemRepository discardItemDbOperation;

    public FeedbackService() throws UnableToConnectDatabase {
        this.discardItemDbOperation = new DiscardItemRepository();
        this.feedbackRepository = new FeedbackDatabaseRepository();
    }
    
    @Override
    public List<Feedback> getFeedbackByItemId(int menuItemId) throws IOException {
        try {
           List<Feedback> feedbackList = feedbackRepository.getFeedbackByItemId(menuItemId);
            return feedbackList; 
        } catch (SQLException ex) {
            throw new IOException("Failed to get feedback for specific item");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
    @Override
    public void submitFeedback(ObjectInputStream input) throws  IOException  {
        try {
            Feedback feedback = (Feedback) input.readObject();
            feedbackRepository.submitFeedback(feedback);
            List<Integer> discardIds = feedbackRepository.getLowRatingMenuItemIds();

            discardItemDbOperation.addDiscardItems(discardIds);
        } catch (SQLException ex) {
            throw new IOException("Failed to submit feedback");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
        
    }
    
    @Override
    public String calculateSentiment(int menuItemId) throws IOException{
        try {
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
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
        
    }
}
