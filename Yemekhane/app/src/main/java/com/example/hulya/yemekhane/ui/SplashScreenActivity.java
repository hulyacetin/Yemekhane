package com.example.hulya.yemekhane.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.controller.AppController;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.felipecsl.gifimageview.library.GifHeaderParser.TAG;

public class SplashScreenActivity extends Activity {

    //variable defines
    private Map<String, ArrayList<FoodListVM>> mapFoodList = new HashMap<>();
    private ArrayList<FoodListVM> foodList = new ArrayList<>();
    private String child = "Day1";
    private int dayCount = 1;
    //component defines
    private NetworkImageView imgNetWorkView;
    private GifImageView garfieldGif;
    //firebase reference value defines
    private Firebase foodListRef;
    private ImageView imageview;
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
        recyclerViewComObj.setMapFoodList(mapFoodList);

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
                    sendDataToRecyclerViewActivity();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void makeImageRequest(String imageURL) {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using NetworkImageView
        //imgNetWorkView.setImageUrl(imageURL, imageLoader);


        // If you are using normal ImageView
        imageLoader.get(imageURL, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    imageview.setImageBitmap(response.getBitmap());
                }
            }
        });

        // Loading image with placeholder and error image
        imageLoader.get(imageURL, ImageLoader.getImageListener(imgNetWorkView, R.drawable.placeholder_rev, R.drawable.ico_error));

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(imageURL);
        if (entry != null) {
            try {
                String imageData = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            // cached response doesn't exists. Make a network call here
        }

    }
}