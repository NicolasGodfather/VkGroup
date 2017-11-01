package com.adnroid.vkgroup.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.manager.MyFragmentManager;
import com.adnroid.vkgroup.ui.fragment.BaseFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    MyFragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        App.getApplicationComponent().inject(this);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolBar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.mainWrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);

    }

    @LayoutRes // return link for this layout
    protected abstract int getMainContentLayout();

    public void fragmentOnScreen(BaseFragment fragment) {
        setToolbarTitle(fragment.createToolBarTitle(this));
    }

    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setContent(BaseFragment fragment) {
        fragmentManager.setFragment(this, fragment, R.id.mainWrapper);
    }

    public void addContent(BaseFragment fragment) {
        fragmentManager.addFragment(this, fragment, R.id.mainWrapper);
    }

    public boolean removeCurrentFragment() {
        return fragmentManager.removeCurrentFragment(this);
    }

    public boolean removeCurrentFragment(BaseFragment fragment) {
        return fragmentManager.removeFragment(this, fragment);
    }

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}
