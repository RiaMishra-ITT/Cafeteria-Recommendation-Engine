/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

/**
 *
 * @author ria.mishra
 */
public interface IUserActivityService {
    public void addUserActivity(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException;
}