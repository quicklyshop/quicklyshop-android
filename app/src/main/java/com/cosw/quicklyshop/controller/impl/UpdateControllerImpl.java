package com.cosw.quicklyshop.controller.impl;

import android.util.Log;
import android.widget.Toast;

import com.cosw.quicklyshop.controller.ControllerFactory;
import com.cosw.quicklyshop.controller.UpdateController;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.helpers.HttpUtils;
import com.cosw.quicklyshop.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import lombok.NonNull;

public class UpdateControllerImpl implements UpdateController {

    private static final String TAG = "UpdateControllerImpl";

    @Override
    public void updateProfile(@NonNull User user, @NonNull Callback<String> callback) {
        Log.d(TAG, "Se intenta realiza actualizacion de perfil de usuario: " + user);

        ObjectMapper om = new ObjectMapper();
        StringEntity entity = null;

        try {
            String json = om.writeValueAsString(user);
            Log.d(TAG, "JSON: " + json);
            entity = new StringEntity(json);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            callback.onFailure(e.getMessage());
            return;
        }

        Map<String, String> headers = new HashMap<>();
        String token = ControllerFactory.getInstance().getSessionController().getSessionToken();
        headers.put("authorization", "Bearer " + token);

        HttpUtils.postWithHeaders(null, "/user/profile", entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                String error = responseString;
                Log.e(TAG, Arrays.asList(headers).toString());
                Log.e(TAG, error, throwable);
                callback.onFailure(error);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, Arrays.asList(headers).toString());
                Log.d(TAG, responseString);
                ControllerFactory.getInstance().getSessionController().getUser(new Callback<User>() {
                    @Override
                    public void onSuccess(User... inputs) {
                        Log.i(TAG, "Updated user from server successfully: " + Arrays.asList(inputs));
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.e(TAG, "Failed to get updated information from server");
                        Toast.makeText(null, "Ocurrio un error, vuelve a iniciar sesion", Toast.LENGTH_LONG);
                    }
                });
                callback.onSuccess(responseString);
            }
        }, headers);
    }
}
