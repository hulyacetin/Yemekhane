package com.example.hulya.yemekhane.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.controller.AppController;
import com.example.hulya.yemekhane.viewmodel.FoodListVM;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by hulya on 14.08.2017.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodListViewHolder> {
    private ArrayList<FoodListVM> foodList = new ArrayList<FoodListVM>();
    private ImageLoader foodImageLoader = AppController.getInstance().getImageLoader();
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
        if (Objects.equals(selectedFoodListVM.getFoodNetworkImageLink1(), null)) {
            holder.cardView1.setVisibility(View.GONE);
        } else {
            holder.networkImageView_food1.setImageUrl(selectedFoodListVM.getFoodNetworkImageLink1(), foodImageLoader);
        }

        holder.txtFood2.setText(selectedFoodListVM.getFoodName2());
        if (Objects.equals(selectedFoodListVM.getFoodNetworkImageLink2(), null)) {
            holder.cardView2.setVisibility(View.GONE);
        } else {
            holder.networkImageView_food2.setImageUrl(selectedFoodListVM.getFoodNetworkImageLink2(), foodImageLoader);
        }

        holder.txtFood3.setText(selectedFoodListVM.getFoodName3());

        if (Objects.equals(selectedFoodListVM.getFoodNetworkImageLink3(), null)) {
            holder.cardView3.setVisibility(View.GONE);
        } else {
            holder.networkImageView_food3.setImageUrl(selectedFoodListVM.getFoodNetworkImageLink3(), foodImageLoader);
        }

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
    private FoodListVM getItem(int position){
        return foodList.get(position);
    }
}
