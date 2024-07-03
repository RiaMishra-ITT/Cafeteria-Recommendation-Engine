/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services.Interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import models.DiscardItemInfo;


public interface IDiscardItemService {
    public void addDiscardItem(List<Integer> menuItemIds) throws IOException, ClassNotFoundException, SQLException; 
    public List<DiscardItemInfo> getDiscardedItemsWithRatings() throws SQLException;
}
