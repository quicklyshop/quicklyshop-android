package com.cosw.quicklyshop.helpers;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;
import lombok.NonNull;

public class HttpUtils {
    private static final String BASE_URL = "https://quicklyshop-backend.herokuapp.com";

    private static AsyncHttpClient client = new AsyncHttpClient();

    private HttpUtils() {
    }

    public static void get(@NonNull String url, @NonNull RequestParams params, @NonNull AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(@NonNull String url, @NonNull RequestParams params, @NonNull AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, @NonNull String url, HttpEntity entity, @NonNull String contentType, @NonNull AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), entity, contentType,
                responseHandler);
    }

    public static void getByUrl(@NonNull String url, @NonNull RequestParams params, @NonNull AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void postByUrl(@NonNull String url, @NonNull RequestParams params, @NonNull AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}