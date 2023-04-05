package com.mush.bumblebee.domain;

import java.sql.Timestamp;

public class Inventory {
    long id;
    String inventoryUniqueId;
    long inventoryQuantity;// To enter the added stock amount
    Timestamp inventoryUpdatedTime;
    String inventoryForProductId;
    String inventoryForProductName;

    public Inventory() {
    }


    public Inventory(String inventoryUniqueId, long inventoryQuantity, String inventoryForProductId) {
        this.inventoryUniqueId = inventoryUniqueId;
        this.inventoryQuantity = inventoryQuantity;
        this.inventoryForProductId = inventoryForProductId;
    }

    public String getInventoryUniqueId() {
        return inventoryUniqueId;
    }

    public void setInventoryUniqueId(String inventoryUniqueId) {
        this.inventoryUniqueId = inventoryUniqueId;
    }

    public long getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(long inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public Timestamp getInventoryUpdatedTime() {
        return inventoryUpdatedTime;
    }

    public void setInventoryUpdatedTime(Timestamp inventoryUpdatedTime) {
        this.inventoryUpdatedTime = inventoryUpdatedTime;
    }

    public String getInventoryForProductId() {
        return inventoryForProductId;
    }

    public void setInventoryForProductId(String inventoryForProductId) {
        this.inventoryForProductId = inventoryForProductId;
    }

    public String getInventoryForProductName() {
        return inventoryForProductName;
    }

    public void setInventoryForProductName(String inventoryForProductName) {
        this.inventoryForProductName = inventoryForProductName;
    }
}
