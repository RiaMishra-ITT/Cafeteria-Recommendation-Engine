/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import java.util.List;
import models.Feedback;

/**
 *
 * @author ria.mishra
 */
public interface IFeedbackRepository {
    List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException;
}
