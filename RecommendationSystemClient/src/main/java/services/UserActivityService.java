/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import authentication.Authentication;
import com.mycompany.recommendationsystemclient.Client;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DiscardItemInfo;
import models.UserActivities;
import services.Interfaces.IUserActivityService;

/**
 *
 * @author ria.mishra
 */
public class UserActivityService implements IUserActivityService{
    Client client;
    public UserActivityService(Client client) {
        this.client = client;
    }
    
    @Override
    public void addUserActivity() {
        Authentication.activities.add("Logout");
        LocalDateTime currentDateTime = LocalDateTime.now();
        UserActivities activity = new UserActivities(0,Authentication.userId, String.join(", ", Authentication.activities),Authentication.logintime,currentDateTime.toString());
        client.sendRequest("recordActivity", activity);
        String response = (String) client.receiveResponse();
        System.out.println("Server Response: " + response);
    }
    
}
