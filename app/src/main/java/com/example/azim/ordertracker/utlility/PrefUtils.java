package com.example.azim.ordertracker.utlility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefUtils {



	public static final String GCM_ID="GCM_ID";
	public static final String BATCH_YEAR="batch_year";

	public static final String userRechargeHistory="userRecgargeHistory";






	public static final String KEY_TOKEN="token";
	public static final String KEY_UTM_SOURCE="utm_source_get";
	public static final String KEY_UTM_MEDIUM="utm_medium_get";
    public static final String KEY_APPDATA = "appData";
    public static final String STORE_SELECTED = "storeSelected";
    public static final String ORDER_SELECTED = "orderSelected";
    public static final String STORE_ID = "storeId";
    private static final String CART = "cart";
    public static final String CURRENT_MONTH = "currentMonth";
    public static final String YEAR = "year";
    public static final String INV_LAST_DIGIT = "invLastDigit";

    public static void saveToPrefs(Context context, String key, String value) {
		try {
			SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
			if(null!=prefs){
                final SharedPreferences.Editor editor = prefs.edit();
                editor.putString(key, value);
                editor.commit();

            }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getFromPrefs(Context context, String key,
                                      String defaultValue) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		try {

			return sharedPrefs.getString(key, defaultValue);

		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	public static void saveToPrefsInt(Context context, String key, int value) {
		try {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			if(null!=prefs){
				final SharedPreferences.Editor editor = prefs.edit();
				editor.putInt(key, value);
				editor.commit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int getFromPrefsInt(Context context, String key,
									  int defaultValue) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		try {

			return sharedPrefs.getInt(key,defaultValue);

		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	public static void clearPref(Context context, String key){
		try {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			if(null!=prefs){
				final SharedPreferences.Editor editor = prefs.edit();
				editor.remove(key);
				editor.commit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveCart(Context context, ArrayList<Integer> cartList){
		try {
			SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
			if (sharedPrefs!=null){
				SharedPreferences.Editor editor = sharedPrefs.edit();

				Gson gson = new Gson();
				String jsonCart = gson.toJson(cartList);
				editor.putString(CART,jsonCart);
				editor.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static ArrayList<Integer> getCart(Context context){
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		List<Integer> cartList;

    		if (sharedPrefs.contains(CART)){
    			String jsonCart = sharedPrefs.getString(CART,null);
    			Gson gson = new Gson();
    			Integer[] cart = gson.fromJson(jsonCart, Integer[].class);
    			cartList = Arrays.asList(cart);
    			cartList = new ArrayList<>(cartList);
			}else {
    			return null;
			}
			return (ArrayList<Integer>) cartList;
	}

	public static void addCart(Context context, int integer){
    	ArrayList<Integer> cartList;
				cartList = getCart(context);
    		cartList.add(integer);
    		saveCart(context, cartList);
	}
}
