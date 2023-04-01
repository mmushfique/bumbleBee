package com.mush.bumblebee.factory;

import com.mush.bumblebee.dao.DbConnector;

public interface DbConnectorFactory {
    public DbConnector getDbConnector();
}
