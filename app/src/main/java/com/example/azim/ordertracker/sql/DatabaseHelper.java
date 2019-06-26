package com.example.azim.ordertracker.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.azim.ordertracker.model.appData.Itmast;
import com.example.azim.ordertracker.model.postData.Orders;
import com.example.azim.ordertracker.model.postData.Sblvars;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String TABLE_SALES_REPORT = "sales_report";
    private static final String TABLE_ITMASTER = "it_master";
    private static final String COLUMN_ITEM_NAME = "item_name";
    private static final String COLUMN_ITEM_CODE = "item_code";
    private static final String COLUMN_OUTLET_COUNT = "store_count";
    private static final String COLUMN_ITEM_QTY = "item_qty";
    private static final String COLUMN_ITEM_PRICE = "item_value";
    private static final String COLUMN_OUTLET_ID = "outlet_id";
    private static final String COLUMN_OUTLET_TCOUNT = "outlet_tcount";
    private static final String COLUMN_ITEM_TPRICE = "item_tprice";
    private static final String COLUMN_ITEM_TQTY = "item_tqty";

    private String CREATE_SALES_REPORT = "CREATE TABLE " + TABLE_SALES_REPORT + "(" + COLUMN_USER_ID
             + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_OUTLET_ID + " INTEGER,"
             + COLUMN_ITEM_CODE + " TEXT," + COLUMN_ITEM_NAME + " TEXT," + COLUMN_ITEM_QTY + " TEXT,"
             + COLUMN_OUTLET_COUNT + " TEXT," + COLUMN_ITEM_PRICE + " TEXT" + ")";

    private String CREATE_ITMASTER = "CREATE TABLE " + TABLE_ITMASTER + "(" + "id" + " INTEGER," +
            COLUMN_ITEM_CODE + " TEXT," + COLUMN_ITEM_NAME + " TEXT," + COLUMN_ITEM_QTY + " TEXT," + COLUMN_ITEM_PRICE + " TEXT," + "sh_Name" +  " TEXT," + "sal_unit" + " TEXT," +
            "sub_sale_unit" + " TEXT," + "no_sl_unit" + " INTEGER," + "active" + " TEXT," + "comm_per" + " TEXT," + "cat" + " TEXT," +
            "co_code" + " TEXT," + "kgunit" + " INTEGER," + " op_bal" + " TEXT," + "brand" + " TEXT," + "case_qty" + " INTEGER," +
            "hsn_code" + " TEXT," + "factor" + " INTEGER," + "gst_per" + " TEXT," + "gst_cess_per" + " TEXT," + "addl_gst_rate" + " TEXT," +
            "cop_icode" + " TEXT," + "Sale_Rate" + " TEXT," + "mrp" + " TEXT," + "batch_no" + " TEXT," + "co_id" + " TEXT," +
            "user_id" + " TEXT," + "areaCd" + " TEXT," + "areaName" + " TEXT," + "storeCode" + " TEXT," + "storeName" + " TEXT,"
             + "submit_flag" + " INTEGER," + "postData" + " TEXT," + "inv_dt" + " TEXT," + "invlat" + " TEXT," + "invlong" + " TEXT," + "invaddress" + " TEXT,"
            + "sale_man" + " TEXT," + "gross_amt" + " TEXT," + "net_amt" + " TEXT," + "inv_no" + " TEXT," + "acode" + " TEXT," +
            "srl_no" + " TEXT," + "disc" + " TEXT," + "disc_type"+ " TEXT," + "incomplete_flag" + " INTEGER," + "disc_amt" + " TEXT" + ")";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";


    // DROP TABLE SQL QUERY
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SALES_REPORT);
        db.execSQL(CREATE_ITMASTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES_REPORT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITMASTER);

        // CREATE NEW TABLE
        onCreate(db);
    }

    public void addItMast(Itmast itmast){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", itmast.id);
        values.put(COLUMN_ITEM_CODE, itmast.icode);
        values.put(COLUMN_ITEM_NAME, itmast.iname);
        if (itmast.shName!=null){values.put("sh_Name", itmast.shName.toString());}
        //if (itmast.salUnit!=null){
        values.put("sal_unit", itmast.salUnit);
        //if (itmast.subSalUnit!=null){
        values.put("sub_sale_unit", itmast.subSalUnit);
        //if (itmast.noSlUnit!=null)
        values.put("no_sl_unit", itmast.noSlUnit);
        if (itmast.active!=null){
        values.put("active", itmast.active.toString());}
        if (itmast.commPer!=null){ values.put("comm_per", itmast.commPer.toString()); }
        if (itmast.cat!=null){
        values.put("cat", itmast.cat.toString());}
        //if (itmast.coCode!=null){
        values.put("co_code", itmast.coCode);
        //if (itmast.kgunit!=null){
        values.put("kgunit", itmast.kgunit);
        if (itmast.opBal!=null){
        values.put("op_bal", itmast.opBal.toString());}
        if (itmast.brand!=null){
        values.put("brand", itmast.brand.toString());}
        //if (itmast.caseQty!=null){
        values.put("case_qty", itmast.caseQty);
        //if (itmast.hsnCode!=null){
        values.put("hsn_code", itmast.hsnCode);
        //if (itmast.factor!=null){
        values.put("factor", itmast.factor);
        //if (itmast.gstPer!=null){
        values.put("gst_per", itmast.gstPer);
        //if (itmast.gstCessPer!=null){
        values.put("gst_cess_per", itmast.gstCessPer);
        //if (itmast.addlGstRate!=null){
        values.put("addl_gst_rate", itmast.addlGstRate);
        if (itmast.compIcode!=null){
        values.put("comp_icode", itmast.compIcode.toString());}
        //if (itmast.saleRate!=null){
        values.put("Sale_Rate", itmast.saleRate);
        if (itmast.mRP!=null){
        values.put("mrp", itmast.mRP.toString());}
        else values.putNull("mrp");
        //if (itmast.batchNo!=null){
        values.put("batch_no", itmast.batchNo);
        //if (itmast.coId!=null){
        values.put("co_id", itmast.coId);
        //if (itmast.userId!=null){
        values.put("user_id", itmast.userId);
        //if (itmast.quantity!=null){
        values.put(COLUMN_ITEM_QTY, itmast.quantity);
        //if (itmast.price!=null){
        values.put(COLUMN_ITEM_PRICE,itmast.price);
        //if (itmast.areaCd!=null){
        values.put("areaCd", itmast.areaCd);
        //if (itmast.areaName!=null){
        values.put("areaName", itmast.areaName);
        //if (itmast.storeCode!=null){
        values.put("storeCode", itmast.storeCode);
        //if (itmast.storeName!=null){
        values.put("storeName", itmast.storeName);
        values.put("submit_flag", itmast.submit_flag);
        values.put("incomplete_flag", itmast.incompleteFlag);
        //if (itmast.inv_dt!=null){
        values.put("inv_dt", itmast.inv_dt);
        //if (itmast.storeCode!=null){
        values.put("acode", itmast.storeCode);
        //if (itmast.inv_no!=null){
        values.put("inv_no", itmast.inv_no);
        //if (itmast.sale_man!=null){
        values.put("sale_man", itmast.sale_man);
        //if (itmast.gross_amt!=null){
        values.put("gross_amt", itmast.gross_amt);
        values.put("net_amt", itmast.net_amt);
        values.put("srl_no", itmast.srl_no);
        values.put("disc", itmast.disc);
        values.put("disc_amt", itmast.disc_amt);
        values.put("disc_type", itmast.disc_type);
        values.put("invlat", itmast.invlat);
        values.put("invlong", itmast.invlong);
        values.put("invaddress", itmast.invaddress);
        db.insert(TABLE_ITMASTER,null,values);
        db.close();
    }

    public void updateSubmitFlag(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       values.put("submit_flag",1);
        db.update(TABLE_ITMASTER, values, null, null);
        db.close();
    }

    public List<String> getInvList(){

        List<String> invList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {"inv_no", "submit_flag", "incomplete_flag"};
        String selection = "submit_flag" + "=?" + " AND" + " incomplete_flag" + "=?" ;
        String[] selectionArg = {String.valueOf(0),String.valueOf(0)};
        Cursor cursor = db.query(TABLE_ITMASTER, column,selection,selectionArg,"inv_no",null,null);

        if (cursor.moveToFirst()){
            do {
                String invNo = cursor.getString(cursor.getColumnIndex("inv_no"));
                invList.add(invNo);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return invList;
    }

    public List<Orders> getOrderHeader(){

        List<Orders> list = new ArrayList<>();
        //Orders orders = new Orders();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {"inv_no", "inv_dt", "areaCd", "acode", "sale_man", "gross_amt", "net_amt","submit_flag", "incomplete_flag", "invlat", "invlong", "invaddress"};
        String selection = "submit_flag" + "=?" + " AND" + " incomplete_flag" + "=?" ;
        String[] selectionArg = {String.valueOf(0),String.valueOf(0)};
        Cursor cursor = db.query(TABLE_ITMASTER, column,selection,selectionArg,"inv_no",null,null);
        int count = cursor.getCount();

        if (cursor.moveToFirst()){
            do {
                Orders orders = new Orders();
                orders.setInvNo(cursor.getString(cursor.getColumnIndex("inv_no")));
                orders.setInvDt(cursor.getString(cursor.getColumnIndex("inv_dt")));
                orders.setAreaCd(cursor.getString(cursor.getColumnIndex("areaCd")));
                orders.setAcode(cursor.getString(cursor.getColumnIndex("acode")));
                orders.setSaleMan(cursor.getString(cursor.getColumnIndex("sale_man")));
                orders.setGrossAmt(cursor.getString(cursor.getColumnIndex("gross_amt")));
                orders.setNetAmt(cursor.getString(cursor.getColumnIndex("net_amt")));
                orders.setInvlat(cursor.getString(cursor.getColumnIndex("invlat")));
                orders.setInvlong(cursor.getString(cursor.getColumnIndex("invlong")));
                orders.setInvaddress(cursor.getString(cursor.getColumnIndex("invaddress")));
                list.add(orders);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ITMASTER);
    }

    public List<Orders> getAllOrders(){
        List<Orders> allOrders = new ArrayList<>();
        List<Sblvars> sblvarsList = new ArrayList<>();

        List<Sblvars> sblvarsList1 = new ArrayList<>();
        List<Orders> headerList = new ArrayList<>();
        if (headerList!=null&& headerList.size()>0){
            headerList.clear();
        }
         headerList = getOrderHeader();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {"inv_no", "inv_dt", "srl_no", COLUMN_ITEM_CODE, "disc", "disc_type","disc_amt",
                    COLUMN_ITEM_QTY, "mrp", "Sale_Rate", "acode","submit_flag"};
        String selection = "submit_flag" + "=?" + " AND" + " incomplete_flag" + "=?";
        String[] selectionArg = {String.valueOf(0),String.valueOf(0)};

        Cursor cursor = db.query(TABLE_ITMASTER, column,selection,selectionArg,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){


            Sblvars sblvar = new Sblvars();
                sblvar.setInvNo(cursor.getString(cursor.getColumnIndex("inv_no")));
                sblvar.setInvDt(cursor.getString(cursor.getColumnIndex("inv_dt")));
                sblvar.setSrlNo(cursor.getString(cursor.getColumnIndex("srl_no")));
                sblvar.setIcode(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_CODE)));
                sblvar.setDisc(cursor.getString(cursor.getColumnIndex("disc")));
                sblvar.setDiscAmt(cursor.getString(cursor.getColumnIndex("disc_amt")));
                sblvar.setDiscType(cursor.getString(cursor.getColumnIndex("disc_type")));
                sblvar.setQty(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_QTY)));
                String mrp = cursor.getString(cursor.getColumnIndex("mrp"));
                if (mrp==null){
                    sblvar.setMrp("0.0");
                }else{
                sblvar.setMrp(cursor.getString(cursor.getColumnIndex("mrp")));}
                sblvar.setRate(cursor.getString(cursor.getColumnIndex("Sale_Rate")));
                sblvarsList.add(sblvar);
            cursor.moveToNext();
        }

        if (headerList!=null&&headerList.size()>0) {
            for (Orders orders : headerList) {
                List<Sblvars> list1 = new ArrayList<>();
                String shopCode = orders.getInvNo();
                for (Sblvars sblvars : sblvarsList) {
                    String shop_code = sblvars.getInvNo();
                    if (shopCode.equalsIgnoreCase(shop_code)) {
                        list1.add(sblvars);
                    }
                }
                orders.setSblvars(list1);
            }
        }
            cursor.close();
            db.close();
            return headerList;
    }

    public List<Itmast> getSavedOrder(String storeCode){
        List<Itmast> savedOrderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {"id",COLUMN_ITEM_QTY, "storeCode","incomplete_flag",COLUMN_ITEM_PRICE};
        String select = "storeCode" + "=?" + " AND" + " incomplete_flag" + "=?";
        String[] selectArg = {storeCode,String.valueOf(0)};
        Cursor cursor = db.query(TABLE_ITMASTER,column,select,selectArg,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Itmast itmast = new Itmast();
                itmast.id = cursor.getInt(cursor.getColumnIndex("id"));
                itmast.quantity = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_QTY));
                itmast.price = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE));
                savedOrderList.add(itmast);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return savedOrderList;
    }

    public void updateSavedOrder(String storeCode, Itmast itmast){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_QTY,itmast.quantity);
        values.put(COLUMN_ITEM_PRICE, itmast.price);
        String[] column = {"id",COLUMN_ITEM_QTY, "storeCode","incomplete_flag", "submit_flag", COLUMN_ITEM_PRICE};
        String select = "storeCode" + "=?" + " AND" + " incomplete_flag" + "=?" + " AND" + " submit_flag" + "=?" + " AND" + " id" + "=?";
        String[] selectArg = {storeCode,String.valueOf(0), String.valueOf(0), String.valueOf(itmast.id)};
        db.update(TABLE_ITMASTER,values,select,selectArg);
    }

    public void updateIncompleteOrder(String storeCode, Itmast itmast){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_QTY,itmast.quantity);
        values.put(COLUMN_ITEM_PRICE, itmast.price);
        values.put("incomplete_flag", 0);
        String select = "storeCode" + "=?" + " AND" + " incomplete_flag" + "=?" + " AND" + " id" + "=?";
        String[] selectArg = {storeCode,String.valueOf(1), String.valueOf(itmast.id)};
        db.update(TABLE_ITMASTER,values,select,selectArg);
    }

    public void addToIncompleteOrder(String storeCode, Itmast itmast){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_QTY,itmast.quantity);
        values.put(COLUMN_ITEM_PRICE, itmast.price);
        values.put("incomplete_flag", 1);
        String select = "storeCode" + "=?" + " AND" + " incomplete_flag" + "=?" + " AND" + " id" + "=?";
        String[] selectArg = {storeCode,String.valueOf(1), String.valueOf(itmast.id)};
        db.update(TABLE_ITMASTER,values,select,selectArg);
    }

    public List<Integer> submitFlag(){
        List<Integer> flagList = new ArrayList<>();
        if (flagList.size()>0){
            flagList.clear();
        }
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {"submit_flag", "incomplete_flag"};
        String select = "submit_flag" + "=?" + " AND" + " incomplete_flag" + "=?";
        String[] selectArg = {String.valueOf(0), String.valueOf(0)};
        Cursor cursor = db.query(TABLE_ITMASTER,column,select,selectArg,null,null,null);

        if (cursor.moveToFirst()){
            do{
                int flag = cursor.getInt(cursor.getColumnIndex("submit_flag"));
                flagList.add(flag);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return flagList;
    }

    public List<Itmast> getIncompleteOrder(String storeCode){
        List<Itmast> incompleteList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] column = {"id",COLUMN_ITEM_QTY,COLUMN_ITEM_PRICE,"storeCode","incomplete_flag"};
        String select = "storeCode" + "=?" + " AND" + " incomplete_flag" + "=?";
        String[] selectArg = {storeCode,String.valueOf(1)};
        Cursor cursor = db.query(TABLE_ITMASTER,column,select,selectArg,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Itmast itmast = new Itmast();
                itmast.id = cursor.getInt(cursor.getColumnIndex("id"));
                itmast.quantity = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_QTY));
                itmast.price = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE));
                incompleteList.add(itmast);

            }while
                (cursor.moveToNext());
        }
        return incompleteList;
    }

    public void updateIncompleteFlag(String storeCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("incomplete_flag", 0);
        String select = "storeCode" + "=?" + " AND" + " incomplete_flag" + "=?";
        String[] selectArg = {storeCode,String.valueOf(1)};

        db.update(TABLE_ITMASTER, values, select, selectArg);
        db.close();
    }

    public List<Itmast> getAllItmast(String acode){
        List<Itmast> allItem = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] select = new String[]{acode};
        String[] columns = {COLUMN_ITEM_CODE, COLUMN_ITEM_NAME, "Sale_Rate", "gst_per", "storeCode"};
        Cursor cursor = db.query(TABLE_ITMASTER, columns,"storeCode",select,null,null,null);

        if (cursor.moveToFirst()){
            do {
                Itmast itmast = new Itmast();
                itmast.icode = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_CODE));
                itmast.iname = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME));
                itmast.saleRate = cursor.getString(cursor.getColumnIndex("Sale_Rate"));
                itmast.gstPer = cursor.getString(cursor.getColumnIndex("gst_per"));
                itmast.price = cursor.getString(cursor.getColumnIndex("storeCode"));
                allItem.add(itmast);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allItem;

    }

    public void deleteItmastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_ITMASTER);
    }

    public List<Itmast> getItmastByBrand(){
        List<Itmast> itemByBrand = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ITEM_CODE, COLUMN_ITEM_NAME, "Sale_Rate", "gst_per", "case_qty"};
        String selection = "CAST(" + "case_qty " + "as TEXT) =?";
        String[] selectionArg = {"24"};

        Cursor cursor = db.query(TABLE_ITMASTER,columns,selection,selectionArg,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Itmast itmast = new Itmast();
                itmast.icode = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_CODE));
                itmast.iname = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME));
               // itmast.saleRate = cursor.getDouble(cursor.getColumnIndex("Sale_Rate"));
               // itmast.gstPer = cursor.getDouble(cursor.getColumnIndex("gst_per"));
                // itmast.price = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE));
                itemByBrand.add(itmast);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemByBrand;
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addToSalesReport(Itmast itmast){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_CODE, itmast.icode);
        values.put(COLUMN_ITEM_NAME, itmast.iname);
        values.put(COLUMN_OUTLET_COUNT, itmast.outletCount);
        values.put(COLUMN_ITEM_QTY, itmast.quantity);
        values.put(COLUMN_ITEM_PRICE, itmast.price);
        db.insert(TABLE_SALES_REPORT, null, values);
        db.close();
    }

    public List<Itmast> getAllSalesItem(){

        String[] columns = {COLUMN_ITEM_CODE, COLUMN_ITEM_NAME,COLUMN_OUTLET_COUNT, COLUMN_ITEM_QTY, COLUMN_ITEM_PRICE};

        String sortOrder = COLUMN_ITEM_NAME ;

        List<Itmast> allItem = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String stringQuery = "SELECT " + COLUMN_ITEM_CODE + "," + COLUMN_ITEM_NAME + "," + COLUMN_ITEM_QTY + "," + COLUMN_ITEM_PRICE +
                "," + COLUMN_OUTLET_COUNT + ", TOTAL(" + COLUMN_ITEM_QTY + ") AS " + COLUMN_ITEM_TQTY + ", TOTAL(" + COLUMN_ITEM_PRICE + ") AS " + COLUMN_ITEM_TPRICE +
                ", TOTAL(" + COLUMN_OUTLET_COUNT + ") AS " + COLUMN_OUTLET_TCOUNT + " from "  + TABLE_SALES_REPORT + " GROUP BY " + COLUMN_ITEM_NAME;
        Cursor cursor = db.rawQuery(stringQuery,null);
        //Cursor cursor =  db.query(TABLE_SALES_REPORT,columns,null,null,null,null,sortOrder);

        if (cursor.moveToFirst()){
            do {
                Itmast report = new Itmast();
                report.icode = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_CODE));
                report.iname = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME));
                report.quantity = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_TQTY));
                report.price = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_TPRICE));
                report.outletCount = cursor.getString(cursor.getColumnIndex(COLUMN_OUTLET_TCOUNT));
                allItem.add(report);
            }while(cursor.moveToNext());
        }cursor.close();
        db.close();
        return allItem;
    }

    public List<User> getAllUser(){
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD
        };
        // sorting order
        String sortOrder = COLUMN_USER_NAME + "ASC";
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns  = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,            // column to return
                selection,          // column for the where clause
                selectionArgs,      // value for the where clause
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
            return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}
