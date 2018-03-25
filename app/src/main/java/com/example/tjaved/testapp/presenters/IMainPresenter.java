package com.example.tjaved.testapp.presenters;

import android.content.Intent;

/**
 * Created by tjaved on 3/24/18.
 */

public interface IMainPresenter extends BasePresenter {
    void loadData(String query);
    void handleSearchIntent(Intent intent);
    void loadIntroView();

}
