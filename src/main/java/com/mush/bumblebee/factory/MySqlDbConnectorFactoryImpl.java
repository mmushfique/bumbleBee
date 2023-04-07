package com.mush.bumblebee.factory;

import com.mush.bumblebee.dao.DbConnector;
import com.mush.bumblebee.dao.MySqlConnectorImpl;
import com.mush.bumblebee.factory.DbConnectorFactory;

public class MySqlDbConnectorFactoryImpl implements DbConnectorFactory {
    @Override
    public DbConnector getDbConnector() {
        return new MySqlConnectorImpl();
    }
}
