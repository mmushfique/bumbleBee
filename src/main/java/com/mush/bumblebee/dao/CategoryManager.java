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

    public String registerCategory(Category category) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="SELECT * FROM category WHERE categoryName=?";
        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1, category.getCategoryName());
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            pst.close();
            connection.close();
            return "You already have a category:" +category.getCategoryName()+", try a different one";
        }else {

            query = "INSERT INTO category (categoryName) VALUES(?)";
            pst = connection.prepareStatement(query);

            pst.setString(1, category.getCategoryName());

            int result = pst.executeUpdate();

            pst.close();
            connection.close();

            return "created";
        }
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

    public String updateCategory(Category category) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="SELECT * FROM category WHERE categoryName=?";
        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1, category.getCategoryName());
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            pst.close();
            connection.close();
            return "You already have a category:" +category.getCategoryName()+", try a different one";
        }else {

            query = "UPDATE category SET categoryName=? WHERE id=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, category.getCategoryName());
            pst.setLong(2, category.getId());
            int result = pst.executeUpdate();

            pst.close();
            connection.close();

            return "updated";
        }
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
