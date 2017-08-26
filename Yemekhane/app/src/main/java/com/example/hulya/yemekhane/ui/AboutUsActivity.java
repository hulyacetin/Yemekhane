package com.example.hulya.yemekhane.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.adapter.RecyclerAboutUsAdapter;
import com.example.hulya.yemekhane.adapter.RecyclerAboutUsViewHolder;
import com.example.hulya.yemekhane.dummydata.DeveloperInformationData;
import com.example.hulya.yemekhane.viewmodel.AboutUsVM;
import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by hulyacetin on 23.08.2017.
 */

public class AboutUsActivity extends AppCompatActivity {

    private RecyclerView recyclerAboutUs = null;
    private ArrayList<AboutUsVM> listAboutUs = null;
    private RecyclerAboutUsAdapter recyclerAboutUsAdapter = null;
    private RecyclerView.LayoutManager aboutUsLayoutManager;
    private AboutUsVM aboutUsVM;
    private RecyclerAboutUsViewHolder recyclerAboutUsViewHolder;
    private View view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initview();
        //recyclerAboutUsViewHolder = new RecyclerAboutUsViewHolder();
        recyclerAboutUsViewHolder.github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri link1 = Uri.parse(aboutUsVM.getGithub());
                Intent intent1 = new Intent(Intent.ACTION_VIEW, link1);
                startActivity(intent1);

            }
        });

        recyclerAboutUsViewHolder.linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri link2 = Uri.parse(aboutUsVM.getLinkedin());
                Intent intent2 = new Intent(Intent.ACTION_VIEW, link2);
                startActivity(intent2);

            }
        });

    }

    private void initview() {

        recyclerAboutUs = (RecyclerView)findViewById(R.id.recycler_about_us);

        aboutUsLayoutManager = new LinearLayoutManager(AboutUsActivity.this);
        recyclerAboutUs.setLayoutManager(aboutUsLayoutManager);
        recyclerAboutUs.setItemAnimator(new DefaultItemAnimator());

        listAboutUs = DeveloperInformationData.developerDummyList();

        RecyclerAboutUsAdapter recyclerAboutUsAdapter = new RecyclerAboutUsAdapter(listAboutUs);
        recyclerAboutUs.setAdapter(recyclerAboutUsAdapter);

    }

}

