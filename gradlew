package com.example.azim.placepicker;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azim.walkmyandroid.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FetchAddressTask.OnTaskCompleted {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TAG = "request_tag";
    private static final String TRACKING_LOCATION_KEY = "tracking_location";
    private Button button;
    private Location mLocation;
    private TextView textView;
    private FusedLocationProviderClient mFusedLocationClient;
    private ImageView mAndroidImageView;
    private AnimatorSet mRotateAnim;
    private boolean mTrackingLocation;
    private LocationCallback mLocationCalllback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button_location);
        button.setOnClickListener(this);
        textView = findViewById(R.id.textview_location);
        mAndroidImageView = findViewById(R.id.imageview_android);
        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.rotate);
        mRotateAnim.setTarget(mAndroidImageView);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (savedInstanceState != null){
            mTrackingLocation = savedInstanceState.getBoolean(TRACKING_LOCATION_KEY);
        }

        mLocationCalllback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (mTrackingLocation){
                    new FetchAddressTask(MainActivity.this, MainActivity.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_location:
                if (!mTrackingLocation){
                    startTrackingLocation();
                }else {
                    stopTrackingLocation();
                }
        }

    }

    private void startTrackingLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION );
        }
        else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCalllback, null);
            textView.setText(getString(R.string.address_text, "Loading...", System.currentTimeMillis()));
            mRotateAnim.start();

            button.setText("Stop tracking Location");
        }

    }

    private void stopTrackingLocation(){
        mFusedLocationClient.removeLocationUpdates(mLocationCalllback);
        if (mTrackingLocation){
            mTrackingLocation = false;
            button.setText(R.string.start_tracking_location);
            textView.setText(R.string.textview_hint);
            mRotateAnim.end();
        }
    }
    private LocationRequest getLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startTrackingLocation();
                }else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onTaskCompleted(String result) {
        if (mTrackingLocation){
            // Update the UI
            textView.setText(getString(R.string.address_text, result, System.currentTimeMillis()));
        }
    }

    @Override
    protected void o