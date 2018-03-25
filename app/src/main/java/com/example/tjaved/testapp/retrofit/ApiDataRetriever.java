package com.example.tjaved.testapp.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.tjaved.testapp.R;
import com.example.tjaved.testapp.data.DataSource;
import com.example.tjaved.testapp.data.model.ApiResponse;
import com.example.tjaved.testapp.utils.NetworkHelper;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tjaved on 3/24/18.
 */

public class ApiDataRetriever {

    Retrofit retrofit;
    NetworkHelper networkHelper;

    @Inject
    public ApiDataRetriever(Retrofit retrofit, NetworkHelper networkHelper) {
        this.retrofit = retrofit;
        this.networkHelper = networkHelper;
    }

    public void getResponse(Context context, String query, final DataSource.GetQueryCallback callback) {
        if (networkHelper.isNetworkAvailable()) {
            //Create a retrofit call object
            Call<ApiResponse> response = retrofit.
                    create(GiantBombService.class).
                    getResults(context.getResources().getString(R.string.API_KEY), query);

            //Enqueue the call
            response.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    callback.onFailure(t);
                }

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    callback.onSuccess(response.body());
                }

            });
        } else
            callback.onNetworkFailure();
    }
}
