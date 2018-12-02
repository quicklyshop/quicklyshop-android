package com.cosw.quicklyshop.controller.impl;

import android.util.Log;

import com.cosw.quicklyshop.controller.LoginController;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.helpers.HttpUtils;
import com.cosw.quicklyshop.model.User;
import com.cosw.quicklyshop.model.UserLogin;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class LoginControllerImpl implements LoginController {

    private static final String TAG = "LoginControllerImpl";

    public LoginControllerImpl() {
    }

    @Override
    public void loginWithUsernameAndPassword(String username, String password, final Callback<String> callback) {
//        UserLogin ul = new UserLogin(username, password);

        String json = "";

        StringEntity entity = null;

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("username", username);
            jsonParams.put("password", password);
            entity = new StringEntity(jsonParams.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            Log.e(TAG, "Error", e);
        }

        HttpUtils.post(null, "/user/login", entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                String error = "An error ocurred in login: " + responseString;
                Log.e(TAG, error, throwable);
                callback.onFailed(error);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "Successful login: " + responseString);
                callback.onSuccess(responseString);
            }
        });
    }

    @Override
    public void registerUser(User newuser, Callback callback) {

    }
}
