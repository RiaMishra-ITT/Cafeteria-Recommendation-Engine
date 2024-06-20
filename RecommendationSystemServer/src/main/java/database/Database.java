/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.MenuItem;
import models.Role;
import models.User;


public class Database {
    String url = "jdbc:mysql://localhost:3306/recommendationsystem";
    String username = "root";
    String password = "root";
    Connection conn = null;
    Statement stmt = null;
    
    public Database() {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        }  catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public User getUserByIdAndName(String userId, String name) {
        String sql = "SELECT * " +
                     "FROM users u "+
                     "WHERE u.UserID = ? AND u.Name = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = Integer.parseInt(rs.getString("RoleId"));
                return new User(id, rs.getString("Name"), rs.getInt("RoleId"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }
    
    public Role getRoleById(int roleId) throws SQLException {
        String sql = "Select * from Roles where RoleID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roleId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Role(rs.getInt("roleID"), rs.getString("Role"));
        }
        
        return null;
    }
    
    public void addMenuItem(MenuItem menuItem) throws SQLException {
        String sql = "INSERT INTO menu_items (ItemName, Price, AvailbilityStatus,ItemTypeId) VALUES (?, ?, ?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, menuItem.itemName);
        pstmt.setDouble(2, menuItem.price);
        pstmt.setString(3, menuItem.availablityStatus);
        pstmt.setInt(4,menuItem.itemTypeId);

        pstmt.executeUpdate();
    }
    
    public void updateMenuItem() {
        
    }
    
    public void getItemsByAvailabilty() {
        
    }
    
    public void deleteItem() {
        
    }
}
