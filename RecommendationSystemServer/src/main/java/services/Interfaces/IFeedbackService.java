/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.Feedback;

/**
 *
 * @author ria.mishra
 */
public interface IFeedbackService {
    List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException;
    void submitFeedback(ObjectInputStream input) throws SQLException, IOException, ClassNotFoundException;
}
