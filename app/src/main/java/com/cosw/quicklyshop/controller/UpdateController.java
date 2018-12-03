package com.cosw.quicklyshop.controller;

import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.User;

public interface UpdateController {

    void updateProfile(User user, Callback<String> callback);
}
