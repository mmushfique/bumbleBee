package com.mush.bumblebee.injector;

import com.mush.bumblebee.dao.AdminManager;
import com.mush.bumblebee.dao.ProductManager;
import com.mush.bumblebee.service.AdminServiceImpl;
import com.mush.bumblebee.service.ProductServiceImpl;

public class Injector {
    public AdminManager getAdminManager(){
        return new AdminManager();
    }
}
