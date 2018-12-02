package com.cosw.quicklyshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cosw.quicklyshop.view.ContainerActivity;
import com.cosw.quicklyshop.view.CreateAccountActivity;
import com.cosw.quicklyshop.view.Payment;

public class LoginActivity extends AppCompatActivity {

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
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    }

    public void goHome(View view){
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
