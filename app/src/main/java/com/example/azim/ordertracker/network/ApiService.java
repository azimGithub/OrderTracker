package com.example.azim.ordertracker.network;

import com.example.azim.ordertracker.model.Store;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("contacts.php")
    Single<List<Store>> getStore(@Query("source") String source, @Query("search") String query);

}
