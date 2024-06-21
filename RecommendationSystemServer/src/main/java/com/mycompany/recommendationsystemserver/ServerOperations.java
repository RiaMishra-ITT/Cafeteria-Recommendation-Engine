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
import models.MenuItem;
import models.RolledOutItem;
import models.User;
import services.ChefService;
import services.FeedbackService;
import services.Interfaces.IAuthService;
import services.Interfaces.IFeedbackService;
import services.Interfaces.IMenuItemService;
import services.Interfaces.IRoleService;
import services.Interfaces.IUserNotificationService;
import services.RecommendationEngineService;
import services.RoleService;
import services.UserNotificationService;

public class ServerOperations {
    public static void handleOperations(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
        IMenuItemService menuItemService = new MenuItemService();
        IFeedbackService feedbackService = new FeedbackService();
        ChefService chefService = new ChefService();
        IUserNotificationService userNotificationService = new UserNotificationService();
        try
        {
            String action = input.readUTF();
            System.out.println(action);
            switch (action) {
                case "login":
                    IAuthService authService = new AuthService();
                    User user = authService.Login(input);
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
                case "viewRolledOutItem":
                    List<RolledOutItem> items =  userNotificationService.getRolledOutItemNotifications();
                    System.out.println(items.size());
                    output.writeObject(items);
                    output.flush();
                default:
                    break;
            }
        } catch(Exception ex) {
            output.writeUTF(ex.toString());
            output.flush();
        }
    }
}
