/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


public class User {
    public int userId;
    public String name;
    public int roleId;
    
    public User(int userId, String name, int roleId) {
        this.userId = userId;
        this.name = name;
        this.roleId = roleId;
    }
}
