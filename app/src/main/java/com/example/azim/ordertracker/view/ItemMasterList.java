package com.example.azim.ordertracker.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.adapter.ItemListAdapter;
import com.example.azim.ordertracker.model.appData.Amast;
import com.example.azim.ordertracker.model.appData.Brandma;
import com.example.azim.ordertracker.model.appData.Catma;
import com.example.azim.ordertracker.model.appData.Coma;
import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.model.appData.Prefitemlist;
import com.example.azim.ordertracker.model.appData.ResAppData;
import com.example.azim.ordertracker.model.orderselected.ItemSelected;
import com.example.azim.ordertracker.sql.DatabaseHelper;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.utlility.DecimalUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static com.example.azim.ordertracker.utlility.Util.hideKeyboard;

public class ItemMasterList extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private String storeName;
    private int storeId;
    private String acode;
    private int id;
    private Menu menu;
    int posInt;
    private SearchView searchView;
    private String areaCodePrev;
    private String areaNamePrev;
    private String address;
    String lat;
    String lng;
    private String invNo;
    List<Itmast> listSaved;
    List<Itmast> itmast;
    List<Brandma> brandmas;
    List<Itmast> brandItems;
    List<Coma> comas;
    List<Catma> catmas;
    List<Itmast> savedList;
    List<Itmast> incompleteList;
    List<Itmast> matchedList;
    List<Itmast> unmatchedList;
    List<Prefitemlist> prefList;
    List<Itmast> shalowIncompleteList = new ArrayList<>();
    List<Itmast> shalowSavedList = new ArrayList<>();
    ArrayList<String> orderSummaryList;
    List<Itmast> listItem = new ArrayList<>();
    private OrderSummary orderSummary = new OrderSummary();
    ItemListAdapter itemListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    AlertDialog dialog;
    private View rootView;

    private CompositeDisposable disposable = new CompositeDisposable();

    @BindView(R.id.emptyView)TextView emptyView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvQuantity)
    TextView tvQuantity;

    @BindView(R.id.tvRow)
    TextView tvRow;

    @BindView(R.id.tvValue)
    TextView tvValue;

    @BindView(R.id.ivNext)
    ImageView ivNext;
    @BindView(R.id.tvTitle) TextView tvTitleBottom;
    @BindView(R.id.action_search_i)android.support.v7.widget.ActionMenuView filter;
    @BindView(R.id.action_search)android.support.v7.widget.ActionMenuView search;

    private ItemSelected itemSelected=new ItemSelected();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_master_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
        tvTitle = (TextView)findViewById(R.id.storeName_tv);
        ivNext.bringToFront();
        ivNext.setOnClickListener(this);
        dbHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        if(intent!=null){
            areaCodePrev=  intent.getStringExtra("areaCode");
            areaNamePrev=  intent.getStringExtra("areaName");
            storeName   =  intent.getStringExtra("storeName");
            id = intent.getIntExtra("id",0);
            acode = intent.getStringExtra("storeCode");
            lat = intent.getStringExtra("lat");
            lng = intent.getStringExtra("lng");
            address = intent.getStringExtra("address");
        }
        //tvTitle.setText(storeName);
        tvTitleBottom.setText(storeName);

        itmast = new ArrayList<>();
        orderSummaryList = new ArrayList<>();
        brandmas = new ArrayList<>();
        brandItems = new ArrayList<>();
        comas = new ArrayList<>();
        catmas = new ArrayList<>();
        prefList = new ArrayList<>();
        savedList = new ArrayList<>();
        incompleteList = new ArrayList<>();
        matchedList = new ArrayList<>();
        unmatchedList = new ArrayList<>();

        initdata();
        initSavedData();
        initIncompleteOrder();
        //setValueSaved();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        itemListAdapter = new ItemListAdapter(this, itmast);
        recyclerView.setAdapter(itemListAdapter);
        //showSelectedFilter();
        Log.i("info", Integer.toString(itmast.size()));



    }

    private void initdata() {
        String appData = PrefUtils.getFromPrefs(getApplicationContext(), PrefUtils.KEY_APPDATA, "");
        if (appData != null && !appData.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ResAppData>() {
            }.getType();
            ResAppData resAppData = gson.fromJson(appData, type);
            if(resAppData!=null){
                itmast= resAppData.itmast;
                for (Itmast row : itmast){
                    row.areaCd = areaCodePrev;
                    row.areaName = areaNamePrev;
                    row.storeCode = acode;
                    row.storeName = storeName;
                    //row.inv_no = "1";
                    row.sale_man = "m";
                    row.gross_amt = "100";
                    row.net_amt = "80";
                    row.srl_no = "2";
                    row.disc = "1";
                    row.disc_amt = "1.5";
                    row.disc_type = "try";
                    row.invlat = lat;
                    row.invlong = lng;
                    row.invaddress = address;
                    //row.mRP = "12";

                    Date c = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat format = new SimpleDateFormat("dd");

                    //to convert Date to String, use format method of SimpleDateFormat class.
                    String strDate = dateFormat.format(c);
                    String invString = format.format(c);
                    String invNo = acode + invString;
                    row.inv_no = invNo;
                    row.inv_dt = strDate;

                }
                    Comparator<Itmast> compareByName = new Comparator<Itmast>() {
                        @Override
                        public int compare(Itmast o1, Itmast o2) {
                            return o1.iname.compareTo(o2.iname);
                        }
                    };
                    Collections.sort(itmast,compareByName);
//                List<Itmast> list =dbHelper.getAllItmast(acode);
//                if (list!=null&&!list.isEmpty()){
//                    itmast = list;
//                }
               brandmas = resAppData.brandmas;
               comas = resAppData.comas;
               catmas = resAppData.catmas;
               prefList = resAppData.prefitemlist;
            }
        }
    }

    private void getFilterCat(String string){
        if (brandItems.size()>0){
            brandItems.clear();
        }
        for ( int i=0; i<itmast.size(); i++){
            String coIdItmast = itmast.get(i).cat.toString();
            if (coIdItmast.equalsIgnoreCase(string)){
                brandItems.add(itmast.get(i));
            }
        }
        itemListAdapter.swapItems(brandItems);
        setVisibility();
    }

    private void getFilterBrand(String string){
        if (brandItems.size()>0){
            brandItems.clear();
        }
        for ( int i=0; i<itmast.size(); i++){
            String coIdItmast = itmast.get(i).brand.toString();
            if (coIdItmast.equalsIgnoreCase(string)){
                brandItems.add(itmast.get(i));
            }
        }
        itemListAdapter.swapItems(brandItems);
       setVisibility();
    }

    private void getPreferredItem(String string){
        if (brandItems.size()>0){
            brandItems.clear();
        }
        for ( int i=0; i<prefList.size(); i++){
            String storeCode = prefList.get(i).acode;
            if (storeCode.equalsIgnoreCase(string)){
                String itemCode = prefList.get(i).icode;
                for (int n=0;n<itmast.size();n++){
                    String itemCodeItmast = itmast.get(n).icode;
                    if (itemCodeItmast.equalsIgnoreCase(itemCode)){
                        brandItems.add(itmast.get(n));
                    }
                }
            }
        }
        itemListAdapter.swapItems(brandItems);
        setVisibility();
    }

    private void getItmast(){
        if (brandItems.size()>0){
            brandItems.clear();
        }
        itemListAdapter.swapItems(itmast);
        emptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        menu.getItem(1).setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.filter_icon));
    }

    private void getFilterCompany(String string){
        if (brandItems.size()>0){
            brandItems.clear();
        }
        for ( int i=0; i<itmast.size(); i++){
            String coIdItmast = itmast.get(i).coCode.toString();
            if (coIdItmast.equalsIgnoreCase(string)){
                brandItems.add(itmast.get(i));
            }
        }
        itemListAdapter.swapItems(brandItems);
        setVisibility();
    }

    private void setVisibility(){
        if (brandItems.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    public void setVisibility1(List<Itmast> list){
        if (list.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }


    private void showFilter() {
        handelFilterClick();
        hideKeyboard(this);
        dialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.filter_dialog, null);
        TextView byBrand = dialogView.findViewById(R.id.byBrand);
        TextView byCategory = dialogView.findViewById(R.id.byCategory);
        TextView byCompany = dialogView.findViewById(R.id.byCompany);
        TextView noFilter = dialogView.findViewById(R.id.noFilter);
        TextView itemSelected = dialogView.findViewById(R.id.itemSelected);
        TextView preferredItem = dialogView.findViewById(R.id.byPreferred);

        byBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Drawable drawable = getResources().getDrawable(R.drawable.filter_icon);
//                PorterDuffColorFilter colorFilter
//                        = new PorterDuffColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
//                drawable.setColorFilter(colorFilter);
                showBrandFilter();
                dialog.dismiss();
            }
        });
        byCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryFilter();
                dialog.dismiss();
            }
        });
        byCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCompanyFilter();
                dialog.dismiss();
            }
        });

        noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItmast();
                dialog.dismiss();
            }
        });

        itemSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectedFilter();
                //setVisibility();
                dialog.dismiss();
            }
        });

        preferredItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPreferredItem(acode);
                dialog.dismiss();
            }
        });
        dialog.setView(dialogView);
        dialog.show();
        //fd.show();
    }

    private void showSelectedFilter() {

        itemListAdapter.getSelectedItem();
        menu.getItem(1).setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_filter_colored));
