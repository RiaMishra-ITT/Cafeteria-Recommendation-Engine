/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import customexception.UnableToConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Profile;

/**
 *
 * @author ria.mishra
 */
public class ProfileRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public ProfileRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    public void updateProfile(Profile profile) throws SQLException {
        String sql = "INSERT INTO profiles (userId, dietary_preference, spice_level, cuisine_preference, sweet_tooth) " +
                         "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, profile.userId);
        pstmt.setString(2, profile.dietaryPreference);
        pstmt.setString(3, profile.spiceLevel);
        pstmt.setString(4, profile.cuisinePreference);
        pstmt.setString(5, profile.sweetTooth);
        pstmt.executeUpdate();
    }
    
    public Profile getProfileByUserId(int userId) throws SQLException {
        String sql = "Select * from profiles where userId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        Profile profile = new Profile();
        while(rs.next()) {
            profile = new Profile(rs.getInt("userId"),rs.getString("dietary_preference"),
                    rs.getString("spice_level"),rs.getString("cuisine_preference"),rs.getString("sweet_tooth"));
        }
        return profile;
    }
    
}
