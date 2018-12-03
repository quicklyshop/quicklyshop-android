package com.cosw.quicklyshop.dataholder;

import com.cosw.quicklyshop.model.User;

import lombok.Getter;
import lombok.Setter;

public class MainDataHolder {

    private static MainDataHolder instance;

    public static MainDataHolder getInstance() {
        if (instance == null) {
            instance = new MainDataHolder();
        }

        return instance;
    }

    @Getter @Setter
    User user;

    private MainDataHolder() {

    }

}
