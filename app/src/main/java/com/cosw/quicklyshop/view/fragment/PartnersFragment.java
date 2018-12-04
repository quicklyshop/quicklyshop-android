package com.cosw.quicklyshop.view.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosw.quicklyshop.R;
import com.cosw.quicklyshop.adapter.PictureAdapterRecyclerView;
import com.cosw.quicklyshop.model.Picture;
import com.cosw.quicklyshop.view.ContainerActivity;
import com.cosw.quicklyshop.view.codescanner.CodeScannerActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class PartnersFragment extends Fragment {


    public PartnersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buidPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CodeScannerActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Almacenes_exito_logo.svg/1028px-Almacenes_exito_logo.svg.png", " Exito"));
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/commons/3/3d/Carulla.png", "Carulla"));
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Logo_Jumbo_Cencosud.png/200px-Logo_Jumbo_Cencosud.png", "Jumbo"));
        pictures.add(new Picture("https://s3-eu-west-1.amazonaws.com/tpd/logos/4c91874300006400050dde6a/0x0.png", "Bershka"));
        pictures.add(new Picture("http://www.conmarca.com/archivos/espectacles_foto_gran/280614125626_pull-and-bear_gr.jpg", "Pull and Bear"));
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/H%26M-Logo.svg/245px-H%26M-Logo.svg.png", " H & M"));
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Falabella.svg/1280px-Falabella.svg.png", "Falabella"));
        pictures.add(new Picture("https://pbs.twimg.com/profile_images/971352713517821952/SvetxMXi_400x400.jpg", "Straduivarius"));
        pictures.add(new Picture("http://unicentrodearmenia.com/wp-content/uploads/2016/06/gef.png", "GEF"));
        return pictures;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


    }

}
