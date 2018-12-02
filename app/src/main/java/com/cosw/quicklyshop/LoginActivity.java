package com.cosw.quicklyshop;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cosw.quicklyshop.controller.ControllerFactory;
import com.cosw.quicklyshop.controller.LoginController;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.view.ContainerActivity;
import com.cosw.quicklyshop.view.CreateAccountActivity;
import com.cosw.quicklyshop.view.fragment.ProfileFragment;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        (this.getApplicationContext())
//                .getApplicationComponent()
//                .newActivityComponentBuilder()
//                .activity(this)
//                .build()
//                .inject(this);

    }

    public void goCreateAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
    public void goHome(View view){
        LoginController lc = ControllerFactory.getInstance().getLoginController();

        lc.loginWithUsernameAndPassword("prueba@prueba.com", "password", new Callback<String>() { // TODO
            @Override
            public void onSuccess(String... inputs) {
                Log.d(TAG, "Success: " + Arrays.toString(inputs));
            }

            @Override
            public void onFailed(String error) {
                Log.e(TAG, "Error: " + error);
            }
        });

        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    /*public void goProfile(Fragment fragment){
        Intent intent = new Intent(this, ProfileFragment.class);
        startActivityFromFragment(intent,ProfileFragment);
    }*/

}
