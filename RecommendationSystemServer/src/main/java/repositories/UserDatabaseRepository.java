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
import java.util.ArrayList;
import java.util.List;
import models.User;
import models.UserActivities;

/**
 *
 * @author ria.mishra
 */
public class UserDatabaseRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public UserDatabaseRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public User getUserByIdAndName(String userId, String name) throws SQLException{
        String sql = "SELECT * " +
                     "FROM users u "+
                     "WHERE u.UserID = ? AND u.Name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userId);
        pstmt.setString(2, name);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = Integer.parseInt(rs.getString("RoleId"));
            return new User(id, rs.getString("Name"), rs.getInt("RoleId"));
        }
        return null;
    }
    
    public List<User> getUserByRoleId(int roleId) throws SQLException {
        String sql = "Select * from Users where roleId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roleId);
        ResultSet rs = pstmt.executeQuery();
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            users.add(new User(rs.getInt("UserID"), rs.getString("Name"), rs.getInt("RoleId")));
        }
        
        return users;
    }
    
    public void addUserActivities(UserActivities activity) throws SQLException {
        String sql = "INSERT INTO useractivity (userId, activities, logintime, logouttime) VALUES (?, ?, ?, ?)";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, activity.userId);
        pstmt.setString(2, activity.activities);
        pstmt.setString(3, activity.logintime);
        pstmt.setString(4, activity.logouttime);
            
        pstmt.executeUpdate();
    }
}
