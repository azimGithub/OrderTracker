package com.example.azim.ordertracker.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.adapter.OrderSummaryAdapter;
import com.example.azim.ordertracker.model.OrderSubmitResponse;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.model.postData.Orders;
import com.example.azim.ordertracker.model.postData.PostData;
import com.example.azim.ordertracker.model.postData.PostOrderData;
import com.example.azim.ordertracker.network.AppRetrofitRate;
import com.example.azim.ordertracker.sql.DatabaseHelper;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.utlility.DecimalUtils;
import com.example.azim.ordertracker.utlility.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSummary extends AppCompatActivity implements View.OnClickListener {

    private int storeId;
    public static String store_id;
    private int id;
    private String areaCodePrev, acode;
    private String areaNamePrev,storeName;
    private String token;
    Context mContext;
    List<Itmast> orderSummaryList = new ArrayList<>();
    ArrayList<PostData> postList = new ArrayList<>();
    List<Itmast> matchedList = new ArrayList<>();
    List<Itmast> unmatchedList = new ArrayList<>();
    List<Itmast> savedList;
    List<Itmast> incompleteList;
    PostData[] data;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    OrderSummaryAdapter orderSummaryAdapter;
    DatabaseHelper databaseHelper;
    RelativeLayout relativeLayout;

    @BindView(R.id.qtyCount)
    TextView qtyCountTv;
    @BindView(R.id.qty_tv) TextView qtyTv;
    @BindView(R.id.hash_tv) TextView hashTv;
    @BindView(R.id.hash_count)
    TextView hashCountTv;
    @BindView(R.id.ivNxt)
    Button ivNxt;
    @BindView(R.id.storeName)TextView storeNameTv;

    @BindView(R.id.custmBallTv)TextView customBalTv;
    @BindView(R.id.customBalSumTv) TextView getCustomBalTv;
    @BindView(R.id.discTv) TextView discTv;
    @BindView(R.id.discSumTv) TextView DiscSumTv;
    @BindView(R.id.codeTv)TextView codeTv;
    @BindView(R.id.codeValTv)TextView codeValTv;
    @BindView(R.id.amtWithVatTv) TextView amtWithVatTv;
    @BindView(R.id.amtWithVatSumTv) TextView amtWithVatSumTv;
    @BindView(R.id.dateTv) TextView dateTv;
    @BindView(R.id.dateValTv) TextView dateValTv;
    @BindView(R.id.netAmtTv) TextView netAmtTv;
    @BindView(R.id.netAmtValTv) TextView netAmtValTv;
    @BindView(R.id.vatTV) TextView vatTv;
    @BindView(R.id.vatValTv) TextView vatValTv;
    @BindView(R.id.netInvTv) TextView netInvTv;
    @BindView(R.id.netAmt) TextView netAmt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
        token= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,"");
        ivNxt.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        areaCodePrev = intent.getStringExtra("areaCode");
        areaNamePrev = intent.getStringExtra("areaName");
        acode = intent.getStringExtra("storeCode");
        store_id = String.valueOf(id);
        storeName = intent.getStringExtra("storeName");
        storeNameTv.setText(storeName);
        //Log.i("storeCode", "info: " + store_id);

        initData();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        orderSummaryAdapter = new OrderSummaryAdapter(this, orderSummaryList);
        recyclerView.setAdapter(orderSummaryAdapter);
    }

    private void initData(){
        String orderSummary = PrefUtils.getFromPrefs(getApplicationContext(), PrefUtils.ORDER_SELECTED, "");
        Gson gson = new Gson();
        Itmast[] orderList = gson.fromJson(orderSummary, Itmast[].class);
        orderSummaryList = Arrays.asList(orderList);

//        Date c = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("dd/mm/ss");
//
//        //to convert Date to String, use format method of SimpleDateFormat class.
//        String strDate = dateFormat.format(c);
//        String invNo = acode + "/" + strDate;
//        for (Itmast row : orderSummaryList){
//            row.inv_no = invNo;
//        }
    }

    public void setValue(List<Itmast> list){

        Double quanSum=0.0;
        Double priceSum=0.0;
        if(list!=null && list.size()>0) {

            for (int i = 0; i < list.size(); i++) {

                Itmast itemData = list.get(i);
                if (itemData != null) {

                    String quanShow = itemData.quantity;
                    String price = itemData.price;
                    if (quanShow != null && !quanShow.isEmpty()) {

                        quanSum = quanSum + Double.parseDouble(quanShow);
                    }
                    if (price != null && !price.isEmpty()) {
                        priceSum = priceSum + Double.parseDouble(price);
                        priceSum = DecimalUtils.round(priceSum, 2);

                    }

                }


            }
            if (priceSum != null)
                netAmt.setText(Double.toString(priceSum));
            hashCountTv.setText(Integer.toString(list.size()));
            qtyCountTv.setText(Double.toString(quanSum));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivNxt:

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
                        builder
                        .setTitle(R.string.order_confirmation)
                        .setMessage(R.string.confirmation_message)
                        .setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PrefUtils.saveToPrefs(getApplicationContext(), PrefUtils.STORE_ID, store_id);

                                savedList = databaseHelper.getSavedOrder(acode);
                                incompleteList = databaseHelper.getIncompleteOrder(acode);

                                if (savedList!=null&& savedList.size()>0) {
                                    saveSavedList();
                                }else if (orderSummaryList!=null&& orderSummaryList.size()>0){
                                    saveIncompleteList();
                                }


                                databaseHelper.updateIncompleteFlag(acode);
                                addCart(id);
                                saveItMast(orderSummaryList);
                                savePostListToPref(postList);
                                dataPostDialog();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel),null);

                        AlertDialog dialog = builder.create();
                        dialog.show();
        }
    }

    public void saveSavedList(){
        for (int i = 0; i < orderSummaryList.size(); i++) {
            Itmast data = orderSummaryList.get(i);
            int id = data.id;
            boolean value = getMatchedData(id, savedList);
            if (value) {
                matchedList.add(data);
            } else {
                unmatchedList.add(data);
            }
        }

        if (matchedList!=null&& matchedList.size()>0) {
            for (int i = 0; i < matchedList.size(); i++) {
                Itmast matchedData = matchedList.get(i);
                databaseHelper.updateSavedOrder(acode, matchedData);
            }
        }
        if (unmatchedList!=null&& unmatchedList.size()>0){
            for (Itmast unmatchedData : unmatchedList) {
                databaseHelper.addItMast(unmatchedData);
            }
        }
    }

    public void saveIncompleteList(){
        for (int i = 0; i < orderSummaryList.size(); i++) {
            Itmast data = orderSummaryList.get(i);
            int id = data.id;
            boolean value = getMatchedData(id, incompleteList);
            if (value) {
                matchedList.add(data);
            } else {
                unmatchedList.add(data);
            }
        }

        if (matchedList!=null&& matchedList.size()>0) {
            for (Itmast matchedData : matchedList) {
                databaseHelper.updateIncompleteOrder(acode, matchedData);
            }
        }
        if (unmatchedList!=null&& unmatchedList.size()>0) {
            for (Itmast unmatchedData : unmatchedList) {
                databaseHelper.addItMast(unmatchedData);
            }
        }
    }

    public boolean getMatchedData(int id, List<Itmast> list){
        for (int i=0;i<list.size();i++){
            int listId = list.get(i).id;
            if (id==listId){
                return true;
            }
        }
        return false;
    }

    private void addCart(int code){
        ArrayList<Integer> cartList = new ArrayList<>();

        String jsonString = PrefUtils.getFromPrefs(this, "cart", "");
        if (jsonString!=null  && !jsonString.isEmpty()){
           Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
            ArrayList<Integer> cart = gson.fromJson(jsonString, type);
            if (!cart.contains(code)){
                cart.add(code);
                String jsonCart = gson.toJson(cart);
                PrefUtils.saveToPrefs(this, "cart", jsonCart);
            }

        }else {
            cartList.add(code);
            Gson gson = new Gson();
            String jsonCart = gson.toJson(cartList);
            PrefUtils.saveToPrefs(this,"cart",jsonCart);
        }
    }

    private void saveItMast(List<Itmast> list){
        ArrayList<List<Itmast>> saveList = new ArrayList<>();
        String jsonString = PrefUtils.getFromPrefs(this, "save_it", "");
        if (jsonString!=null  && !jsonString.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<List<Itmast>>>() {}.getType();
            ArrayList<List<Itmast>>  itList = gson.fromJson(jsonString, type);
            if (!itList.contains(list)){
                itList.add(list);
                String jsonCart = gson.toJson(itList);
                PrefUtils.saveToPrefs(this, "save_it", jsonCart);
            }

        }else {
            saveList.add(list);
            Gson gson = new Gson();
            String jsonCart = gson.toJson(saveList);
            PrefUtils.saveToPrefs(this,"save_it",jsonCart);
        }
    }

    private void savePostListToPref(ArrayList<PostData> postData){

        ArrayList<ArrayList<PostData>> cartList = new ArrayList<>();

        String jsonString = PrefUtils.getFromPrefs(this, "post_list", "");
        if (jsonString!=null  && !jsonString.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ArrayList<PostData>>>() {}.getType();
            ArrayList<ArrayList<PostData>> cart = gson.fromJson(jsonString, type);
            //String ac = cart.get(0).get(0).storeCode;
           // Toast.makeText(mContext, ac, Toast.LENGTH_SHORT).show();
            if (!cart.contains(postData)){
                cart.add(postData);
                String jsonCart = gson.toJson(cart);
                PrefUtils.saveToPrefs(this, "post_list", jsonCart);
            }

        }else {
            cartList.add(postData);
            Gson gson = new Gson();
            String jsonCart = gson.toJson(cartList);
            PrefUtils.saveToPrefs(this,"post_list",jsonCart);
        }
    }

    private void postData(){
        if (Util.isConnectToInternet(getApplicationContext()))  {
            //PostLogin postLogin = new PostLogin();
//            postLogin.userId="2";
//            postLogin.password="demo#123";
            //postLogin.token=token;
            List<Orders> orders=     databaseHelper.getAllOrders();
            PostOrderData postOrderData= new PostOrderData();
            postOrderData.orders= orders;
            postOrderData.token= token;

            if(postOrderData.orders!=null && postOrderData.orders.size()>0){

            }
            Call<OrderSubmitResponse> beanCall = AppRetrofitRate.getInstance().getApiServices().sortDealApi(postOrderData);
            beanCall.enqueue(new Callback<OrderSubmitResponse>() {
                @Override
                public void onResponse(Call<OrderSubmitResponse> call, Response<OrderSubmitResponse> response) {
                    if (response!=null){
                        OrderSubmitResponse response1 = response.body();
                        if(response1!=null){
                            boolean status = response1.getStatus();
                            //Toast.makeText(OrderSummary.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                            if (status) {
                                databaseHelper.updateSubmitFlag();
//                                for (int i = 0; i < orderSummaryList.size(); i++) {
//
//                                   Itmast data = orderSummaryList.get(i);
//                                    databaseHelper.addItMast(data);
//                                }
                            }else{
                                //Toast.makeText(OrderSummary.this, "status false", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<OrderSubmitResponse>call, Throwable t) {
                    Log.e("error", t.toString());
                    // hidePbar();

                }
            });
        }
        else{
            //   hidePbar();

        }
    }

    private void dataPostDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder
                //.setTitle(R.string.order_confirmation)
                .setMessage(R.string.order_taken)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getApplicationContext(),CustomerSelection.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("areaCode",areaCodePrev);
                        intent.putExtra("areaName",areaNamePrev);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
