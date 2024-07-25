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
    public void removeMenuItem(ObjectInputStream input) throws FailedToUpdateItemException, IOException, ClassNotFoundException ;
    public List<MenuItem> getAllMenuItem() throws IOException, ClassNotFoundException;
    public void updateMenuItem(ObjectInputStream input) throws FailedToUpdateItemException,IOException,ClassNotFoundException;
    List<MenuItem> getItemsByMealType(int mealTypeId) throws IOException;
    public void removeMenuItems(ObjectInputStream input) throws IOException, ClassNotFoundException ;
}
