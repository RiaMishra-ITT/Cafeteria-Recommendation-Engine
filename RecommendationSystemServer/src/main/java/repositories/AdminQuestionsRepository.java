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
import models.AdminQuestions;

/**
 *
 * @author ria.mishra
 */
public class AdminQuestionsRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public AdminQuestionsRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public int addQuestions(List<AdminQuestions> questions) throws SQLException {
        String sql = "insert into AdminQuestions(question,menuItemId) values(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for(AdminQuestions question : questions) {
            pstmt.setString(1,question.question);
            pstmt.setInt(2,question.menuItemId);
            pstmt.addBatch();
        }
        
        int[] results = pstmt.executeBatch();
        return results.length;
    }
    
    public List<AdminQuestions> getQuestionsByMenuItem(int menuItemId) throws SQLException {
        String sql = "Select * from adminquestions where menuItemId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, menuItemId);
        ResultSet rs = pstmt.executeQuery();
        List<AdminQuestions> questions = new ArrayList<>();
        while (rs.next()) {
            questions.add(new AdminQuestions(
                rs.getInt("questionId"),
                rs.getString("question"),
                    rs.getInt("menuItemId")
            ));
        }

        return questions;
    }
}
