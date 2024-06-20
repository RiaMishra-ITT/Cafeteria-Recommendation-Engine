/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UserNotFoundException;
import database.Database;
import java.io.IOException;
import java.io.ObjectInputStream;
import models.User;
import services.Interfaces.IAuthService;


public class AuthService implements IAuthService {
    Database database = new Database();
    @Override
    public User Login(ObjectInputStream input) throws IOException, ClassNotFoundException, UserNotFoundException {
        String userId;
        userId = input.readObject().toString();
        String name = input.readObject().toString();
        User user = database.getUserByIdAndName(userId, name);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        
        return user;
    }
}
