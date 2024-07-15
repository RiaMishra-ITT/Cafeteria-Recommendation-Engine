/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import customexception.UnableToConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import models.AdminQuestions;
import models.UserResponse;

/**
 *
 * @author ria.mishra
 */
public class UserResponseRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public UserResponseRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public int addResponse(List<UserResponse> responses) throws SQLException {
        String sql = "insert into useresponse(response,questionId,userId) values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for(UserResponse response : responses) {
            pstmt.setString(1,response.response);
            pstmt.setInt(2,response.questionId);
            pstmt.setInt(3,response.userId);
            pstmt.addBatch();
        }
        
        int[] results = pstmt.executeBatch();
        return results.length;
    }
}
