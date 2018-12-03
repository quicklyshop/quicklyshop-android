package com.cosw.quicklyshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cosw.quicklyshop.LoginActivity;
import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.controller.ControllerFactory;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.User;

import java.util.Arrays;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount), true);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void createAccount(View view) {
        Log.d(TAG, "Hace llamado de crear cuenta");

        User user = new User();

        ControllerFactory.getInstance().getLoginController().registerUser(user, new Callback<String>() {
            @Override
            public void onSuccess(String... inputs) {
                Log.d(TAG, "Registrado satisfatoriamente: " + Arrays.asList(inputs));
                Toast.makeText(getApplicationContext(), "Registrado satisfactoriamente", Toast.LENGTH_LONG).show();
                goToLogin(view);
            }

            @Override
            public void onFailed(String error) {
                Log.e(TAG, "Ocurrio un error al registrar: " + error);
                Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
