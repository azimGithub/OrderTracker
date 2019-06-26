package com.example.azim.ordertracker.view;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.azim.ordertracker.model.appData.Itmast;

import java.util.ArrayList;

public class OredrList implements Parcelable {

    private ArrayList<Itmast> orderList = new ArrayList<>();

    private OredrList(Parcel in) {

    }

    public static final Creator<OredrList> CREATOR = new Creator<OredrList>() {
        @Override
        public OredrList createFromParcel(Parcel in) {
            return new OredrList(in);
        }

        @Override
        public OredrList[] newArray(int size) {
            return new OredrList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeList(orderList);
    }
}
