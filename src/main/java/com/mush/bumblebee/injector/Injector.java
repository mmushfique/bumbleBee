package com.mush.bumblebee.injector;

import com.mush.bumblebee.dao.AdminManager;
import com.mush.bumblebee.dao.BrandManager;
import com.mush.bumblebee.dao.CategoryManager;

public class Injector {
    public AdminManager getAdminManager(){
        return new AdminManager();
    }

    public BrandManager getBrandManager(){
        return new BrandManager();
    }
    public CategoryManager getCategoryManager(){
        return new CategoryManager();
    }

}
