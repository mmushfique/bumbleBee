package com.mush.bumblebee.dao;

import com.mush.bumblebee.factory.DbConnectorFactory;
import com.mush.bumblebee.factory.MySqlDbConnectorFactoryImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnector getDbConnector() {
        DbConnectorFactory factory=new MySqlDbConnectorFactoryImpl();
        return factory.getDbConnector();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        DbConnector connector=getDbConnector();
        Connection connection=connector.getDbConnection();
        return connection;
    }
}
