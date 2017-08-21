package com.example.hulya.yemekhane.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hulya.yemekhane.R;

/**
 * Created by hulya on 14.08.2017.
 */

public class FoodListViewHolder extends  RecyclerView.ViewHolder {
    public TextView foodType = null;
    public TextView txtFood1 = null;
    public TextView txtFood2 = null;
    public TextView txtFood3 = null;
    public CardView cardView1 = null;
    public CardView cardView2 = null;
    public CardView cardView3 = null;

    public FoodListViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    public void initView(){
        foodType = itemView.findViewById(R.id.foodType);
        txtFood1 = itemView.findViewById(R.id.txtfood1);
        txtFood2 = itemView.findViewById(R.id.txtfood2);
        txtFood3 = itemView.findViewById(R.id.txtfood3);
        cardView1 = itemView.findViewById(R.id.card_view1);
        cardView2 = itemView.findViewById(R.id.card_view2);
        cardView3 = itemView.findViewById(R.id.card_view3);
    }
}
