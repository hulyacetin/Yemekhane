package com.example.hulya.yemekhane.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hulya.yemekhane.R;
import com.example.hulya.yemekhane.viewmodel.AboutUsVM;

import java.util.ArrayList;

/**
 * Created by hulyacetin on 24.08.2017.
 */

public class RecyclerAboutUsAdapter extends RecyclerView.Adapter<RecyclerAboutUsViewHolder> implements View.OnClickListener {

    private ArrayList<AboutUsVM> listAboutUs = new ArrayList<AboutUsVM>();
    private String link;

    public RecyclerAboutUsAdapter(ArrayList<AboutUsVM> listAboutUs) {
        this.listAboutUs = listAboutUs;
    }


    @Override
    public RecyclerAboutUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_about_us, parent, false);


        return new RecyclerAboutUsViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final RecyclerAboutUsViewHolder holder, int position) {

        AboutUsVM selectedAboutUsVM = getItem(position);
        holder.person_name.setText(selectedAboutUsVM.getPerson_name());
        holder.person_information.setText(selectedAboutUsVM.getPerson_information());
        holder.github.setText(selectedAboutUsVM.getGithub());
        holder.linkedin.setText(selectedAboutUsVM.getLinkedin());
        holder.person_photo.setImageResource(selectedAboutUsVM.getPerson_photo());
        holder.github.setTag(selectedAboutUsVM.getGithub());
        holder.github.setOnClickListener(this);
        holder.linkedin.setTag(selectedAboutUsVM.getLinkedin());
        holder.linkedin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_about_us_textview_github1:
                link = (String) view.getTag();
                Uri link1 = Uri.parse(link);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, link1);
                view.getContext().startActivity(intent1);
                break;
            case R.id.activity_about_us_textview_linkedin1:
                link = (String) view.getTag();
                Uri link2 = Uri.parse(link);
                Intent intent2 = new Intent(Intent.ACTION_VIEW, link2);
                view.getContext().startActivity(intent2);
                break;
        }

    }

    private AboutUsVM getItem(int position) {

        return listAboutUs.get(position);

    }

    @Override
    public int getItemCount() {
        return listAboutUs.size();
    }


}
