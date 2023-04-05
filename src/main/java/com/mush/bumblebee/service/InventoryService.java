package com.mush.bumblebee.service;

import com.mush.bumblebee.domain.Inventory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InventoryService {
    public boolean registerInventory(Inventory inventory) throws SQLException, ClassNotFoundException, IOException;

    public Inventory searchInventory(String inventoryName) throws SQLException, ClassNotFoundException, IOException;

    public List<Inventory> getAllInventorys() throws SQLException, ClassNotFoundException, IOException;

}
