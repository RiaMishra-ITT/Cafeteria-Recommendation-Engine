/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Repositories.ProfileRepository;
import customexception.UnableToConnectDatabase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Profile;
import services.Interfaces.IProfileService;

/**
 *
 * @author ria.mishra
 */
public class ProfileService implements IProfileService{
    private final ProfileRepository profileRepository;

    public ProfileService() throws UnableToConnectDatabase {
        this.profileRepository = new ProfileRepository();
    }
    @Override
    public void updateProfile(ObjectInputStream input) throws IOException{
       Profile profile;
        try {
            profile = (Profile) input.readObject();
            profileRepository.updateProfile(profile);
        } catch (SQLException ex) {
            throw new IOException("Failed to update profile");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
       
       
    }

    @Override
    public Profile getProfileByUserID(int userId) throws IOException{
        try {
            return this.profileRepository.getProfileByUserId(userId);
        } catch (SQLException ex) {
            throw new IOException("Failed to get user profile");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        } 
    }
    
}
