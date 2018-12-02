package com.cosw.quicklyshop.controller;

import com.cosw.quicklyshop.controller.impl.LoginControllerImpl;

public class ControllerFactory {

    private static ControllerFactory instance;

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }

        return instance;
    }

    /* ---------- */

    private LoginController loginController;

    private ControllerFactory() {
        loginController = new LoginControllerImpl();
    }

    public LoginController getLoginController() {
        return loginController;
    }

}
