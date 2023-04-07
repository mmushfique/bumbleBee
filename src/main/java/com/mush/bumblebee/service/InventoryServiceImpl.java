package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.InventoryManager;
import com.mush.bumblebee.domain.Inventory;
import com.mush.bumblebee.domain.Product;
import com.mush.bumblebee.injector.Injector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InventoryServiceImpl implements InventoryService{
    private final Injector injector = new Injector();
    private static InventoryServiceImpl service;

    private static ProductServiceImpl productService=ProductServiceImpl.getProductServiceInstance();

    InventoryManager inventoryManager= injector.getInventoryManager();

    private InventoryServiceImpl() {
    }

    public static synchronized InventoryServiceImpl getInventoryServiceInstance() {
        if(service==null) {
            service=new InventoryServiceImpl();
        }
        return service;
    }
    @Override
    public boolean registerInventory(Inventory inventory) throws SQLException, ClassNotFoundException, IOException {
        boolean status=false;
        if(inventoryManager.registerInventory(inventory)){
            Product product=new Product(inventory.getInventoryQuantity(),inventory.getInventoryForProductId());
            status = productService.updateProductQuantity(product);
        }
        return status;
    }

    @Override
    public Inventory searchInventory(String inventoryName) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public List<Inventory> getAllInventorys() throws SQLException, ClassNotFoundException, IOException {
        return inventoryManager.getAllInventorys();
    }

}
