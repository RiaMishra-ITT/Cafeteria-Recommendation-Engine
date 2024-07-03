/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import models.UserActivities;
import repositories.Interfaces.IUserActivityRepository;

/**
 *
 * @author ria.mishra
 */
public class UserActivityRepository implements IUserActivityRepository{
    private final Database database = new Database();
    @Override
    public void addUserActivity(UserActivities userActivity) throws SQLException {
        database.addUserActivities(userActivity);
    }
    
}
