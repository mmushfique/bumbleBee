package com.mush.bumblebee.dao;

import com.mush.bumblebee.domain.Admin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManager {

    public Admin verifyLogin(String adminEmail, String adminPassword) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        Admin admin=null;

        String query="SELECT adminFirstName FROM admin WHERE adminEmail=? AND adminPassword=?";
        PreparedStatement pst=connection.prepareStatement(query);

        pst.setString(1, adminEmail);
        pst.setString(2, adminPassword);

        ResultSet rs= pst.executeQuery();

        //rs.next()=> If admin is present in the database
        if(rs.next()){
            admin=new Admin();
            admin.setAdminFirstName(rs.getString("adminFirstName"));
        }

        pst.close();
        connection.close();

        return admin;
    }
}
