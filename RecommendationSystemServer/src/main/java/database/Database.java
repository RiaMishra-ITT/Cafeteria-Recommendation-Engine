/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import customexception.UnableToConnectDatabase;
import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
    static String url = "jdbc:mysql://localhost:3306/recommendationsystem";
    static String username = "root";
    static String password = "root";
    static Connection conn = null;
    
    public static void connectToDatabase() throws UnableToConnectDatabase {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }  catch(Exception e) {
            throw new UnableToConnectDatabase("Unable to connect to database");
        }
    }
    
    public Connection getConnection() throws UnableToConnectDatabase {
        if(conn !=null) {
            return conn;
        } else {
            connectToDatabase();
            return conn;
        }
    }
}
