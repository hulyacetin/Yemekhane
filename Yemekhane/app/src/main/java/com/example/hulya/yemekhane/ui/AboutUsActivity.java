package com.example.hulya.yemekhane.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.adapter.RecyclerAboutUsAdapter;
import com.example.hulya.yemekhane.dummydata.DeveloperInformationData;
import com.example.hulya.yemekhane.viewmodel.AboutUsVM;

import java.util.ArrayList;

/**
 * Created by hulyacetin on 23.08.2017.
 */

public class AboutUsActivity extends AppCompatActivity {
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private RecyclerView recyclerViewAboutUs = null;
    private ArrayList<AboutUsVM> listAboutUs = null;
    private RecyclerAboutUsAdapter recyclerAboutUsAdapter = null;
    private LinearLayoutManager aboutUsLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        aboutUsLayoutManager = new LinearLayoutManager(this);
        initview();



    }

    private void initview() {
        recyclerViewAboutUs = (RecyclerView) findViewById(R.id.about_us_recyclerview);
        listAboutUs = DeveloperInformationData.developerDummyList();
        recyclerAboutUsAdapter = new RecyclerAboutUsAdapter(listAboutUs);
        recyclerViewAboutUs.setAdapter(recyclerAboutUsAdapter);
        recyclerViewAboutUs.setLayoutManager(layoutManager);
        recyclerViewAboutUs.setItemAnimator(new DefaultItemAnimator());


    }

}

