/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.util.List;
import models.MenuItem;

/**
 *
 * @author ria.mishra
 */
public interface IMenuItemService {
    public void addMenuItem();
    public void viewAllItems();
    public MenuItem getItemById(List<MenuItem> items, int id);
    public void deleteMenuItem();
    public void deleteMultipleItems(List<Integer> ids);
    public void updateItem();
}
