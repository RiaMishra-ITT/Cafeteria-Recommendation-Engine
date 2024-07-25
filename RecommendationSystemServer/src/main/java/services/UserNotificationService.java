/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import Repositories.MenuItemRepository;
import Repositories.NotificationRepository;
import Repositories.UserDatabaseRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import models.Feedback;
import models.MenuItem;
import models.Notification;
import models.Profile;
import models.RolledOutItem;
import models.User;
import models.UserNotifcation;
import services.Interfaces.IFeedbackService;
import services.Interfaces.INotificationService;
import services.Interfaces.IProfileService;
import services.Interfaces.IUserNotificationService;

/**
 *
 * @author ria.mishra
 */
public class UserNotificationService implements IUserNotificationService{
    private final NotificationRepository notificationRepository;
    private final UserDatabaseRepository userRepostiroy;
    private final IFeedbackService feedbackService = new FeedbackService();
    private final MenuItemRepository menuItemDbOperation;
    private final INotificationService notificationService = new NotificationService();
    private final IProfileService profileService = new ProfileService();

    public UserNotificationService() throws UnableToConnectDatabase {
        this.userRepostiroy = new UserDatabaseRepository();
        this.menuItemDbOperation = new MenuItemRepository();
        this.notificationRepository = new NotificationRepository();
    }
    
    @Override
    public void addNotification(ObjectInputStream input) throws IOException {
        try {
            String itemIds = input.readObject().toString();
            String[] itemIdArray = itemIds.split(",");
            Notification notification = new Notification(0,"Menu rolled out",1,LocalDateTime.now().toString());
            int notificationId = notificationService.addNotification(notification);
            List<User> users = userRepostiroy.getUserByRoleId(3);
            List<UserNotifcation> userNotifications = new ArrayList<>();
            for (String itemIdArray1 : itemIdArray) {
                for (int j = 0; j < users.size(); j ++) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    UserNotifcation userNotification = new UserNotifcation(0, notificationId, users.get(j).userId, currentTime.toString(), Integer.parseInt(itemIdArray1));
                    userNotifications.add(userNotification);
                }   
            }
            System.out.println(userNotifications.size());
            notificationRepository.addUserNotification(userNotifications);
        } catch (SQLException ex) {
            throw new IOException("Failed to add notification");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
    @Override
    public List<RolledOutItem> getRolledOutItemNotifications(ObjectInputStream input) throws IOException{
        try {
            int userId;
            userId = Integer.parseInt(input.readObject().toString());
            LocalDate today = LocalDate.now();
            String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<UserNotifcation> userNotifications =  notificationRepository.getRolledOutItemNotifications(formattedDate);
            System.out.println(userNotifications.size());
            List<RolledOutItem> rolledOutItems = new ArrayList<>();
            for(UserNotifcation notification : userNotifications) {
                String sentiment = feedbackService.calculateSentiment(notification.menuItemId);
                MenuItem menuItem = menuItemDbOperation.getMenuItemById(notification.menuItemId);
                List<Feedback> feedbacks = feedbackService.getFeedbackByItemId(notification.menuItemId);
                String totalRating = "";
                for(Feedback feedback : feedbacks) {
                    totalRating = totalRating + feedback.rating;
                }

                int avgRating = totalRating.length() / 5;
                String str = "";
                String avg = str.repeat(avgRating);
                RolledOutItem rolledOutItem = new RolledOutItem(menuItem.menuItemId, menuItem.itemName,menuItem.price,menuItem.availbilityStatus
                ,menuItem.mealTypeId,avg,sentiment,menuItem.itemType,menuItem.spiceType,menuItem.cuisineType);
                rolledOutItems.add(rolledOutItem);
            }
            Profile profile = profileService.getProfileByUserID(userId);
            rolledOutItems.sort((item1, item2) -> {
            int score1 = calculatePreferenceScore(item1, profile);
            int score2 = calculatePreferenceScore(item2, profile);
            return Integer.compare(score2, score1);
            });

            return rolledOutItems;
        } catch (SQLException ex) {
            throw new IOException("Failed to get rolled out items");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
    private int calculatePreferenceScore(RolledOutItem item, Profile profile) {
    int score = 0;

    if (profile.dietaryPreference.equalsIgnoreCase(item.itemType)) {
        score += 10;
    }

    if (profile.spiceLevel.equalsIgnoreCase(item.spiceType)) {
        score += 5;
    } else if (profile.spiceLevel.equalsIgnoreCase("High") && item.spiceType.equalsIgnoreCase("Medium")) {
        score += 3;
    } else if (profile.spiceLevel.equalsIgnoreCase("High") && item.spiceType.equalsIgnoreCase("Low")) {
        score += 1; 
    } else if (profile.spiceLevel.equalsIgnoreCase("Medium") && item.spiceType.equalsIgnoreCase("High")) {
        score += 3;
    } else if (profile.spiceLevel.equalsIgnoreCase("Medium") && item.spiceType.equalsIgnoreCase("Low")) {
        score += 3;
    } else if (profile.spiceLevel.equalsIgnoreCase("Low") && item.spiceType.equalsIgnoreCase("High")) {
        score += 1;
    } else if (profile.spiceLevel.equalsIgnoreCase("Low") && item.spiceType.equalsIgnoreCase("Medium")) {
        score += 3;
    }

    if (profile.cuisinePreference.equalsIgnoreCase(item.cuisineType)) {
        score += 3;
    }

    if (profile.sweetTooth.equalsIgnoreCase("Yes") && item.itemName.toLowerCase().contains("sweet")) {
        score += 2;
    }

    return score;
}


    @Override
    public List<Notification> getUserNotifications(ObjectInputStream input) throws IOException {
        try {
          int userId;
            userId = Integer.parseInt(input.readObject().toString());
            return notificationRepository.getUserNotifications(userId);  
        } catch (SQLException ex) {
            throw new IOException("Failed to get user notifications");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
    @Override
    public void sendDetailFeedbackNotification(Set<Integer> menuItemId) throws IOException {
        try {
            Notification notification = new Notification(0,"Provide Feedback",4,LocalDateTime.now().toString());
            int notificationId = notificationService.addNotification(notification);
            List<User> users = userRepostiroy.getUserByRoleId(3);
            List<UserNotifcation> userNotifications = new ArrayList<>();
            for (int itemId : menuItemId) {
                for (int j = 0; j < users.size(); j ++) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    UserNotifcation userNotification = new UserNotifcation(0, notificationId, users.get(j).userId, currentTime.toString(), itemId);
                    userNotifications.add(userNotification);
                }   
            }
            System.out.println(userNotifications.size());
            notificationRepository.addUserNotification(userNotifications);
        } catch (SQLException ex) {
            throw new IOException("Failed to send notification");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
    public int getMenuItemIdByNotification(int notificationId) throws IOException {
        try {
            return this.notificationRepository.getMenuItemIdByNotification(notificationId);
        } catch (SQLException ex) {
            throw new IOException("Failed to get menu item notification");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        }
        
    }
}
