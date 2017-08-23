package com.example.hulya.yemekhane.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.adapter.FoodListAdapter;
import com.example.hulya.yemekhane.viewmodel.FoodListVM;
import com.example.hulya.yemekhane.viewmodel.RecyclerViewComObj;
import com.firebase.client.Firebase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */

public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swiper;


    //variables define
    Map<String, ArrayList<FoodListVM>> map1 = new HashMap<>();
    //adapter defines
    FoodListAdapter foodListAdapter;
    private ArrayList<FoodListVM> foodList ;
    private ArrayList<String> dates_spinner=new ArrayList<>();
    private String day;
    private int i=1;
    private int spinner_position = 0;
    private String default_date;
    //component defines
    private Toolbar toolbar;
    private RecyclerView rFoodList = null;
    private TextView txtdateInformation;
    private Spinner spinner;
    private LinearLayoutManager mLayoutManager;
    //remote client define
    private Firebase foodListRef;
    private ArrayAdapter<String> spAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        Firebase.setAndroidContext(this);
        dateInformation();

        swiper.setOnRefreshListener(this);

        RecyclerViewComObj recyclerViewComObj = getIntent().getParcelableExtra("datas");

        map1 = recyclerViewComObj.getMapFoodList();

        getDateTime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                foodListAdapter = new FoodListAdapter(map1.get("Day" + (position + 1)));
                rFoodList.setAdapter(foodListAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView() {
        txtdateInformation = (TextView) findViewById(R.id.txtDateInformation);
        rFoodList = (RecyclerView) findViewById(R.id.activity_recycler_view_foodList);
        mLayoutManager = new LinearLayoutManager(this);
        rFoodList.setLayoutManager(mLayoutManager);
        rFoodList.setItemAnimator(new DefaultItemAnimator());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner= (Spinner) findViewById(R.id.spinner);
        swiper = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
    }

    private void getDateTime() {

        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(GregorianCalendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        spinner.setAdapter(spAdapter);

        i++;
        Date date1 = new Date();
        DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        default_date = format1.format(date1).toString();
        spinner_position = spAdapter.getPosition(default_date);
        spinner.setSelection(spinner_position);
    }

    private void dateInformation() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("EEEE-dd/MM/yyyy");
        txtdateInformation.setText(dateformat.format(date).toString());

    }

    @Override
    public void onRefresh() {

        //foodlisti çekme requesti atilacak
        // refreshing false kodu da cevabın geldigi yere tassınacak.
        // ve adapter guncellenecek.
        swiper.setRefreshing(false);
    }
}






