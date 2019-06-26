package com.example.azim.ordertracker.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.model.OrderSubmitResponse;
import com.example.azim.ordertracker.model.appData.Areama;
import com.example.azim.ordertracker.model.appData.ResAppData;
import com.example.azim.ordertracker.model.login.PostLogin;
import com.example.azim.ordertracker.model.postData.Orders;
import com.example.azim.ordertracker.model.postData.PostOrderData;
import com.example.azim.ordertracker.network.AppRetrofitRate;
import com.example.azim.ordertracker.sql.DatabaseHelper;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.utlility.Util;
import com.example.azim.ordertracker.view.authentication.LoginActivity;
import com.example.azim.ordertracker.view.authentication.areaSelection.AreaSearching;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetData extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = GetData.this;
    private DatabaseHelper databaseHelper;
    @BindView(R.id.iv_farward)
    ImageView ivNext;
    @BindView(R.id.get_data)
    Button getData;
    @BindView(R.id.send_data)Button sendData;
    @BindView(R.id.log_out)
    TextView logOut;
    @BindView(R.id.continueBtn)Button next;
    @BindView(R.id.tv_gettingData)TextView gettingData;
    private String token;

    List<Areama> storeList = new ArrayList<Areama>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
        token= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,"");

        ivNext.setOnClickListener(this);
        getData.setOnClickListener(this);
        sendData.setOnClickListener(this);
        logOut.setOnClickListener(this);
        next.setOnClickListener(this);
        token= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,"");
//                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.remove(PrefUtils.KEY_APPDATA);
//        editor.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueBtn:
                String appData= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_APPDATA,"");
                if(appData!=null && !appData.isEmpty()) {
                    Intent intent = new Intent(activity, AreaSearching.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(activity, "No data. Please get data first", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.get_data:
                List<Integer> flagList = databaseHelper.submitFlag();
                if (flagList==null||flagList.isEmpty()){
                    gettingData.setVisibility(View.VISIBLE);
                    appData();
                }else {
                    Toast.makeText(activity, "Please post the Orders first", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.send_data:
                postData();
                break;
            case R.id.log_out:
//                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.remove(PrefUtils.KEY_TOKEN);
                PrefUtils.clearPref(this,PrefUtils.KEY_TOKEN);
                Intent i = new Intent(GetData.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
        }
    }

    private void appData() {

        if (Util.isConnectToInternet(getApplicationContext()))  {
            PostLogin postLogin = new PostLogin();
//            postLogin.userId="2";
//            postLogin.password="demo#123";
            postLogin.token=token;
            Call<ResAppData> beanCall = AppRetrofitRate.getInstance().getApiServices().apiAppData();
            beanCall.enqueue(new Callback<ResAppData>() {
                @Override
                public void onResponse(Call<ResAppData> call, Response<ResAppData> response) {

                    if(null!=response){
                        ResAppData responsebody = response.body();
                        int code = response.code();
                        response.headers();
                        if(null==responsebody)
                        {
                            //  showError("oops Error !");
                            //showError("Some Problem occur,Retry");
                        }else{
                            ResAppData resData = response.body();
                            if(null!=resData ){

                                Gson gson = new Gson();
                                Type type = new TypeToken<ResAppData>() {}.getType();
                                String appData = gson.toJson(resData, type);
                                PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.KEY_APPDATA,appData);
                                gettingData.setVisibility(View.GONE);
                                //Toast.makeText(getApplicationContext(), "Data fetched successfully", Toast.LENGTH_SHORT).show();
//                                if(storeList!=null && storeList.size()>0)
//                                    storeList.clear();
//                                storeList=   resData.areamas;
//                                Comparator<Areama> compareByName = new Comparator<Areama>() {
//                                    @Override
//                                    public int compare(Areama o1, Areama o2) {
//                                        return o1.name.compareTo(o2.name);
//                                    }
//                                };
//                                Collections.sort(storeList,compareByName);
//                                showData();

                            }
                            else{
                                Toast.makeText(activity,"Error.Please try again.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResAppData>call, Throwable t) {
                    Log.e("error", t.toString());
                    // hidePbar();

                }
            });
        }
        else{
            //   hidePbar();

        }

    }

    private void postData(){
        if (Util.isConnectToInternet(getApplicationContext())) {
            //PostLogin postLogin = new PostLogin();
//            postLogin.userId="2";
//            postLogin.password="demo#123";
            //postLogin.token=token;
            List<Orders> orders = new ArrayList<>();
            if (orders != null && orders.size() > 0) {
                orders.clear();
            }
            orders = databaseHelper.getAllOrders();
            if (orders != null && orders.size() > 0) {
                PostOrderData postOrderData = new PostOrderData();
                postOrderData.orders = orders;
                postOrderData.token = token;

                if (postOrderData.orders != null && postOrderData.orders.size() > 0) {

                }
                Call<OrderSubmitResponse> beanCall = AppRetrofitRate.getInstance().getApiServices().sortDealApi(postOrderData);
                beanCall.enqueue(new Callback<OrderSubmitResponse>() {
                    @Override
                    public void onResponse(Call<OrderSubmitResponse> call, Response<OrderSubmitResponse> response) {
                        if (response != null) {
                            OrderSubmitResponse response1 = response.body();
                            if (response1 != null) {
                                boolean status = response1.getStatus();
                                //Toast.makeText(OrderSummary.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                                if (status) {
                                    databaseHelper.updateSubmitFlag();
                                    Toast.makeText(activity, "Data posted successfully", Toast.LENGTH_SHORT).show();
                                    Log.e("post", response1.getMessage() );
//                                for (int i = 0; i < orderSummaryList.size(); i++) {
//
//                                   Itmast data = orderSummaryList.get(i);
//                                    databaseHelper.addItMast(data);
//                                }
                                } else {
                                    //Toast.makeText(activity, "No data to post", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderSubmitResponse> call, Throwable t) {
                        Log.e("error", t.toString());
                        // hidePbar();

                    }
                });
            } else {
                Toast.makeText(activity, "No data to post", Toast.LENGTH_SHORT).show();
                //   hidePbar();

            }
        }else {
            Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}
