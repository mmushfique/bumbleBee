package com.mush.bumblebee.dao;

import com.mush.bumblebee.domain.Brand;
import com.mush.bumblebee.factory.DbConnectorFactory;
import com.mush.bumblebee.factory.MySqlDbConnectorFactoryImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandManager {

    public boolean registerBrand(Brand brand) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="INSERT INTO brand (brandName) VALUES(?)";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, brand.getBrandName());

        int result=pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public Brand getSpecificBrand(String brandName) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="SELECT * FROM brand WHERE brandName=?";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, brandName);

        ResultSet rs= pst.executeQuery();

        Brand brand=new Brand();
        while(rs.next()) {
            brand.setId(rs.getLong("id"));
            brand.setBrandName(rs.getString("brandName"));
        }

        pst.close();
        connection.close();

        return brand;
    }

    public List<Brand> getAllBrands() throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        List<Brand> brandList=new ArrayList<Brand>();

        String query="SELECT * FROM brand";
        Statement st=connection.createStatement();

        ResultSet rs=st.executeQuery(query);
        while(rs.next()) {
            Brand brand=new Brand();
            brand.setId(rs.getLong("id"));
            brand.setBrandName(rs.getString("brandName"));

            brandList.add(brand);
        }

        st.close();
        connection.close();

        return brandList;
    }

    public boolean updateBrand(Brand brand) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="UPDATE brand SET brandName=? WHERE id=?";

        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1, brand.getBrandName());
        pst.setLong(2,brand.getId());

        int result = pst.executeUpdate();

        pst.close();
        connection.close();

        return result>0;
    }

    public boolean deleteBrand(String brandName) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = DbConnection.getConnection();

        String query="DELETE FROM brand WHERE brandName=?";

        PreparedStatement pst=connection.prepareStatement(query);
        pst.setString(1,brandName);

        int result = pst.executeUpdate();

        return result>0;
    }
}
