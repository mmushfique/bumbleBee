package com.mush.bumblebee.domain;

public class Product {

    long id;

    String productUniqueId;
    String productName;
    Double productPrice;
    String productDescription;
    String productBrand;
    String productCategory;

    public Product() {
    }

    public Product(String productName, Double productPrice,String productDescription, String productBrand, String productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription=productDescription;
        this.productBrand = productBrand;
        this.productCategory = productCategory;
    }
    public Product(String productUniqueId,String productName, Double productPrice,String productDescription, String productBrand, String productCategory) {
        this.productUniqueId=productUniqueId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription=productDescription;
        this.productBrand = productBrand;
        this.productCategory = productCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductUniqueId() {
        return productUniqueId;
    }

    public void setProductUniqueId(String productUniqueId) {
        this.productUniqueId = productUniqueId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
