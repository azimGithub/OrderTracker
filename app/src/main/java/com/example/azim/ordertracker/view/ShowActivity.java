package com.example.azim.ordertracker.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.azim.ordertracker.R;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
    }

    public void showSalesReport(View view) {
        Intent intent = new Intent(this, SalesReportActivity.class);
        startActivity(intent);
    }
}
