package com.mush.bumblebee.injector;

import com.mush.bumblebee.dao.AdminManager;
import com.mush.bumblebee.dao.BrandManager;

public class Injector {
    public AdminManager getAdminManager(){
        return new AdminManager();
    }

    public BrandManager getBrandManager(){
        return new BrandManager();
    }

}
