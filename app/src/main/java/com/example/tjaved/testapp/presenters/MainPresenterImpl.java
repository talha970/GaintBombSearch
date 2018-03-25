package com.example.tjaved.testapp.presenters;

import android.app.SearchManager;
import android.content.Intent;
import android.util.Log;

import com.example.tjaved.testapp.data.DataManager;
import com.example.tjaved.testapp.data.DataSource;
import com.example.tjaved.testapp.data.model.ApiResponse;
import com.example.tjaved.testapp.data.model.Result;
import com.example.tjaved.testapp.views.BaseView;
import com.example.tjaved.testapp.views.MainView;

import java.util.List;

import javax.inject.Inject;



/**
 * Created by tjaved on 3/24/18.
 */

public class MainPresenterImpl implements IMainPresenter {
    private static final String TAG = "MainPresenterImpl";
    @Inject
    DataManager dataManager;

    @Inject
    public MainPresenterImpl() {

    }

    MainView mainView;

    @Override
    public void loadData(String query) {
        showProgressBar();
        dataManager.getResults(query, new DataSource.GetQueryCallback() {
            @Override
            public void onSuccess(ApiResponse result) {
               List<Result> resultList= result.getResults();
                Log.d(TAG, String.valueOf(resultList.size()));
                mainView.setProgressbar(false);
                if(!resultList.isEmpty())
                    mainView.showResults(resultList);
                else
                   showEmptyView();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onNetworkFailure() {
                showNetworkError();
            }
        });
    }

    @Override
    public void handleSearchIntent(Intent intent) {
       if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
               String query = intent.getStringExtra(SearchManager.QUERY);
               Log.d(TAG,"handleSearchIntent "+query);
               loadData(query);
       }

    }

    @Override
    public void loadIntroView() {
        showIntroView();
    }

    @Override
    public void attachView(BaseView view) {

        this.mainView= (MainView) view;
    }

    @Override
    public void detachView() {
        this.mainView=null;
    }

    private void showProgressBar(){
        mainView.setProgressbar(true);
        mainView.showNetworkError(false);
        mainView.showEmptyResults(false);
        mainView.showIntroView(false);
    }
    private void showNetworkError(){
        mainView.setProgressbar(false);
        mainView.showNetworkError(true);
        mainView.showEmptyResults(false);
        mainView.showIntroView(false);
    }
    private void showEmptyView(){
        mainView.setProgressbar(false);
        mainView.showNetworkError(false);
        mainView.showEmptyResults(true);
        mainView.showIntroView(false);
    }
    private void showIntroView(){
        mainView.setProgressbar(false);
        mainView.showNetworkError(false);
        mainView.showEmptyResults(false);
        mainView.showIntroView(true);
    }
}
