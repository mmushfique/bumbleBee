package com.mush.bumblebee.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnector {
    public Connection getDbConnection() throws ClassNotFoundException, SQLException, IOException;
}
