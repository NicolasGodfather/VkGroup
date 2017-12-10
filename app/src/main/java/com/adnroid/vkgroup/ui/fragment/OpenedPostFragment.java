package com.adnroid.vkgroup.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.model.view.NewsItemFooterViewModel;
import com.adnroid.vkgroup.mvp.presenter.BaseFeedPresenter;
import com.adnroid.vkgroup.mvp.presenter.OpenedPostPresenter;
import com.adnroid.vkgroup.mvp.view.OpenedPostView;
import com.adnroid.vkgroup.ui.view.holder.NewsItemFooterHolder;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenedPostFragment extends BaseFeedFragment implements OpenedPostView {


    @BindView(R.id.rv_footer)
    View mFooter;

    @InjectPresenter
    OpenedPostPresenter mPresenter;

    int id;

    public static OpenedPostFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        OpenedPostFragment fragment = new OpenedPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_opened_wall_item;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_post;
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setId(id);
        return mPresenter;
    }

    @Override
    public void setFooter(NewsItemFooterViewModel viewModel) {
        mFooter.setVisibility(View.VISIBLE);
        new NewsItemFooterHolder(mFooter).bindViewHolder(viewModel);
    }

}
