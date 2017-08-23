package com.example.hulya.yemekhane.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.adapter.FoodListAdapter;
import com.example.hulya.yemekhane.viewmodel.FoodListVM;
import com.example.hulya.yemekhane.viewmodel.RecyclerViewComObj;
import com.felipecsl.gifimageview.library.GifImageView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity extends Activity {

    //variable defines
    Map<String, ArrayList<FoodListVM>> map1 = new HashMap<>();
    private ArrayList<FoodListVM> foodList = new ArrayList<>();
    private String child = "Day1";
    private int dayCount = 1;
    //component defines
    private GifImageView garfieldGif;
    //adapter defines
    private FoodListAdapter foodListAdapter;
    //firebase reference value defines
    private Firebase foodListRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Firebase.setAndroidContext(this);
        foodListRef = new Firebase("https://fooddata-e0a85.firebaseio.com/");
        navi();
        getData();
        try {
            InputStream inputStream = getAssets().open("garfiled_fork_and_knife.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            garfieldGif.setBytes(bytes);
            garfieldGif.startAnimation();
        } catch (IOException ex) {

        }
    }

    private void sendDataToRecyclerViewActivity() {

        Intent intent = new Intent(SplashScreenActivity.this, RecyclerViewActivity.class);

        RecyclerViewComObj recyclerViewComObj = new RecyclerViewComObj();
        recyclerViewComObj.setMapFoodList(map1);

        intent.putExtra("datas", recyclerViewComObj);

        startActivity(intent);

        finish();
    }

    public void navi() {

        garfieldGif = findViewById(R.id.garfield);
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

                if (TextUtils.isEmpty(dataSnapshot.child("MainFood3").getValue().toString())) {
                    foodListVM.setFoodImageLink3(-1);
                } else {
                    foodListVM.setFoodImageLink3(R.mipmap.bamya);
                }

                foodList.add(foodListVM);

                foodListVM = new FoodListVM();
                foodListVM.setFoodType("ALTERNATİF");
                foodListVM.setFoodName1(dataSnapshot.child("Alternatif1").getValue().toString());
                foodListVM.setFoodName2(dataSnapshot.child("Alternatif2").getValue().toString());
                foodListVM.setFoodName3(dataSnapshot.child("Alternatif3").getValue().toString());
                if (TextUtils.isEmpty(dataSnapshot.child("Alternatif1").getValue().toString())) {
                    foodListVM.setFoodImageLink1(-1);
                } else {
                    foodListVM.setFoodImageLink1(R.mipmap.kumru);
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Alternatif2").getValue().toString())) {
                    foodListVM.setFoodImageLink2(-1);
                } else {
                    foodListVM.setFoodImageLink2(R.mipmap.ayran);
                }
                if (TextUtils.isEmpty(dataSnapshot.child("Alternatif3").getValue().toString())) {
                    foodListVM.setFoodImageLink3(-1);
                } else {
                    foodListVM.setFoodImageLink3(R.mipmap.specialsalata);
                }
                foodList.add(foodListVM);

                map1.put(child, foodList);

                dayCount++;

                if (dayCount < 8) {

                    child = "Day" + dayCount;
                    getData();
                } else {

                    sendDataToRecyclerViewActivity();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}

