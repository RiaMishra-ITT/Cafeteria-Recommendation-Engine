/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import models.UserActivities;

/**
 *
 * @author ria.mishra
 */
public interface IUserActivityRepository {
    public void addUserActivity(UserActivities userActivity) throws SQLException;
}
