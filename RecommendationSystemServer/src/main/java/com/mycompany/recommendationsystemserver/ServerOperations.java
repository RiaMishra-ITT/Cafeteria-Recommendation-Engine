/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.recommendationsystemserver;

import services.AuthService;
import services.MenuItemService;
import java.io.IOException;
import java.io.ObjectInputStream;
import models.User;
import services.Interfaces.IAuthService;
import services.Interfaces.IRoleService;
import services.RoleService;

public class ServerOperations {
    public static String handleOperations(ObjectInputStream input) throws IOException, ClassNotFoundException {
        try
        {
            String action = input.readUTF();
            switch (action) {
                case "login":
                    IAuthService authService = new AuthService();
                    User user = authService.Login(input);
                    IRoleService roleService = new RoleService();
                    String role = roleService.getRoleById(user.roleId);
                    return role;
                
                default:
                    return "Invalid operation";
            }
            //return null;
        } catch(Exception ex) {
            return ex.toString();
        }
    }
}
