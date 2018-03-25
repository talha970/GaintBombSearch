package com.example.tjaved.testapp.presenters;

import com.example.tjaved.testapp.views.BaseView;

/**
 * Created by tjaved on 3/24/18.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
