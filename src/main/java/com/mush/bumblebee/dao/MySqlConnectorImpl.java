package com.mush.bumblebee.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnectorImpl implements DbConnector {
    @Override
    public Connection getDbConnection() throws ClassNotFoundException, SQLException, IOException {
        // Load the properties file
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("application-local.properties"));

        String driverclass=props.getProperty("database.driverclass");
        String databaseURL=props.getProperty("database.url");
        String user=props.getProperty("database.username");
        String password =props.getProperty("database.password");

        Class.forName(driverclass);
        Connection connection= DriverManager.getConnection(databaseURL,user,password);

        return connection;
    }
}
