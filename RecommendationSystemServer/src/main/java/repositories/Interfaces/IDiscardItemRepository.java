/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories.Interfaces;

import java.sql.SQLException;
import java.util.List;
import models.DiscardItemInfo;


public interface IDiscardItemRepository {
    public void addDiscardItems(List<Integer> menuItemIds) throws SQLException;
    public List<DiscardItemInfo> getDiscardedItemsWithRatings() throws SQLException;
}
