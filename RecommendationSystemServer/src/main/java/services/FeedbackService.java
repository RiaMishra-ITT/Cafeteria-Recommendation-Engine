/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;
import models.Feedback;
import repositories.FeedbackRepository;
import repositories.Interfaces.IFeedbackRepository;


public class FeedbackService {
    private SemanticAnalysisService semanticAnalysisService = new SemanticAnalysisService();
    private IFeedbackRepository feedbackRepository = new FeedbackRepository();
    List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException {
        List<Feedback> feedbackList = feedbackRepository.getFeedbackByItemId(menuItemId);
        return feedbackList;
    }
    
    String calculateSentiment(int menuItemId) throws SQLException{
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
