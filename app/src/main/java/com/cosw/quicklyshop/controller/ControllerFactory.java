package com.cosw.quicklyshop.controller;

import com.cosw.quicklyshop.controller.impl.LoginControllerImpl;
import com.cosw.quicklyshop.controller.impl.SessionControllerImpl;
import com.cosw.quicklyshop.controller.impl.UpdateControllerImpl;

import lombok.Getter;

public class ControllerFactory {

    private static ControllerFactory instance;

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }

        return instance;
    }

    /* ---------- */

    @Getter private LoginController loginController;
    @Getter private SessionController sessionController;
    @Getter private UpdateController updateController;

    private ControllerFactory() {
        loginController = new LoginControllerImpl();
        sessionController = new SessionControllerImpl();
        updateController = new UpdateControllerImpl();
    }

}
