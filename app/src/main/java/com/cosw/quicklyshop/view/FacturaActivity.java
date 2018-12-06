package com.cosw.quicklyshop.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.adapter.PictureAdapterRecyclerView;
import com.cosw.quicklyshop.dataholder.MainDataHolder;

public class FacturaActivity extends AppCompatActivity {
    private static final String TAG = "FacturaActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        showToolbar(getResources().getString(R.string.toolbar_tittle_factura), false);

        RecyclerView picturesRecycler = (RecyclerView) findViewById(R.id.product_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        ProductAdapterRecyclerView pictureAdapterRecyclerView =
                new ProductAdapterRecyclerView(MainDataHolder.getInstance().getSelectedProducts(), R.layout.cardview_product);
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void goPayment(View view){
        Log.d(TAG, "Se va al pago");
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    public void goBackToScanner(View view) {
        Log.d(TAG, "Se devuelve al scanner");
//        Intent intent = new Intent(this, CodeScannerActivity.class);
//        startActivity(intent);
        finish(); // termina la actividad
    }

    public void deleteSelectedProduct(View view) {
        // TODO
    }

}
