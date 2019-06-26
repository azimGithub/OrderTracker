package com.example.azim.ordertracker.network;


import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;


/**
 * Network Api class to handle service calls
 * Created by Rahul Gupta on 19/1/16.
 * Example :-
 * <pre>
 * {@code
 * Call<CategoriesRespBean> call = mApiServices.categories(new GeneralReqBean(mUserData.getUser_id()));
 * call.enqueue(new Callback<CategoriesRespBean>() {
 *
 * @Override
 * public void onResponse(Response<CategoriesRespBean> response, Retrofit retrofit) {
 *      // Do something with response
 * }
 *
 * @Override
 * public void onFailure(Throwable t) {
 *      // Handle error case
 * }
 * });
 * </pre>
 */
public class AppRetrofitRate {

    private static AppRetrofitRate mInstance;
    ApiServices apiServices;

    private AppRetrofitRate() {

        //for logging
        okhttp3.logging.HttpLoggingInterceptor interceptor = new okhttp3.logging.HttpLoggingInterceptor();
        interceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);

        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.connectTimeout(ApiConstants.timeoutSeconds, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        okhttp3.OkHttpClient  client = builder.build();

//        client.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
        //rest adapter
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        /*.serializeNulls()*/
                        .create()))
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    public static synchronized AppRetrofitRate getInstance() {
        if (mInstance == null)
            mInstance = new AppRetrofitRate();
        return mInstance;
    }

    public ApiServices getApiServices() {
        return apiServices;
    }
}
