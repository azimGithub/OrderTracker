package com.example.azim.ordertracker.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.model.appData.Amast;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAddress extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TAG = "request_tag";
    private FusedLocationProviderClient mFusedLocationClient;

    EditText inputSearch;
    private String token;
    private String areaCodePrev;
    private String areaNamePrev;
    private String acode;
    private String storeCode;
    private String areaCd;
    private int id;
    private int storeId;
    private String lat;
    private String lng;
    private String address1;
    private Double locationLat;
    private Double locationLng;
    Amast   areaMaster;
    //private TextView tvStoereName,tvAddress,tvCrLimit, tvCrDays, tvFreq;
    @BindView(R.id.crDays)TextView tvCrDays;
    @BindView(R.id.crLimit)TextView tvCrLimit;
    @BindView(R.id.tvStoereName)TextView tvStoereName;
    @BindView(R.id.add1)TextView tvAddress1;
    @BindView(R.id.add2) TextView tvAddress2;
    @BindView(R.id.add3)TextView tvAddress3;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.areaName)TextView areaName;
    @BindView(R.id.city)TextView city;
    @BindView(R.id.pinCode)TextView pin;
    @BindView(R.id.contactPerson)TextView contactPerson;
    @BindView(R.id.phone1)TextView phone1;
    @BindView(R.id.phone2)TextView phone2;
    @BindView(R.id.phone3)TextView phone3;
    @BindView(R.id.longitude)TextView longitude;
    @BindView(R.id.latitude)TextView latitude;
    @BindView(R.id.outAmt)TextView outBal;
    @BindView(R.id.freq)TextView freq;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_address);
        ButterKnife.bind(this);
        init();
        initListener();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
    }

    private void init() {

        Intent intent = getIntent();
        if(intent!=null){

            areaCodePrev= intent.getStringExtra("areaCode");
            areaNamePrev= intent.getStringExtra("areaName");
            acode = intent.getStringExtra("storeCode");
            id = intent.getIntExtra("id", 0);
            //areaCd = intent.getStringExtra("areaCd");
            areaName.setText(areaNamePrev);
        }
        String  storeDetail= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.STORE_SELECTED,"");
        if(storeDetail!=null && !storeDetail.isEmpty()){

            Gson gson= new Gson();

            Type type = new TypeToken<Amast>() {}.getType();
            areaMaster = gson.fromJson(storeDetail, type);

            if(areaMaster!=null){

//                storeId = areaMaster.id;
//                storeCode = areaMaster.acode;

                String nameStore=    areaMaster.name;
                if(nameStore!=null && !nameStore.isEmpty())
                    tvTitle.setText(nameStore);
            }

            String nameStore=    areaMaster.name;
            if(nameStore!=null && !nameStore.isEmpty()){
                tvStoereName.setText(nameStore);
            }

            address1=    areaMaster.add1;
            if(address1!=null && !address1.isEmpty()){
                tvAddress1.setText(address1);
            }

            String address2=    areaMaster.add2;
            if(address2!=null && !address2.isEmpty()){
                tvAddress2.setVisibility(View.VISIBLE);
                tvAddress2.setText(", " + address2);
            }

            String address3=    areaMaster.add3;
            if(address3!=null && !address3.isEmpty()){
                tvAddress3.setVisibility(View.VISIBLE);
                tvAddress3.setText(address3);
            }
            String cityName = areaMaster.city;
            if (cityName!=null && !cityName.isEmpty()){
                city.setVisibility(View.VISIBLE);
                city.setText(cityName);
            }
            String pinCode = areaMaster.pin;
            if (pinCode!=null && !pinCode.isEmpty()){
                pin.setText("Pin code: " + pinCode);
            }
            String phoneNo1 = areaMaster.phone1;
            if (phoneNo1!=null && !phoneNo1.isEmpty()){
                phone1.setText("Ph: " + phoneNo1);
            }

            String phoneNo2 = areaMaster.phone2;
            if (phoneNo2!=null && !phoneNo2.isEmpty()){
                phone2.setText(", " + phoneNo2);
            }

            String phoneNo3 = areaMaster.mobile;
            if (phoneNo3!=null && !phoneNo3.isEmpty()){
                phone3.setText(", " + phoneNo3);
            }

            String contact_person = areaMaster.contactPerson;
            if (contact_person!=null && !contact_person.isEmpty()){
                contactPerson.setText("Contact person: " + contact_person);
            }

            String creditLimit = areaMaster.creditLimit;
            if (creditLimit!=null && !creditLimit.isEmpty()){
                tvCrLimit.setText("Cr Limit: " + creditLimit);
            }

            String creditDays = areaMaster.creditDays;
            if (creditDays!=null &&!creditDays.isEmpty()){
                tvCrDays.setText("Cr Days: " + creditDays);
            }

            String outsBal = areaMaster.outsBal;
            if (!TextUtils.isEmpty(outsBal)){
                outBal.setText("Out bal: " + outsBal);
            }

        }
    }



    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION );
        }
        else {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null){
                                locationLat = location.getLatitude();
                                locationLng = location.getLongitude();
                                longitude.setText(Double.toString(locationLat));
                                latitude.setText(Double.toString(locationLng));
                                //new FetchAddressTask(MainActivity.this, MainActivity.this).execute(location);
                            }else {//textView.setText("No location");
                                Toast.makeText(CustomerAddress.this, "Location is not available ", Toast.LENGTH_SHORT).show(); }
                        }
                    }
            );
            Log.d(TAG, "getLocation: permission granted");
        }
        //textView.setText(getString(R.string.address_text, "Loading...", System.currentTimeMillis()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void setMasterList(View view) {

        String lat;
        String lng;
        if (locationLat!=null){
            lat = Double.toString(locationLat);
        }else lat = "0.0";
        if (locationLng!=null){
            lng = Double.toString(locationLng);
        }else {
            lng = "0.0";
        }
        Intent intent = new Intent(this, MenuItem.class);
        intent.putExtra("areaCode",areaCodePrev);
        intent.putExtra("areaName",areaNamePrev);
        intent.putExtra("storeName",  areaMaster.name);
        intent.putExtra("storeCode",areaMaster.acode);
        intent.putExtra("id", areaMaster.id);
        intent.putExtra("address",address1);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.phone1:
                String number1 = phone1.getText().toString();
                if (!TextUtils.isEmpty(number1)) {
                    Uri call = Uri.parse("tel:" + number1);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
                break;
            case R.id.phone2:
                String number2 = phone2.getText().toString();
                if (!TextUtils.isEmpty(number2)) {
                    Uri call = Uri.parse("tel:" + number2);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
                break;
            case R.id.phone3:
                String number3 = phone3.getText().toString();
                if (!TextUtils.isEmpty(number3)) {
                    Uri call = Uri.parse("tel:" + number3);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
                break;
        }
    }

    private void initListener(){
        phone1.setOnClickListener(this);
        phone2.setOnClickListener(this);
        phone3.setOnClickListener(this);
    }
}
