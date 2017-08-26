package com.example.hulya.yemekhane.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hulya.yemekhane.R;

/**
 * Created by hulyacetin on 24.08.2017.
 */

public class RecyclerAboutUsViewHolder extends RecyclerView.ViewHolder {

    public CardView cardview_cv = null;
    public ImageView person_photo = null;
    public TextView person_name = null;
    public TextView person_information = null;
    public TextView github, linkedin = null;


    public RecyclerAboutUsViewHolder(View itemView) {
        super(itemView);

        cardview_cv = itemView.findViewById(R.id.activity_about_us_cardview_cv1);
        person_photo = itemView.findViewById(R.id.activity_about_us_imageview_person_photo1);
        person_name = itemView.findViewById(R.id.activity_about_us_textview_person_name1);
        person_information = itemView.findViewById(R.id.activity_about_us_textview_person_information1);
        github = itemView.findViewById(R.id.activity_about_us_textview_github1);
        linkedin = itemView.findViewById(R.id.activity_about_us_textview_linkedin1);


    }
}
