package com.cosw.quicklyshop.controller;

import com.cosw.quicklyshop.controller.impl.LoginControllerImpl;
import com.cosw.quicklyshop.controller.impl.SessionControllerImpl;

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
    private SessionController sessionController;

    private ControllerFactory() {
        loginController = new LoginControllerImpl();
        sessionController = new SessionControllerImpl();
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public SessionController getSessionController() {
        return sessionController;
    }
}
