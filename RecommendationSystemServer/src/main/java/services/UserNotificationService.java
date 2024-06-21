/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import models.Feedback;
import models.MenuItem;
import models.RolledOutItem;
import models.User;
import models.UserNotifcation;
import repositories.Interfaces.IMenuItemRepository;
import repositories.Interfaces.IUserNotificationRepository;
import repositories.Interfaces.IUserRepository;
import repositories.MenuItemRepository;
import repositories.UserNotificationRepository;
import repositories.UserRepository;
import services.Interfaces.IFeedbackService;
import services.Interfaces.IUserNotificationService;

/**
 *
 * @author ria.mishra
 */
public class UserNotificationService implements IUserNotificationService{
    private IUserNotificationRepository userNotificationRepository = new UserNotificationRepository();
    private IUserRepository userRepository = new UserRepository();
    private IFeedbackService feedbackService = new FeedbackService();
    private IMenuItemRepository menuItemRepository = new MenuItemRepository();
    
    @Override
    public void addNotification(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException {
        String itemIds = input.readObject().toString();
        String[] itemIdArray = itemIds.split(",");
        List<User> users = userRepository.getUserByRoleId(3);
        List<UserNotifcation> userNotifications = new ArrayList<>();
        for(int i =0 ;i < itemIdArray.length; i++) {
            for(int j =0; j < users.size(); j ++) {
                LocalDateTime currentTime = LocalDateTime.now();
                UserNotifcation userNotification = new UserNotifcation(0,8,users.get(j).userId,currentTime.toString(),Integer.parseInt(itemIdArray[i]));
                userNotifications.add(userNotification);
            }   
        }
        System.out.println(userNotifications.size());
        userNotificationRepository.addUserNotification(userNotifications);
    }
    
    @Override
    public List<RolledOutItem> getRolledOutItemNotifications() throws IOException, ClassNotFoundException, SQLException{
        List<UserNotifcation> userNotifications =  userNotificationRepository.getRolledOutItemNotification();
        System.out.println(userNotifications.size());
        List<RolledOutItem> rolledOutItems = new ArrayList<>();
        for(UserNotifcation notification : userNotifications) {
            String sentiment = feedbackService.calculateSentiment(notification.menuItemId);
            MenuItem menuItem = menuItemRepository.getMenuItemById(notification.menuItemId);
            List<Feedback> feedbacks = feedbackService.getFeedbackByItemId(notification.menuItemId);
            String totalRating = "";
            for(Feedback feedback : feedbacks) {
                totalRating = totalRating + feedback.rating;
            }
            
            int avgRating = totalRating.length() / 5;
            String str = "";
            String avg = str.repeat(avgRating);
            System.out.println(avg);
            RolledOutItem rolledOutItem = new RolledOutItem(menuItem.menuItemId, menuItem.itemName,menuItem.price,menuItem.availbilityStatus
            ,menuItem.mealTypeId,avg,sentiment);
            rolledOutItems.add(rolledOutItem);
        }
        
        System.out.println(rolledOutItems.size());
        return rolledOutItems;
    }
}
