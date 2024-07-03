/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import database.Database;
import java.sql.SQLException;
import java.util.List;
import models.DiscardItemInfo;
import repositories.Interfaces.IDiscardItemRepository;


public class DiscardItemRepository implements IDiscardItemRepository{
    private Database database = new Database();
    @Override
    public void addDiscardItems(List<Integer> menuItemIds) throws SQLException {
        database.addDiscardItem(menuItemIds);
    }

    @Override
    public List<DiscardItemInfo> getDiscardedItemsWithRatings() throws SQLException {
        return database.getDiscardedItemsWithRatings();
    }
    
}
