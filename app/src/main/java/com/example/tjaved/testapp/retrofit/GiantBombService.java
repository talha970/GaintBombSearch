package com.example.tjaved.testapp.retrofit;

import com.example.tjaved.testapp.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tjaved on 3/24/18.
 */

public interface GiantBombService {

    @GET("/api/search/?&format=json&resources=game")
    Call<ApiResponse> getResults(@Query("api_key") String api_key, @Query("query") String query);
}
