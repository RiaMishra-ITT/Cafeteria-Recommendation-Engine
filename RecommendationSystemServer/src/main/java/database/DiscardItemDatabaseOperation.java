/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

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
public class DiscardItemDatabaseOperation {
    private final Database database = new Database();
    private static Connection conn;
    
    public DiscardItemDatabaseOperation() throws UnableToConnectDatabase {
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
            if (rs.next() && rs.getInt(1) == 0) { // If the menuItemId does not exist
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
    
    private void deleteDiscardItems(List<Integer> itemIds) throws SQLException {

        String placeholders = String.join(",", new String[itemIds.size()]);
        String sql = "DELETE FROM MenuItem WHERE menuItemId IN (" + placeholders + ")";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < itemIds.size(); i++) {
                pstmt.setInt(i + 1, itemIds.get(i));
            }
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println(rowsDeleted + " items removed from the menu.");
        }
    }
}
