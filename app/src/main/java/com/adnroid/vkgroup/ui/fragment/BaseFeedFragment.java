package com.adnroid.vkgroup.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.manager.MyLinearLayoutManager;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.mvp.presenter.BaseFeedPresenter;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.adnroid.vkgroup.ui.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView {

    BaseAdapter mAdapter;
    protected BaseFeedPresenter presenter;
    protected ProgressBar mProgressBar;

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpSwipeToRefreshLayout(view);
        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);

        presenter = onCreateFeedPresenter();
        presenter.loadStart();
    }

    private void setUpRecyclerView(View rootView) {
        MyLinearLayoutManager mLinearLayoutManager = new MyLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (mLinearLayoutManager.isOnNextPagePosition()) {
                    presenter.loadNext(mAdapter.getRealItemCount());
                }
            }
        });

        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    protected void setUpAdapter(RecyclerView rv) {
        mAdapter = new BaseAdapter();
        rv.setAdapter(mAdapter);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return 0;
    }

    private void setUpSwipeToRefreshLayout(View rootView) {
        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(() -> onCreateFeedPresenter().loadRefresh()); // to update data
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mProgressBar = getBaseActivity().getProgressBar();
    }

    @Override
    public void showRefreshing() {
    }

    @Override
    public void hideRefreshing() {
        swipeRefresh.setRefreshing(false);
    }


    @Override
    public void showListProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void setItems(List<BaseViewModel> items) {
        mAdapter.setItems(items);
    }

    @Override
    public void addItems(List<BaseViewModel> items) {
        mAdapter.addItems(items);
    }

    protected abstract BaseFeedPresenter onCreateFeedPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
