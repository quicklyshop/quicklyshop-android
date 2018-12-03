package com.cosw.quicklyshop.controller.impl;

import android.util.Log;

import com.cosw.quicklyshop.controller.SessionController;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.helpers.HttpUtils;
import com.cosw.quicklyshop.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class SessionControllerImpl implements SessionController {

    private static final String TAG = "SessionControllerImpl";

    private String sessionToken;
    private String username;
    private User actualUser;

    @Override
    public void setSessionToken(String sessionToken) {
        Log.d(TAG, "Se coloca token de sesion: " + sessionToken);
        this.sessionToken = sessionToken;
    }

    @Override
    public void removeSessionToken() {
        this.sessionToken = null;
        actualUser = null;
    }

    @Override
    public String getSessionToken() {
        Log.d(TAG, "Se obtiene token de sesion: " + this.sessionToken);
        return this.sessionToken;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void getUser(final Callback<User> callback) {
        if (sessionToken == null) {
            callback.onFailure("Is not logged in");
            return;
        }

        if (actualUser == null && sessionToken != null) {
            RequestParams rp = new RequestParams();
            HttpUtils.get("/user/" + this.username, rp, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callback.onFailure(responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        User user = objectMapper.readValue(responseString, User.class);
                        callback.onSuccess(user);
                    } catch (IOException e) {
                        callback.onFailure(e.getMessage());
                    }
                }
            });

            return;
        }

        if (actualUser != null) {
            callback.onSuccess(actualUser);
        }
    }
}
