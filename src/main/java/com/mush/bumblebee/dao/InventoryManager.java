package com.mush.bumblebee.dao;

import com.mush.bumblebee.domain.Inventory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {


    public boolean registerInventory(Inventory inventory) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="INSERT INTO inventory (inventoryUniqueId,inventoryQuantity,inventoryForProductId) VALUES(?,?,?)";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1,inventory.getInventoryUniqueId());
        pst.setLong(2, inventory.getInventoryQuantity());
        pst.setString(3, inventory.getInventoryForProductId());

        int result=pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public List<Inventory> getAllInventorys() throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        List<Inventory> inventoryList=new ArrayList<Inventory>();

        String query="SELECT * FROM inventory JOIN product ON inventory.inventoryForProductId=product.productUniqueId";
        Statement st=connection.createStatement();

        ResultSet rs=st.executeQuery(query);
        while(rs.next()) {
            Inventory inventory=new Inventory();
            inventory.setInventoryUniqueId(rs.getString("inventoryUniqueId"));
            inventory.setInventoryQuantity(rs.getLong("inventoryQuantity"));
            inventory.setInventoryUpdatedTime(rs.getTimestamp("inventoryUpdatedTime"));
            inventory.setInventoryForProductId(rs.getString("inventoryForProductId"));
            inventory.setInventoryForProductName(rs.getString("productName"));

            inventoryList.add(inventory);
        }

        st.close();
        connection.close();

        return inventoryList;
    }
}
