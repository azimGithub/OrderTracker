package com.example.azim.ordertracker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.model.appData.Amast;

public class MenuItem extends AppCompatActivity {
    private String areaCodePrev;
    private String areaNamePrev;
    private String address;
    String lat;
    String lng;
    private int id;
    private String acode;
    private Amast   aMast;
    private String storeName;
    private TextView tvTitle;
    private int storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_related_task);
        
        
        init();
    }

    private void init() {


        Intent intent = getIntent();
        if(intent!=null){

            areaCodePrev=  intent.getStringExtra("areaCode");
            areaNamePrev=      intent.getStringExtra("areaName");
            storeName   =  intent.getStringExtra("storeName");
            lat = intent.getStringExtra("lat");
            lng = intent.getStringExtra("lang");
            id = intent.getIntExtra("id", 0);
            acode = intent.getStringExtra("storeCode");
            address = intent.getStringExtra("address");
        }


        tvTitle = (TextView)findViewById(R.id.toolbar_title);
        tvTitle.setText(storeName);

//        String  storeDetail= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.STORE_SELECTED,"");
//        if(storeDetail!=null && !storeDetail.isEmpty()) {
//
//            Gson gson = new Gson();
//
//            Type type = new TypeToken<Amast>() {
//            }.getType();
//            aMast = gson.fromJson(storeDetail, type);
//
//            if (aMast != null) {
//
//                TextView teTitle = (TextView) findViewById(R.id.teTitle);
//                String nameStore = aMast.name;
//                if (nameStore != null && !nameStore.isEmpty())
//                    teTitle.setText(nameStore);
//            }
//        }
        
        
    }

    public void setProductMasterList(View view) {
        Intent intent = new Intent(this, ItemMasterList.class);
        intent.putExtra("areaCode",areaCodePrev);
        intent.putExtra("areaName",areaNamePrev);
        intent.putExtra("storeName",  storeName);
        intent.putExtra("storeCode",acode);
        intent.putExtra("id", id);
        intent.putExtra("lat", lat);
        intent.putExtra("lng",lng);
        intent.putExtra("address", address);
        startActivity(intent);
    }
}
