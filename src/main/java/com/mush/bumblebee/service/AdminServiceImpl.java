package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.AdminManager;
import com.mush.bumblebee.domain.Admin;
import com.mush.bumblebee.injector.Injector;
import com.mush.bumblebee.util.PasswordEncryption;
import java.io.IOException;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private static AdminServiceImpl service;
    private static Injector injector = new Injector();
    private AdminManager adminManager = injector.getAdminManager();

    private AdminServiceImpl() {
    }

    public static synchronized AdminServiceImpl getAdminServiceInstance() {
        if (service == null) {
            service = new AdminServiceImpl();
        }
        return service;
    }

    @Override
    public Admin verifyLogin(String adminEmail, String adminPassword) throws SQLException, ClassNotFoundException, IOException {
        if (adminEmail==null || adminPassword==null) {
            return null;
        } else {
            //verify whether email is in correct format(example@example.com) before quering database
            String regex = "^[\\w\\d._%+-]+@[\\w\\d.-]+\\.[a-zA-Z]{2,}$";
            if (!adminEmail.matches(regex)) {
                return null;
            } else {
                String hashedPasssword = PasswordEncryption.encryptPassword(adminPassword);
                return adminManager.verifyLogin(adminEmail, hashedPasssword);
            }
        }
    }
}
