package com.cosw.quicklyshop.controller;

import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.User;

public interface LoginController {

    void loginWithUsernameAndPassword(String username, String password, Callback<String> callback);
    void registerUser(User newuser, Callback<String> callback);

}
