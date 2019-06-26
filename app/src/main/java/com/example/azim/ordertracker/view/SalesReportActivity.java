package com.example.azim.ordertracker.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.adapter.SalesReportAdapter;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesReportActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private List<Itmast> itmast;
    private SalesReportAdapter adapter;
    private DatabaseHelper databaseHelper;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_achievement_report);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);

        itmast = new ArrayList<>();
        itmast = databaseHelper.getAllSalesItem();
        layoutManager = new LinearLayoutManager(this);
        adapter = new SalesReportAdapter(this, itmast);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
