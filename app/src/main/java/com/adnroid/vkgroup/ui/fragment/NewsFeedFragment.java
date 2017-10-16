package com.adnroid.vkgroup.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.adapter.BaseAdapter;
import com.adnroid.vkgroup.model.WallItem;
import com.adnroid.vkgroup.model.view.NewsItemBodyViewModel;
import com.adnroid.vkgroup.rest.api.WallApi;
import com.adnroid.vkgroup.rest.model.request.WallGetRequestModel;
import com.adnroid.vkgroup.rest.model.response.WallGetResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi wallApi;

    RecyclerView recyclerView;
    BaseAdapter baseAdapter;

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
        wallApi.get(new WallGetRequestModel(-86529522).toMap())
                .enqueue(new Callback<WallGetResponse>() {
                    @Override
                    public void onResponse(Call<WallGetResponse> call, Response<WallGetResponse> response) {
                        Toast.makeText(getActivity(), "Like: " + response.body().response.getItems().get(0).getLikes(), Toast.LENGTH_SHORT).show();

                        List<NewsItemBodyViewModel> list = new ArrayList<>();
                        for (WallItem item : response.body().response.getItems()) {
                            list.add(new NewsItemBodyViewModel(item));
                        }
                    }

                    @Override
                    public void onFailure(Call<WallGetResponse> call, Throwable t) {
                        t.getMessage();
                    }
                });
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_news_feed;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_feed, container, false);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);
        setUpAdapter(recyclerView);
    }

    private void setUpRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    protected void setUpAdapter(RecyclerView rv) {
        baseAdapter = new BaseAdapter();
        rv.setAdapter(baseAdapter);
    }

}
