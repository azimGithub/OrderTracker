package com.example.azim.ordertracker.utlility;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Adil on 23/03/17.
 */

public class Util {

    private static final String EMAIL_REGEX = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
    public static final String TYPE_BUY ="" ;
    public static final String TYPE_SELL = "";
    public static final String TYPE_REMIT ="" ;



    public static String GAdid="";
    public static final String SHARED_PREF = "mobomanager_firebase";
    public static String latitude,longitude,city,country,postalcode,address,state,publicIp="";
    public static String macAddress="";
    public static final String ACTION_PROCESS_UPDATES ="com.adsizzler.carryculum"+".LOCATION_UPDATE" ;

    public static final String EXTRA_STRING_ONE = "extra_string_one";

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }



    /**
     * Static method to perform sms intent operation
     *
     * @param mContext    Context of the calling class
     * @param phoneNumber contact number
     */

    public static void sendSms(Context mContext, String phoneNumber) {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)));
    }

    /**
     * Static method to perform email intent operation
     *
     * @param mContext Context of the calling class
     * @param email    Email address to send to
     */
    public static void sendEmail(Context mContext, String email) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        mContext.startActivity(Intent.createChooser(emailIntent, "Send Email..."));
    }

    /**
     * Method to check email validation
     *
     * @param editText  First object of edittext
     * @param context   Context of the calling activity
     * @param messageId Resource id of the string to show if both doesn't match
     * @return status of the matching
     */
    public static boolean validateEmailFields(EditText editText, Context context, int messageId) {

        String expression = EMAIL_REGEX;
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;

        Matcher m = p.matcher(editText.getText());
        if (m.matches() && !editText.getText().toString().isEmpty()) {
            return true;
        } else {
          //  showAlertDialog(context, context.getResources().getString(messageId));

            return false;
        }
    }

    /**
     * Method to check two edit text of same text
     *
     * @param editText  First object of edittext
     * @param context   Context of the calling activity
     * @param messageId Resource id of the string to show if both doesn't match
     * @return status of the matching
     */
    public static boolean validateInputFields(EditText editText, Context context, int messageId) {
        // boolean status = false;
        if (editText.getText() != null && isFieldEmpty(editText.getText().toString().trim())) {
           // showAlertDialog(context, context.getResources().getString(messageId));
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method to check two edit text of same text
     *
     * @param text      Text to validate
     * @param context   Context of the calling activity
     * @param messageId Resource id of the string to show if both doesn't match
     * @return status of the matching
     */
    public static boolean validateInputFields(String text, Context context, int messageId) {
        if (text != null && isFieldEmpty(text)) {
          //  showAlertDialog(context, context.getResources().getString(messageId));
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateMinNumberFieldLength(CharSequence editTextString,
                                                       int lengthMin) {
        if (editTextString.toString().trim().length() > lengthMin ||editTextString.toString().trim().length()==lengthMin) {
            return true;
        } else {
          //  showAlertDialog(context, context.getResources().getString(messageId));
            return false;
        }
    }

    /**
     * Method to check two edittext of same text
     *
     * @param password     First object of edittext
     * @param confPassword Second object to match
     * @return status of the matching
     */
    public static boolean validatePasswordSameFields(EditText password, EditText confPassword) {
        // boolean status = false;
        return password.getText().toString().equals(confPassword.getText().toString());
        // return status;
    }

    /**
     * Method for checking empty input fields
     *
     * @param field Field to check
     * @return true or false if field is empty
     */
    public static boolean isFieldEmpty(String field) {
        return field.trim().length() <= 0;
    }
    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isConnectToInternet(Context mContext) {

        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        }else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            Log.d("Network",
                                    "NETWORKNAME: " + anInfo.getTypeName());
                            return true;
                        }
                    }
                }
            }
        }
       // Toast.makeText(mContext,mContext.getString(R.string.please_connect_to_internet),Toast.LENGTH_SHORT).show();
        return false;

    }



    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Util.address = address;
    }

    public static String getPostalcode() {
        return postalcode;
    }

    public static void setPostalcode(String postalcode) {
        Util.postalcode = postalcode;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        Util.country = country;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Util.city = city;
    }

    public static String getLongitude() {
        return longitude;
    }

    public static void setLongitude(String longitude) {
        Util.longitude = longitude;
    }

    public static String getLatitude() {
        return latitude;
    }

    public static void setLatitude(String latitude) {
        Util.latitude = latitude;
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




}


