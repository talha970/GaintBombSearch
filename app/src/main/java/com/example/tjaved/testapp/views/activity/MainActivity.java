package com.example.tjaved.testapp.views.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tjaved.testapp.App;
import com.example.tjaved.testapp.R;
import com.example.tjaved.testapp.adapter.ResultsRecycleAdapter;
import com.example.tjaved.testapp.data.model.Result;
import com.example.tjaved.testapp.presenters.MainPresenterImpl;
import com.example.tjaved.testapp.views.MainView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, ResultsRecycleAdapter.RecycleItemOnClickListener {

    private static final String TAG = "MainActivity";

    @Inject
    MainPresenterImpl mainPresenter;

    @BindView(R.id.searchRV)
    RecyclerView searchRv;
    @BindView(R.id.loading_view)
    LottieAnimationView lottieAnimationView;
    @BindView(R.id.search_bar)
    SearchView searchView;
    @BindView(R.id.llnetworkerror)
    View netErrorView;
    @BindView(R.id.llemptyview)
    View resultEmptyView;
    @BindView(R.id.llintroview)
    View introView;

    ResultsRecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getAppComponent().inject(this);
        ButterKnife.bind(this);
        initPresenter();
        initUI();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        adapter.setResultList(Collections.<Result>emptyList());
        mainPresenter.handleSearchIntent(intent);

    }


    public void initUI() {
        initSearchBar();
        adapter = new ResultsRecycleAdapter(this, new ArrayList<Result>());
        searchRv.setLayoutManager(new LinearLayoutManager(this));
        searchRv.setAdapter(adapter);

        mainPresenter.loadIntroView();

    }

    private void initSearchBar() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconified(false);

    }

    public void initPresenter() {
        mainPresenter.attachView(this);

    }

    @Override
    public void showResults(List<Result> resultList) {
        netErrorView.setVisibility(View.GONE);
        Log.d(TAG, String.valueOf(resultList.size()));
        adapter.setResultList(resultList);
    }

    @Override
    public void showEmptyResults(boolean show) {
        if (show)
            resultEmptyView.setVisibility(View.VISIBLE);
        else
            resultEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showIntroView(boolean show) {
        if (show)
            introView.setVisibility(View.VISIBLE);
        else
            introView.setVisibility(View.GONE);
    }

    @Override
    public void showApiFailure(boolean show) {

    }

    @Override
    public void showNetworkError(boolean show) {
        if (show)
            netErrorView.setVisibility(View.VISIBLE);
        else
            netErrorView.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void setProgressbar(boolean show) {
        if (!show) {
            lottieAnimationView.setVisibility(View.GONE);
            searchRv.setVisibility(View.VISIBLE);
        } else {
            lottieAnimationView.setVisibility(View.VISIBLE);
            searchRv.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(Result result) {
        Toast.makeText(this, result.getName(), Toast.LENGTH_SHORT).show();
    }
}
