package com.example.hulya.yemekhane.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.viewmodel.FoodListVM;

import java.util.ArrayList;

/**
 * Created by hulya on 14.08.2017.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodListViewHolder> {
    private ArrayList<FoodListVM> foodList = new ArrayList<FoodListVM>();

    public FoodListAdapter(ArrayList<FoodListVM> foodList, SwipeRefreshLayout swiper, Context context) {
        this.foodList = foodList;
        this.swiper = swiper;
        this.context = context;
    }

    public FoodListAdapter(RecyclerView recyclerView, ArrayList<FoodListVM> foodList) {
    }

    public FoodListAdapter(ArrayList<FoodListVM> foodList) {
        this.foodList = foodList;
    }


    @Override
    public FoodListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_foodlist, parent, false);

        return new FoodListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FoodListViewHolder holder, int position) {
        FoodListVM selectedFoodListVM = getItem(position);
        holder.foodType.setText(selectedFoodListVM.getFoodType());
        holder.txtFood1.setText(selectedFoodListVM.getFoodName1());
        holder.cardView1.setBackgroundResource(selectedFoodListVM.getFoodImageLink1());
        holder.txtFood2.setText(selectedFoodListVM.getFoodName2());
        holder.cardView2.setBackgroundResource(selectedFoodListVM.getFoodImageLink2());
        holder.txtFood3.setText(selectedFoodListVM.getFoodName3());
        holder.cardView3.setBackgroundResource(selectedFoodListVM.getFoodImageLink3());


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    private void refresh() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                ArrayList<FoodListVM> foodList = new ArrayList<>();

                //foodList.add(0,foodList.get(new Random().nextInt(foodList.size())));
                addall(foodList);
                FoodListAdapter.this.notifyDataSetChanged();
                swiper.setRefreshing(false);
            }
        }, 1000);
    }

    private void addall(ArrayList<FoodListVM> foodList) {

        foodList.addAll(foodList);

    }

    private FoodListVM getItem(int position){
        return foodList.get(position);
    }
}
