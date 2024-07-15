/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import models.Profile;

/**
 *
 * @author ria.mishra
 */
public interface IProfileService {
    public void updateProfile(ObjectInputStream input) throws IOException;
    public Profile getProfileByUserID(int userId) throws IOException;
}
