package com.cosw.quicklyshop.view;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.controller.ControllerFactory;
import com.cosw.quicklyshop.controller.LoginController;
import com.cosw.quicklyshop.controller.SessionController;
import com.cosw.quicklyshop.dataholder.MainDataHolder;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.User;
import com.cosw.quicklyshop.view.ContainerActivity;
import com.cosw.quicklyshop.view.CreateAccountActivity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import com.cosw.quicklyshop.view.PaymentActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goPayment(View view){
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    public void goHome(View view){
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        final TextInputEditText username = (TextInputEditText) findViewById(R.id.username);
        final TextInputEditText password = (TextInputEditText) findViewById(R.id.password);

        username.setEnabled(true);
        password.setEnabled(true);
        super.onResume();
    }

    public void loginToServer(final View view) {
        LoginController lc = ControllerFactory.getInstance().getLoginController();

        final TextInputEditText username = (TextInputEditText) findViewById(R.id.username);
        final TextInputEditText password = (TextInputEditText) findViewById(R.id.password);

        username.setEnabled(false);
        password.setEnabled(false);

        lc.loginWithUsernameAndPassword(username.getText().toString(), password.getText().toString(), new Callback<String>() {
            @Override
            public void onSuccess(String... inputs) {
                Log.d(TAG, "Success getting token: " + Arrays.toString(inputs));

                try {
                    JsonNode rootNode = new ObjectMapper().readTree(new StringReader(inputs[0]));

                    JsonNode tokenNode = rootNode.get("accessToken");

                    String token = tokenNode.asText();

                    SessionController sc = ControllerFactory.getInstance().getSessionController();
                    sc.setSessionToken(token);
                    sc.setUsername(username.getText().toString());
                    sc.getUser(new Callback<User>() {
                        @Override
                        public void onSuccess(User... inputs) {
                            MainDataHolder.getInstance().setUser(inputs[0]);
                            Log.d(TAG, "Success getting user info: " + inputs[0]);

                            Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
                            goHome(view); // Cambia de vista
                        }

                        @Override
                        public void onFailure(String error) {
                            Log.e(TAG, "Error getting user data: " + error);
                            Toast.makeText(getApplicationContext(), "Failed to login: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (IOException e) {
                    Log.e(TAG, "Error en parsing de json de respuesta" ,e);
                    this.onFailure("Error parseando json de respuesta");
                }

            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Error: " + error);
                Toast.makeText(getApplicationContext(), "Failed to login: " + error, Toast.LENGTH_SHORT).show();

                username.setEnabled(true);
                password.setEnabled(true);
            }
        });
    }

}
