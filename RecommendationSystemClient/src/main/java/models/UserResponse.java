/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author ria.mishra
 */
public class UserResponse implements Serializable{
    public int responseId;
    public String response;
    public int userId;
    public int questionId;
    
    public UserResponse(int responseId,int questionId,String response,int userId) {
        this.responseId = responseId;
        this.response = response;
        this.userId = userId;
        this.questionId = questionId;
    }
}
