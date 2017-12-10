package com.adnroid.vkgroup.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.mvp.presenter.BaseFeedPresenter;
import com.adnroid.vkgroup.mvp.presenter.InfoPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;

public class InfoFragment extends BaseFeedFragment {

    @InjectPresenter
    InfoPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_info;
    }

}
