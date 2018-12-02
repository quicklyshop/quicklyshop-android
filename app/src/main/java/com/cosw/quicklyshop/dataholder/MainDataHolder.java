package com.cosw.quicklyshop.dataholder;

import com.cosw.quicklyshop.model.User;

public class MainDataHolder {

    private static MainDataHolder instance;

    public static MainDataHolder getInstance() {
        if (instance == null) {
            instance = new MainDataHolder();
        }

        return instance;
    }

    User user;

    private MainDataHolder() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
