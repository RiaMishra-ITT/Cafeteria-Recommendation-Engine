/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import customexception.UserNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import models.User;

/**
 *
 * @author ria.mishra
 */
public interface IAuthService {
    public User Login(ObjectInputStream input) throws IOException, ClassNotFoundException, UserNotFoundException;
}
