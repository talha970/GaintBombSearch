package com.example.tjaved.testapp.data;

import android.content.Context;


import com.example.tjaved.testapp.retrofit.ApiDataRetriever;


import javax.inject.Inject;


/**
 * Created by tjaved on 3/24/18.
 */

public class DataManager {

    Context context;

    ApiDataRetriever apiDataRetriever;


    @Inject
    public DataManager(Context context, ApiDataRetriever apiDataRetriever) {

        this.context = context;
        this.apiDataRetriever = apiDataRetriever;
    }

    public void getResults(String query, final DataSource.GetQueryCallback callback) {
        apiDataRetriever.getResponse(context, query, callback);
    }

}
