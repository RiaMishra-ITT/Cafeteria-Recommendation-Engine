/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Repositories.UserResponseRepository;
import customexception.UnableToConnectDatabase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserResponse;
import services.Interfaces.IUserResponseService;

/**
 *
 * @author ria.mishra
 */
public class UserResponseService implements IUserResponseService {
    private final UserResponseRepository userResponseRepository;

    public UserResponseService() throws UnableToConnectDatabase {
        this.userResponseRepository = new UserResponseRepository();
    }
    
    @Override
    public void addResponse(ObjectInputStream input) throws IOException {
        try {
            List<UserResponse> responses = (List<UserResponse>) input.readObject();
            this.userResponseRepository.addResponse(responses);
        } catch (SQLException ex) {
            throw new IOException("Failed to add response");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
}
