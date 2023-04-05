package com.mush.bumblebee.service;

import com.mush.bumblebee.domain.Product;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    public boolean registerProduct(Product product) throws SQLException, ClassNotFoundException, IOException;

    public Product getSpecificProduct(String productUniqueId) throws SQLException, ClassNotFoundException, IOException;

    public Product searchProduct(String productName) throws SQLException, ClassNotFoundException, IOException;

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException, IOException;

    public boolean updateProduct(Product product) throws SQLException, IOException, ClassNotFoundException;

    public boolean updateProductQuantity(Product product) throws SQLException, IOException, ClassNotFoundException;

    public boolean deleteProduct(String productUniqueId) throws SQLException, ClassNotFoundException, IOException;
}
