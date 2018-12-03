package com.cosw.quicklyshop.view;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.dataholder.MainDataHolder;
import com.cosw.quicklyshop.model.User;

import lombok.NonNull;

public class EditProfileActivity extends AppCompatActivity {
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
        // TODO
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
