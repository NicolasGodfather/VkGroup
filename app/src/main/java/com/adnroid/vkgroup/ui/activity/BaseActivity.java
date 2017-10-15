package com.adnroid.vkgroup.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.ui.fragment.BaseFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolBar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.mainFrame);
        getLayoutInflater().inflate(getMainContentLayout(), parent);

    }

    @LayoutRes // return link for this layout
    protected abstract int getMainContentLayout();

    public void fragmentOnScreen(BaseFragment fragment) {

    }
}
