package com.example.hulya.yemekhane.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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

public class RecyclerViewActivity extends AppCompatActivity implements ValueEventListener, SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swiper;
    FoodListAdapter foodListAdapter;
    Map<String, ArrayList<FoodListVM>> map1 = new HashMap<>();
    //variables define
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

        GetData(i);
        swiper.setOnRefreshListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                foodListRef = new Firebase("https://fooddata-e0a85.firebaseio.com/");
                foodList = new ArrayList<FoodListVM>();

                switch (position){
                    case 0:
                        day = "Day1";
                        break;
                    case 1:
                        day = "Day2";
                        break;
                    case 2:
                        day = "Day3";
                        break;
                    case 3:
                        day = "Day4";
                        break;
                    case 4:
                        day = "Day5";
                        break;
                    case 5:
                        day = "Day6";
                        break;
                    case 6:
                        day = "Day7";
                        break;

                }
                foodListRef.child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FoodListVM foodListVM = new FoodListVM();



                        foodListVM.setFoodType("ÇORBALAR");
                        foodListVM.setFoodName1(dataSnapshot.child("Soup1").getValue().toString());
                        foodListVM.setFoodName2(dataSnapshot.child("Soup2").getValue().toString());
                        foodListVM.setFoodImageLink1(R.mipmap.nanelicorba);
                        foodListVM.setFoodImageLink2(R.mipmap.telsehriye2);
                        foodList.add(foodListVM);

                        foodListVM = new FoodListVM();
                        foodListVM.setFoodType("BAŞLANGIÇ");
                        foodListVM.setFoodName1(dataSnapshot.child("Entree1").getValue().toString());
                        foodListVM.setFoodName2(dataSnapshot.child("Entree2").getValue().toString());
                        foodListVM.setFoodImageLink1(R.mipmap.pilav2);
                        foodListVM.setFoodImageLink2(R.mipmap.soslumakarna);
                        foodList.add(foodListVM);

                        foodListVM = new FoodListVM();
                        foodListVM.setFoodType("ANA YEMEK");
                        foodListVM.setFoodName1(dataSnapshot.child("MainFood1").getValue().toString());
                        foodListVM.setFoodName2(dataSnapshot.child("MainFood2").getValue().toString());
                        foodListVM.setFoodName3(dataSnapshot.child("MainFood3").getValue().toString());
                        foodListVM.setFoodImageLink1(R.mipmap.soslukofte);
                        foodListVM.setFoodImageLink2(R.mipmap.fajita);
                        foodListVM.setFoodImageLink3(R.mipmap.bamya);
                        foodList.add(foodListVM);

                        foodListVM = new FoodListVM();
                        foodListVM.setFoodType("ALTERNATİF");
                        foodListVM.setFoodName1(dataSnapshot.child("Alternatif1").getValue().toString());
                        foodListVM.setFoodName2(dataSnapshot.child("Alternatif2").getValue().toString());
                        foodListVM.setFoodName3(dataSnapshot.child("Alternatif3").getValue().toString());
                        foodListVM.setFoodImageLink1(R.mipmap.kumru);
                        foodListVM.setFoodImageLink2(R.mipmap.ayran);
                        foodListVM.setFoodImageLink3(R.mipmap.specialsalata);

                        foodList.add(foodListVM);


                        map1.put(day,foodList);
                        foodListAdapter = new FoodListAdapter(map1.get(day));
                        rFoodList.setAdapter(foodListAdapter);
                        foodListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

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

    public void dateInformation() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("EEEE-dd/MM/yyyy");
        txtdateInformation.setText(dateformat.format(date).toString());

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

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

        spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, days);
        spinner.setAdapter(spAdapter);
        //(Arrays.toString(days));


//        dates_spinner.add(dataSnapshot.child("Date").getValue().toString());
//        spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dates_spinner);
//        spinner.setAdapter(spAdapter);
        i++;

        Date date1 = new Date();
        DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        default_date = format1.format(date1).toString();
        spinner_position = spAdapter.getPosition(default_date);
        spinner.setSelection(spinner_position);

        if (i<8)
            GetData(i);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
    }
    public void GetData(int i){
        foodListRef = new Firebase("https://fooddata-e0a85.firebaseio.com/");
        switch (i)
        {
            case 1:
                day="Day1";
                break;
            case 2:
                day="Day2";
                break;
            case 3:
                day="Day3";
                break;
            case 4:
                day="Day4";
                break;
            case 5:
                day="Day5";
                break;
            case 6:
                day="Day6";
                break;
            case 7:
                day="Day7";
                break;
        }
        foodListRef.child(day).addListenerForSingleValueEvent(this);
    }

    @Override
    public void onRefresh() {
        foodList.clear();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                i = 1;
                dates_spinner.clear();
                GetData(i);
                swiper.setRefreshing(false);

            }
        }, 1000);
    }
}






