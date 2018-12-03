package com.cosw.quicklyshop.helpers;

public interface Callback<T> {
    void onSuccess(T... inputs);

    void onFailure(String error);
}
