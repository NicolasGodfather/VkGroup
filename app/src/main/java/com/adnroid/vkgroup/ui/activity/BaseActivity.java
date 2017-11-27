package com.adnroid.vkgroup.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.manager.MyFragmentManager;
import com.adnroid.vkgroup.ui.fragment.BaseFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    MyFragmentManager fragmentManager;

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        App.getApplicationComponent().inject(this);

        setSupportActionBar(toolBar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.main_wrapper);
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
        fragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    public void addContent(BaseFragment fragment) {
        fragmentManager.addFragment(this, fragment, R.id.main_wrapper);
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
