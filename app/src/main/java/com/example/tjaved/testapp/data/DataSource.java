package com.example.tjaved.testapp.data;

import com.example.tjaved.testapp.data.model.ApiResponse;

import java.util.List;

/**
 * Created by tjaved on 3/24/18.
 */

public abstract class DataSource {


    public interface GetQueryCallback {
        void onSuccess(ApiResponse photos);

        void onFailure(Throwable throwable);

        void onNetworkFailure();

    }


}