package com.cosw.quicklyshop.view;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.controller.ControllerFactory;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.RegistrationForm;
import com.cosw.quicklyshop.model.User;
import com.cosw.quicklyshop.model.UserLogin;

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

        TextInputEditText password = findViewById(R.id.password_createaccount);
        TextInputEditText confirm = findViewById(R.id.confirmPassword);
        if (!password.getText().toString().equals(confirm.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Las contrasenas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        /* ------ */

        User user = getUserForm();
        UserLogin ul = getUserLoginForm();
        RegistrationForm rf = new RegistrationForm(user, ul);

        /* --------- */

        ControllerFactory.getInstance().getLoginController().registerUser(rf, new Callback<String>() {
            @Override
            public void onSuccess(String... inputs) {
                Log.d(TAG, "Registrado satisfatoriamente: " + Arrays.asList(inputs));
                Toast.makeText(getApplicationContext(), "Registrado satisfactoriamente", Toast.LENGTH_LONG).show();
                goToLogin(view);
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Ocurrio un error al registrar: " + error);
                Toast.makeText(getApplicationContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public User getUserForm() {
        User user = new User();

        TextInputEditText field = findViewById(R.id.email_createaccount);
        user.setEmail(field.getText().toString());
        user.setUsername(field.getText().toString());

        field = findViewById(R.id.name_createaccount);
        user.setFirstname(field.getText().toString());
        field = findViewById(R.id.lastname_createaccount);
        user.setLastname(field.getText().toString());
        field = findViewById(R.id.address_createaccount);
        user.setAddress(field.getText().toString());
        field = findViewById(R.id.phone_createaccount);
        user.setPhone(field.getText().toString());

        return user;
    }

    public UserLogin getUserLoginForm(){
        UserLogin ul = new UserLogin();

        TextInputEditText field = findViewById(R.id.email_createaccount);
        ul.setUsername(field.getText().toString());
        field = findViewById(R.id.password_createaccount);
        ul.setPassword(field.getText().toString());

        return ul;
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
