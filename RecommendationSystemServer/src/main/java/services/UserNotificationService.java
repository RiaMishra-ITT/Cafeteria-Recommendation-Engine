/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.FailedToAddNotification;
import customexception.UnableToConnectDatabase;
import database.MenuItemDatabaseOperation;
import database.NotificationDatabaseOperation;
import database.UserDatabaseOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import models.Feedback;
import models.MenuItem;
import models.Notification;
import models.RolledOutItem;
import models.User;
import models.UserNotifcation;
import services.Interfaces.IFeedbackService;
import services.Interfaces.INotificationService;
import services.Interfaces.IUserNotificationService;

/**
 *
 * @author ria.mishra
 */
public class UserNotificationService implements IUserNotificationService{
    private final NotificationDatabaseOperation notificationDbOperation;
    private final UserDatabaseOperation userDboperation;
    private final IFeedbackService feedbackService = new FeedbackService();
    private final MenuItemDatabaseOperation menuItemDbOperation;
    private final INotificationService notificationService = new NotificationService();

    public UserNotificationService() throws UnableToConnectDatabase {
        this.userDboperation = new UserDatabaseOperation();
        this.menuItemDbOperation = new MenuItemDatabaseOperation();
        this.notificationDbOperation = new NotificationDatabaseOperation();
    }
    
    @Override
    public void addNotification(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException,FailedToAddNotification {
        try {
            String itemIds = input.readObject().toString();
            String[] itemIdArray = itemIds.split(",");
            Notification notification = new Notification(0,"Menu rolled out",1,LocalDateTime.now().toString());
            int notificationId = notificationService.addNotification(notification);
            List<User> users = userDboperation.getUserByRoleId(3);
            List<UserNotifcation> userNotifications = new ArrayList<>();
            for (String itemIdArray1 : itemIdArray) {
                for (int j = 0; j < users.size(); j ++) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    UserNotifcation userNotification = new UserNotifcation(0, notificationId, users.get(j).userId, currentTime.toString(), Integer.parseInt(itemIdArray1));
                    userNotifications.add(userNotification);
                }   
            }
            System.out.println(userNotifications.size());
            notificationDbOperation.addUserNotification(userNotifications);
        } catch (Exception ex) {
            throw new FailedToAddNotification("Failed to add notification");
        }
    }
    
    @Override
    public List<RolledOutItem> getRolledOutItemNotifications() throws IOException, ClassNotFoundException, SQLException{
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<UserNotifcation> userNotifications =  notificationDbOperation.getRolledOutItemNotifications(formattedDate);
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
            System.out.println(avg);
            RolledOutItem rolledOutItem = new RolledOutItem(menuItem.menuItemId, menuItem.itemName,menuItem.price,menuItem.availbilityStatus
            ,menuItem.mealTypeId,avg,sentiment);
            rolledOutItems.add(rolledOutItem);
        }
        

        return rolledOutItems;
    }

    @Override
    public List<Notification> getUserNotifications(ObjectInputStream input) throws SQLException,IOException,ClassNotFoundException {
        int userId;
        userId = Integer.parseInt(input.readObject().toString());
        return notificationDbOperation.getUserNotifications(userId);
    }
}
