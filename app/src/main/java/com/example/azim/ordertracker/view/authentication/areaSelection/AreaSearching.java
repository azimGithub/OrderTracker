package com.example.azim.ordertracker.view.authentication.areaSelection;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.adapter.AreaSearchAdapter;
import com.example.azim.ordertracker.client.ApiClient;
import com.example.azim.ordertracker.extras.DividerItemDecoration;
import com.example.azim.ordertracker.model.appData.Amast;
import com.example.azim.ordertracker.model.appData.Areama;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.model.appData.ResAppData;
import com.example.azim.ordertracker.model.login.PostLogin;
import com.example.azim.ordertracker.network.ApiService;
import com.example.azim.ordertracker.network.AppRetrofitRate;
import com.example.azim.ordertracker.sql.DatabaseHelper;
import com.example.azim.ordertracker.sql.SalesReport;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.utlility.Util;
import com.example.azim.ordertracker.view.SalesReportActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaSearching extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchView searchView;
    private AreaSearchAdapter mAdapter;
    DatabaseHelper databaseHelper;

    List<Areama> storeList = new ArrayList<Areama>();

    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Unbinder unbinder;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_search_activity);
        unbinder = ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);

        token= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,"");
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.remove("cart");
//        editor.apply();

        //appData();
        showData();
        //databaseHelper.deleteItmastData();

        setSupportActionBar(toolbar);
        apiService = ApiClient.getClient().create(ApiService.class);
       /* disposable.add(RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300,TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchContacts()));
        */




    }

   /* private void fetchStores(String source){
        Call<List<Store>> call = apiService.getStore(source,null);
        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                List<Store> stores = response.body();
                for (Store row : stores){
                    storeList.add(row);
                    mAdapter.notifyDataSetChanged();
                }
                //generateDataList(stores);

            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {

            }
        });
    }*/


    private DisposableObserver<TextViewTextChangeEvent> searchContacts(){
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                mAdapter.getFilter().filter(textViewTextChangeEvent.text());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


    private void fetchContacts(String source) {
//        disposable.add(apiService
//                .getStore(source, null)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<List<Store>>() {
//                    @Override
//                    public void onSuccess(List<Store> contacts) {
//                        storeList.clear();
//                        storeList.addAll(contacts);
//                        mAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                }));
    }
    @Override
    protected void onDestroy() {
        disposable.clear();
        //unbinder.unbind();
        super.onDestroy();
    }

    /*private void generateDataList(List<Store> photoList) {
        mAdapter = new StoreListAdapter(this,photoList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // associate searchable configuration with the searchview
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text changes
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // filter recyclerview when query submitted
                mAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // filter recyclerView when text is changed
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_search:
                return true;

            case R.id.alert:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
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

                    //hidePbar();
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
                                if(storeList!=null && storeList.size()>0)
                                    storeList.clear();
                                storeList=   resData.areamas;
                                Comparator<Areama> compareByName = new Comparator<Areama>() {
                                    @Override
                                    public int compare(Areama o1, Areama o2) {
                                        return o1.name.compareTo(o2.name);
                                    }
                                };
                                Collections.sort(storeList,compareByName);
                                showData();

                            }
                            else{
                                Toast.makeText(AreaSearching.this,"Error.Please try again.",Toast.LENGTH_SHORT).show();
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

    private void showData() {
        PostLogin postLogin = new PostLogin();
        postLogin.token=token;
        String appData= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_APPDATA,"");
        if(appData!=null && !appData.isEmpty()){

            Gson gson = new Gson();
            Type type = new TypeToken<ResAppData>() {}.getType();
            ResAppData   resAppData = gson.fromJson(appData, type);
            if(resAppData!=null ){
                if(storeList!=null && storeList.size()>0)
                    storeList.clear();
                storeList=   resAppData.areamas;
                Comparator<Areama> compareByName = new Comparator<Areama>() {
                    @Override
                    public int compare(Areama o1, Areama o2) {
                        return o1.name.compareTo(o2.name);
                    }
                };
                Collections.sort(storeList,compareByName);
            }else {
                Toast.makeText(AreaSearching.this,"Error.Please try again.",Toast.LENGTH_SHORT).show();
            }
        }

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setHasFixedSize(true);
        mAdapter = new AreaSearchAdapter(this, storeList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

}