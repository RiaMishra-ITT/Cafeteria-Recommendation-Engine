/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recommendationsystemserver;

import services.AuthService;
import services.MenuItemService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import models.AdminQuestions;
import models.DiscardItemInfo;
import models.MenuItem;
import models.Notification;
import models.RolledOutItem;
import models.User;
import services.AdminQuestionsService;
import services.ChefService;
import services.DiscardItemService;
import services.FeedbackService;
import services.Interfaces.IAdminQuestionsService;
import services.Interfaces.IAuthService;
import services.Interfaces.IChefService;
import services.Interfaces.IDiscardItemService;
import services.Interfaces.IFeedbackService;
import services.Interfaces.IMenuItemService;
import services.Interfaces.IProfileService;
import services.Interfaces.IRoleService;
import services.Interfaces.IUserActivityService;
import services.Interfaces.IUserNotificationService;
import services.Interfaces.IUserResponseService;
import services.Interfaces.IUserVoteService;
import services.ProfileService;
import services.RoleService;
import services.UserActivityService;
import services.UserNotificationService;
import services.UserResponseService;
import services.UserVoteService;

public class ServerOperations {
    public static void handleOperations(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
        
        try
        {
            IMenuItemService menuItemService = new MenuItemService();
            IFeedbackService feedbackService = new FeedbackService();
            IChefService chefService = new ChefService();
            IUserNotificationService userNotificationService = new UserNotificationService();
            IDiscardItemService discardItemService = new DiscardItemService();
            IUserActivityService activityService = new UserActivityService();
            IAdminQuestionsService questionsService = new AdminQuestionsService();
            IProfileService profileService = new ProfileService();
            IUserResponseService userResponseService = new UserResponseService();
            IUserVoteService userVoteService = new UserVoteService();
            String action = input.readUTF();
            switch (action) {
                case "login":
                    IAuthService authService = new AuthService();
                    User user = authService.login(input);
                    IRoleService roleService = new RoleService();
                    String role = roleService.getRoleById(user.roleId);
                    output.writeUTF(role);
                    String userId = Integer.toString(user.userId);
                    output.writeUTF(userId);
                    output.flush();
                    break;
                case "addMenuItem":
                    menuItemService.addMenuItem(input);
                    output.writeUTF("Item added succesfully");
                    output.flush();
                    break;
                case "viewAllItems":
                    List<MenuItem> menuItems = menuItemService.getAllMenuItem();
                    output.writeObject(menuItems);
                    output.flush();
                    break;
                case "updateMenuItem":
                    menuItemService.updateMenuItem(input);
                    output.writeUTF("Item updated succesfully");
                    output.flush();
                    break;
                case "deleteMenuItem":
                    menuItemService.removeMenuItem(input);
                    output.writeUTF("Item deleted succesfully");
                    output.flush();
                    break;
                case "rollOutItem":
                    List<MenuItem> nextDayItems = chefService.getFoodItemForNextDay(input);
                    output.writeObject(nextDayItems);
                    output.flush();
                    break;
                case "submitFeedback":
                    feedbackService.submitFeedback(input);
                    output.writeUTF("Feedback submitted succesfully");
                    output.flush();
                    break;
                case "addUserNotification":
                    userNotificationService.addNotification(input);
                    output.writeUTF("Item rolled out succesfully");
                    output.flush();
                    break;
                case "viewRolledOutItem":
                    List<RolledOutItem> items =  userNotificationService.getRolledOutItemNotifications(input);
                    System.out.println(items.size());
                    output.writeObject(items);
                    output.flush();
                    break;
                case "viewDiscardItems":
                    List<DiscardItemInfo> discardItems =  discardItemService.getDiscardedItemsWithRatings();
                    output.writeObject(discardItems);
                    output.flush();
                    break;
                case "recordActivity":
                    activityService.addUserActivity(input);
                    output.writeUTF("Activity recoreded succesfully");
                    output.flush();
                    break;
                case "usernotification":
                    List<Notification> notifications =  userNotificationService.getUserNotifications(input);
                    output.writeObject(notifications);
                    output.flush();
                    break;
                case "deleteMenuItems":
                    menuItemService.removeMenuItems(input);
                    output.writeUTF("Items removed succesfully");
                    output.flush();
                    break;
                case "submitquestions":
                    questionsService.addQuestions(input);
                    output.writeUTF("Question added succesfully");
                    output.flush();
                    break;
                case "updateProfile":
                    profileService.updateProfile(input);
                    output.writeUTF("Profile updated succesfully");
                    output.flush();
                    break;
                case "viewquestions":
                    int notificationId;
                    notificationId = Integer.parseInt(input.readObject().toString());
                    int menuItemId = userNotificationService.getMenuItemIdByNotification(notificationId);
                    List<AdminQuestions> questions =  questionsService.getQuestionsByItemId(menuItemId);
                    output.writeObject(questions);
                    output.flush();
                    break;
                case "addResponse":
                    userResponseService.addResponse(input);
                    output.writeUTF("Response added succesfully");
                    output.flush();
                    break;
                case "addVote":
                    userVoteService.addUserVote(input);
                    output.writeUTF("Vote added succesfully");
                    output.flush();
                    break;
                default:
                    break;
            }
        } catch (ClassNotFoundException ex) {
            output.writeUTF("Unable to load class: " + ex.getMessage());
            output.flush();
        } catch (Exception ex) {
            output.writeUTF("An error occurred: " + ex.getMessage());
            output.flush();
        }
    }
}
