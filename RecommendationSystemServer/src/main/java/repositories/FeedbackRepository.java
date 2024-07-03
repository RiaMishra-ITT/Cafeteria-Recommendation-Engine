/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import java.util.List;
import models.Feedback;
import repositories.Interfaces.IFeedbackRepository;

/**
 *
 * @author ria.mishra
 */
public class FeedbackRepository implements IFeedbackRepository {
   private Database database = new Database();
   public List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException {
       return database.getFeedbackByItemId(menuItemId);
   } 
   
   @Override
   public int submitFeedback(Feedback feedback) throws SQLException {
       return database.submitFeedback(feedback);
   }

    @Override
    public List<Integer> getLowRatingMenuItemIds() throws SQLException {
        return database.getLowRatingMenuItemIds();
    }
}
