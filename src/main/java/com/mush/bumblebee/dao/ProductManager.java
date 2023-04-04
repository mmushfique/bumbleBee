package com.mush.bumblebee.dao;

import com.mush.bumblebee.domain.Product;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {


    public boolean registerProduct(Product product) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="INSERT INTO product (productUniqueId,productName,productPrice,productDescription,productBrand,productCategory) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1,product.getProductUniqueId());
        pst.setString(2, product.getProductName());
        pst.setDouble(3, product.getProductPrice());
        pst.setString(4, product.getProductDescription());
        pst.setString(5, product.getProductBrand());
        pst.setString(6, product.getProductCategory());

        int result=pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public Product getSpecificProduct(String productUniqueId) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="SELECT * FROM product JOIN brand ON product.productBrand=brand.id JOIN category ON product.productBrand=category.id WHERE productUniqueId=?";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, productUniqueId);

        ResultSet rs= pst.executeQuery();

        Product product=new Product();
        while(rs.next()) {
            product.setProductUniqueId(rs.getString("productUniqueId"));
            product.setProductName(rs.getString("productName"));
            product.setProductPrice(rs.getDouble("productPrice"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductBrand(rs.getString("brandName"));
            product.setProductCategory(rs.getString("categoryName"));
        }

        pst.close();
        connection.close();

        return product;
    }

    public Product searchProduct(String productName) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        Product product=new Product();

        String query="SELECT * FROM product  JOIN brand ON product.productBrand=brand.id JOIN category ON product.productCategory=category.id WHERE productName=?";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, productName);

        ResultSet rs= pst.executeQuery();

        while(rs.next()) {
            //Product product=new Product();
            product.setProductUniqueId(rs.getString("productUniqueId"));
            product.setProductName(rs.getString("productName"));
            product.setProductPrice(rs.getDouble("productPrice"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductBrand(rs.getString("brandName"));
            product.setProductCategory(rs.getString("categoryName"));

            //productList.add(product);
        }

        pst.close();
        connection.close();

        return product;
    }

    public List<Product> getAllProducts() throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        List<Product> productList=new ArrayList<Product>();

        String query="SELECT * FROM product";
        Statement st=connection.createStatement();

        ResultSet rs=st.executeQuery(query);
        while(rs.next()) {
            Product product=new Product();
            product.setProductUniqueId(rs.getString("productUniqueId"));
            product.setProductName(rs.getString("productName"));
            product.setProductPrice(rs.getDouble("productPrice"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductBrand(rs.getString("productBrand"));
            product.setProductCategory(rs.getString("productCategory"));

            productList.add(product);
        }

        st.close();
        connection.close();

        return productList;
    }

    public boolean updateProduct(Product product) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="UPDATE product SET productName=?,productPrice=?,productDescription=?,productBrand=?,productCategory=?" +
                "WHERE productUniqueId=?";

        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1, product.getProductName());
        pst.setDouble(2, product.getProductPrice());
        pst.setString(3, product.getProductDescription());
        pst.setString(4, product.getProductBrand());
        pst.setString(5, product.getProductCategory());
        pst.setString(6, product.getProductUniqueId());

        int result = pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public boolean deleteProduct(String productUniqueId) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="DELETE FROM product WHERE productUniqueId=?";

        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1,productUniqueId);

        int result = pst.executeUpdate();

        return result>0;
    }
}
