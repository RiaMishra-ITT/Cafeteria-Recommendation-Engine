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
import models.Feedback;

/**
 *
 * @author ria.mishra
 */
public class FeedbackDatabaseRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public FeedbackDatabaseRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public List<Feedback> getFeedbackByItemId(int menuItemId) throws SQLException {
        String sql = "Select * from feedback where menuItemId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, menuItemId);
        ResultSet rs = pstmt.executeQuery();
        List<Feedback> feedbacks= new ArrayList<>();
        while(rs.next()) {
            feedbacks.add(new Feedback(rs.getInt("FeedbackId"),rs.getString("Comment"),
                    rs.getString("Rating"),rs.getString("Date"),rs.getInt("MenuItemId"),rs.getInt("UserId")));
        }
        
        return feedbacks;
    }
    
    public List<Integer> getLowRatingMenuItemIds() throws SQLException {
        String sql = "SELECT * FROM feedback WHERE LENGTH(Rating) <= 2";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> menuItemIds = new ArrayList<>();
        while (rs.next()) {
            menuItemIds.add(rs.getInt("MenuItemId"));
        }

        return menuItemIds;
    }

    
    public int submitFeedback(Feedback feedback) throws SQLException{
        String sql = "INSERT INTO feedback (Comment, Rating,Date,MenuItemId,UserId) VALUES (?, ?, ?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, feedback.comment);
        pstmt.setString(2, feedback.rating);
        pstmt.setString(3, feedback.date);
        pstmt.setInt(4,feedback.menuItemId);
        pstmt.setInt(5,feedback.userId);

        int rowInserted = pstmt.executeUpdate();
        return rowInserted;
    }
}