//        itemListAdapter.swapItems(listItem);
//        if (listItem.isEmpty()){
//            emptyView.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
//        }else {
//            recyclerView.setVisibility(View.VISIBLE);
//            emptyView.setVisibility(View.GONE);
//        }
    }


    private void showBrandFilter(){
       ArrayList<String> brand = new ArrayList<>();
        for (Brandma brandma : brandmas){
            brand.add(brandma.name);
        }
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Category");
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.brand_dialog, null);
        builder.setCustomTitle(view);
        ListView lv = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,brand);
        lv.setAdapter(adapter);
        builder.setView(lv);
        Dialog dialog = builder.create();
        dialog.show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> brandCode = new ArrayList<>();
                for (Brandma brandma : brandmas){
                    brandCode.add(brandma.brandCode);
                }
                String brand_code = brandCode.get(position);
                getFilterBrand(brand_code);
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_filter_colored));
                dialog.dismiss();
            }
        });
    }

    private void showCategoryFilter(){
        ArrayList<String> cat = new ArrayList<>();
        for (Catma catma : catmas){
            cat.add(catma.name);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.category_dialog, null);
        builder.setCustomTitle(view);
        ListView lv = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cat);
        lv.setAdapter(adapter);
        builder.setView(lv);
        Dialog dialog = builder.create();
        dialog.show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> catCode = new ArrayList<>();
                for (Catma catma : catmas){
                    catCode.add(catma.catCode);
                }
                String cat_code = catCode.get(position);
                getFilterCat(cat_code);
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_filter_colored));
                dialog.dismiss();
            }
        });
    }

    private void showCompanyFilter(){
        ArrayList<String> comp = new ArrayList<>();
        for (Coma coma : comas){
            comp.add(coma.name);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.company_dialog, null);
        builder.setCustomTitle(view);
        ListView lv = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,comp);
        lv.setAdapter(adapter);
        builder.setView(lv);
        Dialog dialog = builder.create();
        dialog.show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> comCode = new ArrayList<>();
                for (Coma coma : comas){
                    comCode.add(coma.code);
                }
                String com_code = comCode.get(position);
                getFilterCompany(com_code);
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_filter_colored));
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text changes
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // filter recyclerview when query submitted
                itemListAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // filter recyclerView when text is changed
                itemListAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){

            case R.id.action_search:

                return true;

            case R.id.alert:
                showFilter();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    public void setValue(Itmast valueItmast, int position, String quanProduct) {

        try {
            if(savedList!=null && savedList.size()>0) {
                int id = valueItmast.id;
                Iterator<Itmast> itr = savedList.iterator();
                while (itr.hasNext()) {
                    Itmast number = itr.next();
                    if (number.id == id) {
                        itr.remove();
                    }
                }

//                for (Itmast itmast : savedList){
//                    int savedListId = itmast.id;
//                    if (id==savedListId)
//                        savedList.remove(valueItmast);
//                }
                //shalowSavedList = new ArrayList<>(savedList.size());
//                for (Itmast list : savedList){
//                    shalowSavedList.add(list.clone());
//                }
//                for (int i = 0; i < shalowSavedList.size(); i++) {
//                    Itmast savedItmast = shalowSavedList.get(i);
//                    int savedId = savedItmast.id;
//                    int valueItmastId = valueItmast.id;
//                    if (savedId == valueItmastId) {
//                        shalowSavedList.remove(savedItmast);
//                    }
//                }
            }else {
                if (incompleteList!=null&& !incompleteList.isEmpty()){
                    int id = valueItmast.id;
                    Iterator<Itmast> itr = incompleteList.iterator();
                    while (itr.hasNext()) {
                        Itmast number = itr.next();
                        if (number.id == id) {
                            itr.remove();
                        }
                    }
                }
            }

//            else if (incompleteList!=null&& incompleteList.size()>0){
//                //shalowIncompleteList = new ArrayList<>(incompleteList.size());
//                for (Itmast list : incompleteList){
//                    shalowIncompleteList.add(list.clone());
//                }
//                for (int n=0; n<shalowIncompleteList.size();n++){
//                    Itmast incompleteItmast = shalowIncompleteList.get(n);
//                    int incompleteId = incompleteItmast.id;
//                    int valueItmastId = valueItmast.id;
//                    if (incompleteId==valueItmastId){
//                        shalowIncompleteList.remove(incompleteItmast);
//                    }
//                }
//            }
            if(valueItmast.quantity==null || valueItmast.quantity.isEmpty() || valueItmast.quantity.equalsIgnoreCase("0.0")) {

            } else{
                if (incompleteList!=null&& incompleteList.size()>0) {
                    incompleteList.add(valueItmast);
                    listItem = incompleteList;
                }else {
                    savedList.add(valueItmast);
                    listItem = savedList;
                }
//                    shalowIncompleteList.add(valueItmast);
//                    listItem = shalowIncompleteList;
//                }else {
//                    shalowSavedList.add(valueItmast);
//                    listItem = shalowSavedList;
//                }
                //savedList.add(valueItmast);
            }

                //listItem = savedList;

            Double quanSum=0.0;
            Double priceSum=0.0;
            if(!listItem.isEmpty() && listItem.size()>0){

                for(int i=0 ; i < listItem.size(); i ++){

                    Itmast itemData=   listItem.get(i);
                    if(itemData!=null){

                        String quanShow=    itemData.quantity;
                        String price=    itemData.price;
                        if(quanShow!=null && !quanShow.isEmpty()){

                            quanSum= quanSum+Double.parseDouble(quanShow);
                        }
                        if(price!=null && !price.isEmpty()){
                            priceSum= priceSum+Double.parseDouble(price);
                            priceSum=  DecimalUtils.round(priceSum,2);

                        }

                    }


                }
                if(priceSum!=null)
                    tvValue.setText("Val :"+priceSum);
                tvRow.setText("Line :"+listItem.size());
                tvQuantity.setText("Qty :"+quanSum);

            }else {
                tvValue.setText("Val :");
                tvRow.setText("Line :");
                tvQuantity.setText("Qty :");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Itmast> getListItem(){
        return listItem;
        }

    @Override
    public void onClick(View v) {
        if (listItem.size()>0 && !listItem.isEmpty()){

            switch (v.getId()){

                case R.id.ivNext:
                    Gson gson = new Gson();
                    String jsonOrderList = gson.toJson(listItem);
                    PrefUtils.saveToPrefs(getApplicationContext(), PrefUtils.ORDER_SELECTED, jsonOrderList);
                    Intent intent = new Intent(ItemMasterList.this, OrderSummary.class);
                    intent.putExtra("areaCode",areaCodePrev);
                    intent.putExtra("areaName",areaNamePrev);
                    intent.putExtra("id", id);
                    intent.putExtra("storeName",storeName);
                    intent.putExtra("storeCode", acode);

                    startActivity(intent);

                    Log.i("info", "image clicked ");
                    break;

            }
        }else {
            Toast.makeText(this, "Please select an item", Toast.LENGTH_SHORT).show();
        }

    }

    private List<Itmast> getSaveList(){
//        String pos = PrefUtils.getFromPrefs(this,"pos","");
//        if (pos!=null&&!pos.isEmpty()){
//            posInt = Integer.valueOf(pos);
//        }
        String cart = PrefUtils.getFromPrefs(this,"cart","");
        if (cart!=null&&!cart.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
            ArrayList<Integer> cartList = gson.fromJson(cart, type);
            for (Integer cartInt : cartList){
                if (cartInt.intValue()==id){
                    posInt = cartList.indexOf(cartInt);
                }
            }
        }
        String jsonString = PrefUtils.getFromPrefs(this,"save_it","");
        if (jsonString!=null&&!jsonString.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<List<Itmast>>>(){}.getType();
            ArrayList<List<Itmast>> saveList = gson.fromJson(jsonString,type);
            listSaved = saveList.get(posInt);
        }
        return listSaved;
    }

    public void initSavedData(){
        if (savedList!=null&&!savedList.isEmpty()){
            savedList.clear();
        }
        savedList = dbHelper.getSavedOrder(acode);
        if (savedList!=null&&!savedList.isEmpty()){
            for (Itmast listItem : savedList){
                String quan = listItem.quantity;
                String price = listItem.price;
                int itemId = listItem.id;
                for (Itmast list : itmast){
                    int item_id = list.id;
                    if (item_id==itemId){
                        list.quantity = quan;
                        list.price = price;
                    }
                }
            }
        }
    }

    public void initIncompleteOrder(){
        incompleteList = dbHelper.getIncompleteOrder(acode);
        if (incompleteList!=null&&!incompleteList.isEmpty()){
            for (Itmast listItem : incompleteList){
                String quan = listItem.quantity;
                String price = listItem.price;
                int itemId = listItem.id;
                for (Itmast list : itmast){
                    int item_id = list.id;
                    if (item_id==itemId){
                        list.quantity = quan;
                        list.price = price;
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (listItem!=null&& !listItem.isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
            builder
                    .setTitle("Save order?")
                    .setMessage("Do you want to save the order?")
                    .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (incompleteList!=null&& incompleteList.size()>0){
                                saveIncompleteList();
                            }else if (listItem.size()>0&&!listItem.isEmpty()){

                                for (Itmast list : listItem){
                                    String quan = list.quantity;
                                    String price = list.price;
                                    int itemId = list.id;
                                    for (Itmast _list : itmast){
                                        int _itemId = _list.id;
                                        if (_itemId==itemId){
                                            _list.quantity=quan;
                                            _list.price = price;
                                            _list.incompleteFlag = true;
                                            dbHelper.addItMast(_list);
                                        }
                                    }
                                }
                            }
                            ItemMasterList.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ItemMasterList.super.onBackPressed();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            super.onBackPressed();
        }
    }

    private void handelFilterClick() {
        // close search view if its visible
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            searchView.clearFocus();
            searchView.onActionViewCollapsed();
            //searchView.setQuery("", false);
            return;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void saveIncompleteList(){
        for (int i = 0; i < listItem.size(); i++) {
            Itmast data = listItem.get(i);
            int id = data.id;
            boolean value = orderSummary.getMatchedData(id, incompleteList);
            if (value) {
                data.incompleteFlag = true;
                matchedList.add(data);
            } else {
                data.incompleteFlag = true;
                unmatchedList.add(data);
            }
        }

        if (matchedList!=null&& matchedList.size()>0) {
            for (Itmast matchedData : matchedList) {
                dbHelper.addToIncompleteOrder(acode, matchedData);
            }
        }
        if (unmatchedList!=null&& unmatchedList.size()>0) {
            for (Itmast unmatchedData : unmatchedList) {
                dbHelper.addItMast(unmatchedData);
            }
        }
    }

}
