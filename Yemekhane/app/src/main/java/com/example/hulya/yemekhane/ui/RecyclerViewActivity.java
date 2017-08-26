package com.example.hulya.yemekhane.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.adapter.FoodListAdapter;
import com.example.hulya.yemekhane.viewmodel.FoodListVM;
import com.example.hulya.yemekhane.viewmodel.RecyclerViewComObj;
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

public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swiper;
    //adapter defines
    FoodListAdapter foodListAdapter;
    private ArrayAdapter<String> spAdapter;
    //variables define
    private Map<String, ArrayList<FoodListVM>> mapFoodList = new HashMap<>();
    private ArrayList<FoodListVM> foodList;
    private int selectedItemPosition = 1;
    private int spinner_position = 0;
    private int dayCount = 1;
    private String default_date;
    private String child = "Day1";
    //component defines
    private Toolbar toolbar;
    private RecyclerView rFoodList = null;
    private TextView txtdateInformation;
    private Spinner spinner;
    private LinearLayoutManager mLayoutManager;
    //remote client define
    private Firebase foodListRef;


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

        mapFoodList = recyclerViewComObj.getMapFoodList();

        getDateTime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                foodListAdapter = new FoodListAdapter(mapFoodList.get("Day" + (position + 1)));
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
        spinner = (Spinner) findViewById(R.id.spinner);
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

        Date date1 = new Date();
        DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        default_date = format1.format(date1);
        spinner_position = spAdapter.getPosition(default_date);
        spinner.setSelection(spinner_position);
    }

    private void dateInformation() {
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("EEEE-dd/MM/yyyy");
        txtdateInformation.setText(dateformat.format(date));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_action_about_us, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about_us:
                openAbout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAbout() {
        Intent intent_about_us_activity = new Intent(RecyclerViewActivity.this, AboutUsActivity.class);
        startActivity(intent_about_us_activity);
    }

    @Override
    public void onRefresh() {
        foodListRef = new Firebase("https://fooddata-e0a85.firebaseio.com/");
        selectedItemPosition = spinner.getSelectedItemPosition();
        dayCount = 1;
        getData();
    }

    public void getData() {

        foodListRef.child(child).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FoodListVM foodListVM = new FoodListVM();
                foodList = new ArrayList<>();

                foodListVM.setFoodType("ÇORBALAR");
                foodListVM.setFoodName1(dataSnapshot.child("Soup1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("Soup2").getValue().toString());
                if (TextUtils.isEmpty(dataSnapshot.child("Soup1").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink1(null);
                } else {
                    foodListVM.setFoodNetworkImageLink1(dataSnapshot.child("SoupImage1").getValue().toString());
                }

                if (TextUtils.isEmpty(dataSnapshot.child("Soup2").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink2(null);
                } else {
                    foodListVM.setFoodNetworkImageLink2(dataSnapshot.child("SoupImage2").getValue().toString());
                }

                if (TextUtils.isEmpty(dataSnapshot.child("Soup3").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink3(null);
                } else {
                    foodListVM.setFoodNetworkImageLink3(dataSnapshot.child("SoupImage3").getValue().toString());
                }
                foodList.add(foodListVM);

                foodListVM = new FoodListVM();
                foodListVM.setFoodType("BAŞLANGIÇ");
                foodListVM.setFoodName1(dataSnapshot.child("Entree1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("Entree2").getValue().toString());
                if (TextUtils.isEmpty(dataSnapshot.child("Entree1").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink1(null);
                } else {
                    foodListVM.setFoodNetworkImageLink1(dataSnapshot.child("EntreeImage1").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Entree2").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink2(null);
                } else {
                    foodListVM.setFoodNetworkImageLink2(dataSnapshot.child("EntreeImage2").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Entree3").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink3(null);
                } else {
                    foodListVM.setFoodNetworkImageLink3(dataSnapshot.child("EntreeImage3").getValue().toString());
                }
                foodList.add(foodListVM);

                foodListVM = new FoodListVM();
                foodListVM.setFoodType("ANA YEMEK");
                foodListVM.setFoodName1(dataSnapshot.child("MainFood1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("MainFood2").getValue().toString());
                foodListVM.setFoodName3(dataSnapshot.child("MainFood3").getValue().toString());
                if (TextUtils.isEmpty(dataSnapshot.child("MainFood1").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink1(null);
                } else {
                    foodListVM.setFoodNetworkImageLink1(dataSnapshot.child("MainFoodImage1").getValue().toString());
                }

                if (TextUtils.isEmpty(dataSnapshot.child("MainFood2").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink2(null);
                } else {
                    foodListVM.setFoodNetworkImageLink2(dataSnapshot.child("MainFoodImage2").getValue().toString());
                }


                if (TextUtils.isEmpty(dataSnapshot.child("MainFood3").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink3(null);
                } else {
                    foodListVM.setFoodNetworkImageLink3(dataSnapshot.child("MainFoodImage3").getValue().toString());
                }

                foodList.add(foodListVM);

                foodListVM = new FoodListVM();
                foodListVM.setFoodType("ALTERNATİF");
                foodListVM.setFoodName1(dataSnapshot.child("Alternatif1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("Alternatif2").getValue().toString());
                foodListVM.setFoodName3(dataSnapshot.child("Alternatif3").getValue().toString());

                if (TextUtils.isEmpty(dataSnapshot.child("Alternatif1").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink1(null);
                } else {
                    foodListVM.setFoodNetworkImageLink1(dataSnapshot.child("AlternatifFoodImageLink1").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Alternatif2").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink2(null);
                } else {
                    foodListVM.setFoodNetworkImageLink2(dataSnapshot.child("AlternatifFoodImageLink2").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Alternatif3").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink3(null);
                } else {
                    foodListVM.setFoodNetworkImageLink3(dataSnapshot.child("AlternatifFoodImageLink3").getValue().toString());
                }
                if ((TextUtils.isEmpty(dataSnapshot.child("Alternatif1").getValue().toString())) && (TextUtils.isEmpty(dataSnapshot.child("Alternatif2").getValue().toString())) && (TextUtils.isEmpty(dataSnapshot.child("Alternatif3").getValue().toString()))) {
                    foodListVM.setFoodType(null);
                }

                foodList.add(foodListVM);
                foodListVM = new FoodListVM();
                foodListVM.setFoodType("ZY BÜFESİ");
                foodListVM.setFoodName1(dataSnapshot.child("ZYBufe1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("ZYBufe2").getValue().toString());
                foodListVM.setFoodName3(dataSnapshot.child("ZYBufe3").getValue().toString());
                if (TextUtils.isEmpty(dataSnapshot.child("ZYBufe1").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink1(null);
                } else {
                    foodListVM.setFoodNetworkImageLink1(dataSnapshot.child("ZYBufeImage1").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("ZYBufe2").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink2(null);
                } else {
                    foodListVM.setFoodNetworkImageLink2(dataSnapshot.child("ZYBufeImage2").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("ZYBufe3").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink3(null);
                } else {
                    foodListVM.setFoodNetworkImageLink3(dataSnapshot.child("ZYBufeImage3").getValue().toString());
                }
                foodList.add(foodListVM);

                foodListVM = new FoodListVM();
                foodListVM.setFoodType("TATLI BÜFESİ");
                foodListVM.setFoodName1(dataSnapshot.child("Desert1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("Desert2").getValue().toString());
                foodListVM.setFoodName3(dataSnapshot.child("Desert3").getValue().toString());
                if (TextUtils.isEmpty(dataSnapshot.child("Desert1").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink1(null);
                } else {
                    foodListVM.setFoodNetworkImageLink1(dataSnapshot.child("DesertImage1").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Desert2").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink2(null);
                } else {
                    foodListVM.setFoodNetworkImageLink2(dataSnapshot.child("DesertImage2").getValue().toString());
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Desert3").getValue().toString())) {
                    foodListVM.setFoodNetworkImageLink3(null);
                } else {
                    foodListVM.setFoodNetworkImageLink3(dataSnapshot.child("DesertImage3").getValue().toString());
                }
                foodList.add(foodListVM);

                mapFoodList.put(child, foodList);

                dayCount++;

                if (dayCount < 8) {
                    child = "Day" + dayCount;
                    getData();
                } else {
                    foodListAdapter = new FoodListAdapter(mapFoodList.get("Day" + (selectedItemPosition + 1)));
                    rFoodList.setAdapter(foodListAdapter);
                    swiper.setRefreshing(false);
                }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
