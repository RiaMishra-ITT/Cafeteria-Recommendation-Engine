/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import customexception.UnableToConnectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.DiscardItemInfo;

/**
 *
 * @author ria.mishra
 */
public class DiscardItemRepository {
    private final Database database = new Database();
    private static Connection conn;
    
    public DiscardItemRepository() throws UnableToConnectDatabase {
        conn = database.getConnection();
    }
    
    public void addDiscardItems(List<Integer> menuItemIds) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM discardItem WHERE MenuItemId = ?";
        String insertSql = "INSERT INTO discardItem (MenuItemId) VALUES (?)";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        PreparedStatement insertStmt = conn.prepareStatement(insertSql);

        for (int menuItemId : menuItemIds) {
            checkStmt.setInt(1, menuItemId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) { 
                insertStmt.setInt(1, menuItemId);
                insertStmt.addBatch();
            }
        }
        insertStmt.executeBatch();
    }
    
    public List<DiscardItemInfo> getDiscardedItemsWithRatings() throws SQLException {
        String sql = "SELECT di.MenuItemId, mi.ItemName " +
                     "FROM discardItem di " +
                     "JOIN menuItem mi ON di.MenuItemId = mi.MenuItemId";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<DiscardItemInfo> discardedItems = new ArrayList<>();
        while (rs.next()) {
            discardedItems.add(new DiscardItemInfo(
                rs.getInt("MenuItemId"),
                rs.getString("ItemName"),
                "",
                    ""
            ));
        }

        return discardedItems;
    }
}
