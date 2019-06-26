package com.example.azim.ordertracker.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.adapter.StoreListAdapter;
import com.example.azim.ordertracker.client.ApiClient;
import com.example.azim.ordertracker.extras.DividerItemDecoration;
import com.example.azim.ordertracker.model.appData.Amast;
import com.example.azim.ordertracker.model.appData.Areama;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.model.appData.ResAppData;
import com.example.azim.ordertracker.model.login.PostLogin;
import com.example.azim.ordertracker.network.ApiService;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.utlility.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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

public class CustomerSelection extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchView searchView;
    private StoreListAdapter mAdapter;
    private int saveListPos;
   // private List<Store> storeList = new ArrayList<>();

    List<Amast> storeList = new ArrayList<Amast>();
    List<Amast> listFilter= new ArrayList<Amast>();

    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Unbinder unbinder;
    @BindView(R.id.beatName)
    TextView beatName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private String token;
    private String areaCodePrev;
    private String areaNamePrev;
    private String acodPrev;
    private int areaPositionePrev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_selection);
        unbinder = ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent!=null){

           areaCodePrev=  intent.getStringExtra("areaCode");
             areaNamePrev=      intent.getStringExtra("areaName");
             areaPositionePrev=    intent.getIntExtra("areaPosition",0);
        }
        toolbar.setTitle("");
        beatName.setText(areaNamePrev);

        token= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,"");

        appData();
        setCart();


        setSupportActionBar(toolbar);

        apiService = ApiClient.getClient().create(ApiService.class);
       /* disposable.add(RxTextView.textChangeEvents(inputSearch)
        .skipInitialValue()
        .debounce(300,TimeUnit.MILLISECONDS)
        .distinctUntilChanged()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(searchContacts()));

        fetchContacts("gmail");
        */

    }

    public String getArea(){
        return areaNamePrev;
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

    public void setAddress(Amast aMast) {

        if(aMast!=null){

            Gson gson= new Gson();
            Type type = new TypeToken<Amast>() {}.getType();
            String appData = gson.toJson(aMast, type);
            PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.STORE_SELECTED,appData);
        }
        Intent intent = new Intent(this, CustomerAddress.class);
        intent.putExtra("EMAIL", "");
        intent.putExtra("areaCode",areaCodePrev);
        intent.putExtra("areaName",areaNamePrev);
        intent.putExtra("storeCode", aMast.acode);
        intent.putExtra("id", aMast.id);
        startActivity(intent);
    }

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
        int id = item.getItemId();
        if (id == R.id.action_search){
            return true;
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


        String appData= PrefUtils.getFromPrefs(getApplicationContext(),PrefUtils.KEY_APPDATA,"");
        if(appData!=null && !appData.isEmpty()){

            Gson gson = new Gson();
            Type type = new TypeToken<ResAppData>() {}.getType();
            ResAppData   resAppData = gson.fromJson(appData, type);
            if(resAppData!=null ){

              storeList=  resAppData.amast;
                Comparator<Amast> compareByName = new Comparator<Amast>() {
                    @Override
                    public int compare(Amast o1, Amast o2) {
                        return o1.name.compareTo(o2.name);
                    }
                };
                Collections.sort(storeList,compareByName);

                showData();
            }
        }


        if (Util.isConnectToInternet(getApplicationContext()))  {

            PostLogin postLogin = new PostLogin();
//            postLogin.userId="2";
//            postLogin.password="demo#123";
            postLogin.token=token;



        }
        else{
            //   hidePbar();

        }



    }

    private void showData() {


        compareAreacode();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setHasFixedSize(true);

            mAdapter = new StoreListAdapter(this, listFilter);



        recyclerView.setAdapter(mAdapter);
    }

    private void compareAreacode() {


        if(areaCodePrev!=null && !areaCodePrev.isEmpty()){

            if(storeList!=null && storeList.size()>0){

                for(int i = 0 ; i < storeList.size(); i++){

                    if(storeList.get(i).areaCd.replaceFirst("^0+(?!$)", "").equalsIgnoreCase(areaCodePrev.replaceFirst("^0+(?!$)", ""))){

                        listFilter.add(storeList.get(i));
                    }


                }

            }

        }



    }


    @Override
    protected void onRestart() {
        super.onRestart();
        setCart();
    }

    private void setCart(){


        String jsonString = PrefUtils.getFromPrefs(this, "cart", "");
        if (jsonString!=null && !jsonString.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
            ArrayList<Integer> cartList = gson.fromJson(jsonString, type);
            for (int i=0; i<cartList.size(); i++){
                String store_id = String.valueOf(cartList.get(i));
                for (int n=0; n<listFilter.size(); n++){
                    String _store_id = String.valueOf(listFilter.get(n).id);
                    if (store_id.equalsIgnoreCase(_store_id)){
                        saveListPos = i;
                        String pos = String.valueOf(saveListPos);
                        PrefUtils.saveToPrefs(this,"pos",pos);
                        listFilter.get(n).img = R.drawable.shoping_cart_loaded;
                        mAdapter.swapItems(listFilter);
                    }
                }
            }
        }

    }

}
