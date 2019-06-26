package com.example.azim.ordertracker.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.view.authentication.LoginActivity;

public class SplashActivity extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                String token = PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,"");
                if (!TextUtils.isEmpty(token)){
                    Intent i = new Intent(SplashActivity.this, GetData.class);
                    startActivity(i);
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
