package com.cosw.quicklyshop.view.codescanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.dataholder.MainDataHolder;
import com.cosw.quicklyshop.model.Product;
import com.cosw.quicklyshop.view.ContainerActivity;
import com.cosw.quicklyshop.view.FacturaActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodeScannerActivity extends AppCompatActivity {

    private static final int RC_PERMISSION = 10;
    private static final String TAG = "CodeScannerActivity";
    private CodeScanner mCodeScanner;
    private boolean mPermissionGranted;

    private List<Product> addedProducts;
    private Set<String> addedProductsIds;

    public CodeScannerActivity() {
        super();
        addedProducts = MainDataHolder.getInstance().getSelectedProducts();
        addedProductsIds = new HashSet<>();
    }

    Product decodeProduct(String json) {
        Log.d(TAG, "Product JSON: " + json);
        ObjectMapper om = new ObjectMapper();
        Product p = null;
        try {
             p = om.readValue(json, Product.class);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return p;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Button goToCheckoutBtn = findViewById(R.id.gotocheckout_button);
        goToCheckoutBtn.setEnabled(false);

        mCodeScanner = new CodeScanner(this, (CodeScannerView) findViewById(R.id.scanner_view));
        mCodeScanner.setDecodeCallback(result -> {
            Product p = decodeProduct(result.getText());
            if (p != null) {
                if (!addedProductsIds.contains(p.getId())) {
                    addedProducts.add(p);
                    addedProductsIds.add(p.getId());
                    Log.d(TAG, "Productos en el carrito: " + Arrays.asList(addedProducts));
                    runOnUiThread(() -> {
                        goToCheckoutBtn.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Producto " + p.getName() + " adicionado", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Log.i(TAG, "El producto ya se adiciono: " + p.toString());
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Producto ya se adiciono.", Toast.LENGTH_SHORT).show();
                    });
                }
            } else {
                runOnUiThread(() ->  {
                    Toast.makeText(getApplicationContext(), "Error: no parece ser un producto nuestro", Toast.LENGTH_SHORT).show();
                });
            }

            runOnUiThread(() -> {
                mCodeScanner.startPreview();
            });
        });


        mCodeScanner.setErrorCallback(error -> runOnUiThread(
                () -> Toast.makeText(this, getString(R.string.scanner_error), Toast.LENGTH_LONG).show()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = false;
                requestPermissions(new String[] {Manifest.permission.CAMERA}, RC_PERMISSION);
            } else {
                mPermissionGranted = true;
            }
        } else {
            mPermissionGranted = true;
        }
    }

    public void goToCheckout(View view) {
        Intent intent = new Intent(this, FacturaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == RC_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = true;
                mCodeScanner.startPreview();
            } else {
                mPermissionGranted = false;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionGranted) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
