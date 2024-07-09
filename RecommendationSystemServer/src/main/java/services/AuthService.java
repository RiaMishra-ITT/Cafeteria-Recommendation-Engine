/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import customexception.UnableToConnectDatabase;
import customexception.UserNotFoundException;
import Repositories.UserDatabaseRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import models.User;
import services.Interfaces.IAuthService;


public class AuthService implements IAuthService {
    private final UserDatabaseRepository dbOperation;
    public String username = "";

    public AuthService() throws UnableToConnectDatabase {
        this.dbOperation = new UserDatabaseRepository();
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
