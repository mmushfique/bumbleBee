package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.ProductManager;
import com.mush.bumblebee.domain.Product;
import com.mush.bumblebee.injector.Injector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private static ProductServiceImpl service;
    private final Injector injector = new Injector();
    ProductManager productManager= injector.getProductManager();

    private ProductServiceImpl() {
    }

    public static synchronized ProductServiceImpl getProductServiceInstance() {
        if(service==null) {
            service=new ProductServiceImpl();
        }
        return service;
    }

    @Override
    public boolean registerProduct(Product product) throws SQLException, ClassNotFoundException, IOException {
        return productManager.registerProduct(product);
    }

    @Override
    public Product getSpecificProduct(String productUniqueId) throws SQLException, ClassNotFoundException, IOException {
        Product product=productManager.getSpecificProduct(productUniqueId);
        return product;
    }

    @Override
    public Product searchProduct(String productName) throws SQLException, ClassNotFoundException, IOException {
        return productManager.searchProduct(productName);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException, IOException {
        return productManager.getAllProducts();
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException, IOException, ClassNotFoundException {
        return productManager.updateProduct(product);
    }

    @Override
    public boolean updateProductQuantity(Product product) throws SQLException, IOException, ClassNotFoundException {
        return productManager.updateProductQuantity(product);
    }

    @Override
    public boolean deleteProduct(String productUniqueId) throws SQLException, ClassNotFoundException, IOException {
        return productManager.deleteProduct(productUniqueId);
    }
}
