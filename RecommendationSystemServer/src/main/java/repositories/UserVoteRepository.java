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
import models.UserVote;

/**
 *
 * @author ria.mishra
 */
public class UserVoteRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public UserVoteRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public int addVote(List<UserVote> votes) throws SQLException {
        String sql = "INSERT INTO uservote (userId, menuItemId,Date) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for(UserVote vote : votes) {
            pstmt.setInt(1, vote.userId);
            pstmt.setInt(2, vote.menuItemId);
            pstmt.setString(3, vote.date);
            pstmt.addBatch();
        }
        
        int[] results = pstmt.executeBatch();
        return results.length;
    }
}
