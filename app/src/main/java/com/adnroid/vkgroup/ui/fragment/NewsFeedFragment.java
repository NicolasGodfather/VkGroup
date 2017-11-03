package com.adnroid.vkgroup.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.mvp.presenter.BaseFeedPresenter;
import com.adnroid.vkgroup.mvp.presenter.NewsFeedPresenter;
import com.adnroid.vkgroup.rest.api.WallApi;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;


public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;
    @InjectPresenter
    NewsFeedPresenter presenter;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return presenter;
    }

}
