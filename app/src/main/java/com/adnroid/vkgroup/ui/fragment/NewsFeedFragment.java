package com.adnroid.vkgroup.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.utils.VkListHelper;
import com.adnroid.vkgroup.model.WallItem;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.model.view.NewsItemBodyViewModel;
import com.adnroid.vkgroup.model.view.NewsItemFooterViewModel;
import com.adnroid.vkgroup.model.view.NewsItemHeaderViewModel;
import com.adnroid.vkgroup.rest.api.WallApi;
import com.adnroid.vkgroup.rest.model.request.WallGetRequestModel;
import com.adnroid.vkgroup.rest.model.response.GetWallResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

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
//-22741624 22741624 -147166906 -16108331
        mWallApi.get(new WallGetRequestModel(-16108331).toMap()).enqueue(new Callback<GetWallResponse>() {
            @Override
            public void onResponse(Call<GetWallResponse> call, Response<GetWallResponse> response) {
                List<WallItem> wallItems = VkListHelper.getWallList(response.body().response);
                List<BaseViewModel> list = new ArrayList<>();

                for (WallItem item : wallItems) {
                    list.add(new NewsItemHeaderViewModel(item));
                    list.add(new NewsItemBodyViewModel(item));
                    list.add(new NewsItemFooterViewModel(item));
                }
                mAdapter.addItems(list);
                Toast.makeText(getActivity(), "Likes: " + response.body().response.getItems().get(0).getLikes().getCount(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GetWallResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

//        mWallApi.get(new WallGetRequestModel(-16108331).toMap()
//                .flatMap(new Function<GetWallResponse, ObservableSource<WallItem>>() {
//                    @Override
//                    public ObservableSource<WallItem> apply(@NonNull GetWallResponse getWallResponse) throws Exception {
//                        return Observable.fromIterable(VkListHelper.getWallList(getWallResponse.response));
//                    }
//                })
//                .flatMap(new Function<WallItem, ObservableSource<BaseViewModel>>() {
//                    @Override
//                    public ObservableSource<BaseViewModel> apply(@NonNull WallItem wallItem) throws Exception {
//                        List<BaseViewModel> baseItems = new ArrayList<>();
//                        baseItems.add(new NewsItemHeaderViewModel(wallItem));
//                        baseItems.add(new NewsItemBodyViewModel(wallItem));
//                        baseItems.add(new NewsItemFooterViewModel(wallItem));
//                        return Observable.fromIterable(baseItems);
//                    }
//                })
//                .toList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<BaseViewModel>>() {
//                    @Override
//                    public void accept(List<BaseViewModel> objects) throws Exception {
//                        mAdapter.addItems(objects);
//                    }
//                });
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

}
