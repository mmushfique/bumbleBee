package com.mush.bumblebee.dao;

import com.mush.bumblebee.domain.Category;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    public boolean registerCategory(Category category) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="INSERT INTO category (categoryName) VALUES(?)";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, category.getCategoryName());

        int result=pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public Category getSpecificCategory(String categoryName) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="SELECT * FROM category WHERE categoryName=?";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, categoryName);

        ResultSet rs= pst.executeQuery();

        Category category=new Category();
        while(rs.next()) {
            category.setId(rs.getLong("id"));
            category.setCategoryName(rs.getString("categoryName"));
        }

        pst.close();
        connection.close();

        return category;
    }

    public List<Category> getAllCategorys() throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        List<Category> categoryList=new ArrayList<Category>();

        String query="SELECT * FROM category";
        Statement st=connection.createStatement();

        ResultSet rs=st.executeQuery(query);
        while(rs.next()) {
            Category category=new Category();
            category.setId(rs.getLong("id"));
            category.setCategoryName(rs.getString("categoryName"));

            categoryList.add(category);
        }

        st.close();
        connection.close();

        return categoryList;
    }

    public boolean updateCategory(Category category) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="UPDATE category SET categoryName=? WHERE id=?";

        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1, category.getCategoryName());
        pst.setLong(2, category.getId());
        int result = pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public boolean deleteCategory(String categoryName) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="DELETE FROM category WHERE categoryName=?";

        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1,categoryName);

        int result = pst.executeUpdate();

        return result>0;
    }
}
