package com.example.tjaved.testapp.views;

import com.example.tjaved.testapp.data.model.Result;

import java.util.List;

/**
 * Created by tjaved on 3/24/18.
 */

public interface MainView extends BaseView {
    void showResults(List<Result> resultList);

    void showEmptyResults(boolean show);

    void showIntroView(boolean show);

    void showApiFailure(boolean show);

    void showNetworkError(boolean show);

    void setProgressbar(boolean show);


}
