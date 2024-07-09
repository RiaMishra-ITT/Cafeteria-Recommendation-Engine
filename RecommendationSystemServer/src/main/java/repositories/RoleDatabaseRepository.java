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
import models.Role;

/**
 *
 * @author ria.mishra
 */
public class RoleDatabaseRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public RoleDatabaseRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
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
}
