package com.cosw.quicklyshop.controller;

import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.User;

public interface SessionController {
    void setSessionToken(String sessionToken);
    void removeSessionToken();
    String getSessionToken();

    void setUsername(String username);

    void getUser(Callback<User> callback);
}
