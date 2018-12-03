package com.cosw.quicklyshop.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.adapter.PictureAdapterRecyclerView;
import com.cosw.quicklyshop.dataholder.MainDataHolder;
import com.cosw.quicklyshop.model.Picture;
import com.cosw.quicklyshop.model.User;
import com.cosw.quicklyshop.view.EditProfileActivity;
import com.cosw.quicklyshop.view.PaymentActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private static final String TAG = "ProfileFragment";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //showToolbar("", false, view);

        //RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.picturesProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //picturesRecycler.setLayoutManager(linearLayoutManager);

//        PictureAdapterRecyclerView pictureAdapterRecyclerView =
//                new PictureAdapterRecyclerView(buidPictures(), R.layout.cardview_picture, getActivity());
        //picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        /* --------- */
        User user = MainDataHolder.getInstance().getUser();

        if (user != null) {
            TextView tvUser = (TextView) view.findViewById(R.id.tv_profile_username);
            tvUser.setText(user.getFirstname() + " " + user.getLastname());
        } else {
            Log.e(TAG, "El usuario es nulo");
        }

        Button editProfile = (Button) view.findViewById(R.id.edit);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        /* ---------- */

        return view;
    }

//    public ArrayList<Picture> buidPictures(){
//        ArrayList<Picture> pictures = new ArrayList<>();
//        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Uriel Ramírez", "4 días", "3 Me Gusta"));
//        pictures.add(new Picture("http://www.enjoyart.com/library/landscapes/tuscanlandscapes/large/Tuscan-Bridge--by-Art-Fronckowiak-.jpg", "Juan Pablo", "3 días", "10 Me Gusta"));
//        pictures.add(new Picture("http://www.educationquizzes.com/library/KS3-Geography/river-1-1.jpg", "Anahi Salgado", "2 días", "9 Me Gusta"));
//        return pictures;
//    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

}
