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
import models.User;
import services.Interfaces.IAuthService;
import services.Interfaces.IMenuItemService;
import services.Interfaces.IRoleService;
import services.RecommendationEngineService;
import services.RoleService;

public class ServerOperations {
    public static void handleOperations(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
        IMenuItemService menuItemService = new MenuItemService();
        RecommendationEngineService recommendationService = new RecommendationEngineService();
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
                    output.flush();
                    break;
                case "addMenuItem":
                    menuItemService.addMenuItem(input);
                    output.writeUTF("Item added succesfully");
                    output.flush();
                    break;
                case "viewAllItems":
                    input.readObject();
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
                    List<MenuItem> menuItems = menuItemService.getAllMenuItem();
                    output.writeObject(menuItems);
                    output.flush();
                    break;
                default:
                    break;
            }
        } catch(Exception ex) {
            output.writeUTF(ex.toString());
            output.flush();
        }
    }
}
