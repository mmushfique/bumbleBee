package com.mush.bumblebee.service;

import com.mush.bumblebee.domain.Admin;
import java.io.IOException;
import java.sql.SQLException;

public interface AdminService {
    public Admin verifyLogin(String adminEmail, String adminPassword) throws SQLException, ClassNotFoundException, IOException;
}
