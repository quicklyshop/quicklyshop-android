package com.cosw.quicklyshop.controller.impl;

import android.util.Log;

import com.cosw.quicklyshop.controller.LoginController;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.helpers.HttpUtils;
import com.cosw.quicklyshop.model.RegistrationForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                String error = responseString;
                Log.e(TAG, error, throwable);
                callback.onFailure(error);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, responseString);
                callback.onSuccess(responseString);
            }
        });
    }

    @Override
    public void registerUser(RegistrationForm newuser, Callback<String> callback) {
        Log.d(TAG, "Intenta realizar registro de usuario: " + newuser);

        ObjectMapper om = new ObjectMapper();
        StringEntity entity = null;

        try {
            String json = om.writeValueAsString(newuser);
            Log.d(TAG, "JSON: " + json);
            entity = new StringEntity(json);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            callback.onFailure(e.getMessage());
            return;
        }

        HttpUtils.post(null, "/user/register", entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                String error = responseString;
                Log.e(TAG, error, throwable);
                callback.onFailure(error);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, responseString);
                callback.onSuccess(responseString);
            }
        });
    }
}
