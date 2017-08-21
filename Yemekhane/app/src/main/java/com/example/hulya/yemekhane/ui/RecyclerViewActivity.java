package com.example.hulya.yemekhane.ui;


import android.content.Context;
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
import com.example.hulya.yemekhane.dummydata.FirebaseDataList;
import com.example.hulya.yemekhane.viewmodel.FoodListVM;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerViewActivity extends AppCompatActivity implements ValueEventListener {

    //variables define
    private ArrayList<FoodListVM> foodList ;
    private ArrayList<String> dates_spinner=new ArrayList<>();
    private String day;
    private int i=1;
    //component defines
    private Toolbar toolbar;
    SwipeRefreshLayout swiper;
    private RecyclerView rFoodList = null;
    private ArrayList<FoodListVM> foodList = null;
    private TextView txtDateInformation;
    private Spinner spinner;
    private LinearLayoutManager mLayoutManager;
    //remote client dafine
    private Firebase foodListRef;

    private ArrayAdapter<String> spAdapter;

    FoodListAdapter foodListAdapter;
    Map<String, ArrayList<FoodListVM>> map1 = new HashMap<>();


    private Map<String, FirebaseDataList> firebaseDataListMap;
    private String day;
    private int i = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
      
        initView();

        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtDateInformation = (TextView) findViewById(R.id.txtDateInformation);
        toolbar.setTitleTextColor(Color.WHITE);
        swiper = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        Firebase.setAndroidContext(this);

        setSupportActionBar(toolbar);

        initView();
      
        GetData(i);
      
        DateInformation();
      
        GetData(i);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                foodListRef = new Firebase("https://refectory-84b81.firebaseio.com/");
                foodList=new ArrayList<FoodListVM>();
                switch (position){
                    case 0:
                        day="Day1";
                        break;
                    case 1:
                        day="Day2";
                        break;
                    case 2:
                        day="Day3";
                        break;
                    case 3:
                        day="Day4";
                        break;
                    case 4:
                        day="Day5";
                        break;
                    case 5:
                        day="Day6";
                        break;
                    case 6:
                        day="Day7";
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
//                foodListAdapter = new FoodListAdapter(map1.get(day));
//                rFoodList.setAdapter(foodListAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView() {
        txtDateInformation = (TextView) findViewById(R.id.txtDateInformation);
        rFoodList = (RecyclerView) findViewById(R.id.activity_recycler_view_foodList);
        mLayoutManager = new LinearLayoutManager(this);
        rFoodList.setLayoutManager(mLayoutManager);
        rFoodList.setItemAnimator(new DefaultItemAnimator());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtDateInformation = (TextView) findViewById(R.id.txtDateInformation);
        spinner= (Spinner) findViewById(R.id.spinner);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void DateInformation() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("EEEE-dd/MM/yyyy");
        txtDateInformation.setText(format.format(date).toString());
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        FoodListVM foodListVM = new FoodListVM();

        List<Map<String, List<FoodListVM>>> mapListfoodList = new ArrayList<>();
        Map<String, List<FoodListVM>> map1 = new HashMap<>();

        List<FoodListVM> foodList = new ArrayList<>();


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
        mapListfoodList.add(map1);

        Context context = null;
        FoodListAdapter foodListAdapter = new FoodListAdapter((ArrayList<FoodListVM>) foodList, swiper, this);

        rFoodList.setAdapter(foodListAdapter);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
    public void GetData(int i){
        foodListRef = new Firebase("https://refectory-84b81.firebaseio.com/");
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
    public void GetData(int i){
        foodListRef = new Firebase("https://refectory-84b81.firebaseio.com/");foodListRef.child("Day6").addListenerForSingleValueEvent(this);
    }


}






