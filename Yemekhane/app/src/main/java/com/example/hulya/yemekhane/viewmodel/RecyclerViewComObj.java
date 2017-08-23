package com.example.hulya.yemekhane.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mbura on 23.08.2017.
 */

public class RecyclerViewComObj implements Parcelable {

    public static final Creator<RecyclerViewComObj> CREATOR = new Creator<RecyclerViewComObj>() {
        @Override
        public RecyclerViewComObj createFromParcel(Parcel in) {
            return new RecyclerViewComObj(in);
        }

        @Override
        public RecyclerViewComObj[] newArray(int size) {
            return new RecyclerViewComObj[size];
        }
    };
    // private ArrayList<Map<String,ArrayList<FoodListVM>>> list=new ArrayList<>();
    private Map<String, ArrayList<FoodListVM>> mapFoodList = new HashMap<>();

    public RecyclerViewComObj() {

    }

    private RecyclerViewComObj(Parcel in) {

        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            ArrayList<FoodListVM> value = in.readArrayList(null);
            mapFoodList.put(key, value);
        }
    }

    public static Creator<RecyclerViewComObj> getCREATOR() {
        return CREATOR;
    }

    public Map<String, ArrayList<FoodListVM>> getMapFoodList() {
        return mapFoodList;
    }

    public void setMapFoodList(Map<String, ArrayList<FoodListVM>> mapFoodList) {
        this.mapFoodList = mapFoodList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(mapFoodList.size());
        for (Map.Entry<String, ArrayList<FoodListVM>> entry : mapFoodList.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeList(entry.getValue());
        }
    }
}
