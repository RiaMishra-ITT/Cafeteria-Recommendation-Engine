/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import customexception.FailedToAddItemException;
import customexception.FailedToUpdateItemException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public interface IMenuItemService {
    public void addMenuItem(ObjectInputStream input) throws IOException, ClassNotFoundException, SQLException, FailedToAddItemException  ;
    public void removeMenuItem(ObjectInputStream input) throws SQLException, FailedToUpdateItemException, IOException, ClassNotFoundException ;
    public List<MenuItem> getAllMenuItem() throws SQLException;
    public void updateMenuItem(ObjectInputStream input) throws SQLException, FailedToUpdateItemException,IOException,ClassNotFoundException;
    public MenuItem getMenuItemById(MenuItem menuItem) throws SQLException;
}
