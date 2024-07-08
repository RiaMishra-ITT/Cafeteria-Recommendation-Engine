/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import customexception.UserNotFoundException;
import database.UserDatabaseOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import models.User;
import services.Interfaces.IAuthService;


public class AuthService implements IAuthService {
    private final UserDatabaseOperation dbOperation;
    public String username = "";

    public AuthService() throws UnableToConnectDatabase {
        this.dbOperation = new UserDatabaseOperation();
    }
    
    @Override
    public User login(ObjectInputStream input) throws IOException, ClassNotFoundException, UserNotFoundException {
        String userId;
        userId = input.readObject().toString();
        String name = input.readObject().toString();
        User user = dbOperation.getUserByIdAndName(userId, name);
        username = user.name;
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        
        return user;
    }
}
