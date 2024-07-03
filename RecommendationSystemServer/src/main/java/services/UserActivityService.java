/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import models.UserActivities;
import repositories.Interfaces.IUserActivityRepository;
import repositories.UserActivityRepository;
import services.Interfaces.IUserActivityService;

/**
 *
 * @author ria.mishra
 */
public class UserActivityService implements IUserActivityService{
    private final IUserActivityRepository repository = new UserActivityRepository();
    @Override
    public void addUserActivity(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException {
        UserActivities userActivity = (UserActivities) input.readObject();
        repository.addUserActivity(userActivity);
    }
    
}
