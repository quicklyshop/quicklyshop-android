package com.cosw.quicklyshop.dataholder;

import com.cosw.quicklyshop.model.Product;
import com.cosw.quicklyshop.model.User;

import java.util.ArrayList;
import java.util.List;

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

    @Getter @Setter
    List<Product> selectedProducts;

    private MainDataHolder() {
        selectedProducts = new ArrayList<>();
    }

}
