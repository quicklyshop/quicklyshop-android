package com.cosw.quicklyshop.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.controller.ControllerFactory;
import com.cosw.quicklyshop.dataholder.MainDataHolder;
import com.cosw.quicklyshop.helpers.Callback;
import com.cosw.quicklyshop.model.User;

import lombok.NonNull;

public class EditProfileActivity extends AppCompatActivity {
    private static final String TAG = "EditProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        showToolbar(getResources().getString(R.string.toolbar_tittle_editProfile), true);

        @NonNull User user = MainDataHolder.getInstance().getUser();
        setUserDataInView(user);
    }

    public void setUserDataInView(User user) {
        TextInputEditText email = findViewById(R.id.email_updateprofile);
        email.setText(user.getEmail());

        TextInputEditText name = findViewById(R.id.name_updateprofile);
        name.setText(user.getFirstname());

        TextInputEditText lastname = findViewById(R.id.lastname_updateprofile);
        lastname.setText(user.getLastname());

        TextInputEditText phone = findViewById(R.id.phone_updateprofile);
        phone.setText(user.getPhone());

        TextInputEditText address = findViewById(R.id.address_updateprofile);
        address.setText(user.getAddress());
    }

    public void updateProfile(View view) {
        @NonNull User user = MainDataHolder.getInstance().getUser();

        TextInputEditText name = findViewById(R.id.name_updateprofile);
        user.setFirstname(name.getText().toString());

        TextInputEditText lastname = findViewById(R.id.lastname_updateprofile);
        user.setLastname(lastname.getText().toString());

        TextInputEditText phone = findViewById(R.id.phone_updateprofile);
        user.setPhone(phone.getText().toString());

        TextInputEditText address = findViewById(R.id.address_updateprofile);
        user.setAddress(address.getText().toString());

        Log.d(TAG, "Datos obtenidos del usuario: " + user);

        sendUserUpdate(user);
    }

    public void sendUserUpdate(User user) {
        ControllerFactory.getInstance().getUpdateController().updateProfile(user, new Callback<String>() {
            @Override
            public void onSuccess(String... inputs) {
                Log.d(TAG, "Success getting user info: " + inputs[0]);

                Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT).show();
                goProfileFragment();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), "An error ocurred updating", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error: " + error);
            }
        });
    }

    public void goProfileFragment() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
