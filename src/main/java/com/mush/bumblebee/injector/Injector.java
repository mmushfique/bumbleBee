package com.mush.bumblebee.injector;

import com.mush.bumblebee.dao.AdminManager;

public class Injector {
    public AdminManager getAdminManager(){
        return new AdminManager();
    }

}
