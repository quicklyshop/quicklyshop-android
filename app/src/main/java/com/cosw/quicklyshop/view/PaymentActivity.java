package com.cosw.quicklyshop.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.cosw.quicklyshop.R;

public class PaymentActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        showToolbar(getResources().getString(R.string.toolbar_tittle_payment), true);

    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void payAndGoToHome(View view) {
        Toast.makeText(getApplicationContext(), "Se esta procesando su solicitud", Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Se ha realizado el pago satisfactoriamente", Toast.LENGTH_LONG).show();
            }

        }, 5000); // 5000ms delay

        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
