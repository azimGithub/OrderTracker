package com.example.azim.ordertracker.view.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.azim.ordertracker.R;
import com.example.azim.ordertracker.extras.InputValidation;
import com.example.azim.ordertracker.model.login.ErrorLogin;
import com.example.azim.ordertracker.model.login.PostLogin;
import com.example.azim.ordertracker.model.login.ResLogin;
import com.example.azim.ordertracker.network.AppRetrofitRate;
import com.example.azim.ordertracker.utlility.PrefUtils;
import com.example.azim.ordertracker.sql.DatabaseHelper;
import com.example.azim.ordertracker.utlility.Util;
import com.example.azim.ordertracker.view.GetData;
import com.example.azim.ordertracker.view.authentication.areaSelection.AreaSearching;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //  getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        //
        loginAuth();
        //

//
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
//            return;
//        }
//
//        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
//                , textInputEditTextPassword.getText().toString().trim())) {
//
//
//            Intent accountsIntent = new Intent(activity, CustomerSelection.class);
//            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
//            emptyInputEditText();
//            startActivity(accountsIntent);



    }

    private void loginAuth() {


            if (Util.isConnectToInternet(getApplicationContext()))  {

                PostLogin postLogin = new PostLogin();
                postLogin.userId=textInputEditTextEmail.getText().toString();           //"2"
                postLogin.password= textInputEditTextPassword.getText().toString();     //"demo#123";

                Call<ResLogin> beanCall = AppRetrofitRate.getInstance().getApiServices().apilogin(postLogin);
                beanCall.enqueue(new Callback<ResLogin>() {
                    @Override
                    public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {

                        //hidePbar();
                        if(null!=response){
                            if (response.code()==404){
                                Toast.makeText(activity, "Wrong email or password", Toast.LENGTH_SHORT).show();
                            }
                            /*ResLogin responsebody = response.body();
                            int code = response.code();
                            response.headers();
                            if(null==responsebody)
                            {

                                //  showError("oops Error !");
                                //showError("Some Problem occur,Retry");
                            }else */
                                {
                                ResLogin responseBean = response.body();
                                if(null!=responseBean ){
                                   String token=       responseBean.token;

                                    if(token!=null ){
                                        // Edit Student
                                        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.KEY_TOKEN,token);
                                    }else {
                                        Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                    }

                                    //Intent accountsIntent = new Intent(activity, AreaSearching.class);
                                    Intent accountsIntent = new Intent(activity, GetData.class);
                                    accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                                    emptyInputEditText();
                                    startActivity(accountsIntent);
                                    finish();

                                }
                                else{

                                    Toast.makeText(LoginActivity.this,"Error.Please try again.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLogin>call, Throwable t) {
                        Log.e("error", t.toString());
                        Toast.makeText(LoginActivity.this, "Wrong data", Toast.LENGTH_SHORT).show();


                    }
                });
            }
            else{
                Toast.makeText(activity, "Please check internet", Toast.LENGTH_SHORT).show();
             //   hidePbar();

            }



    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
