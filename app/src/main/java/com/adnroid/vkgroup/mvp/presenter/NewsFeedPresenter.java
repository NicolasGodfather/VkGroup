package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.common.utils.VkListHelper;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.model.view.NewsItemBodyViewModel;
import com.adnroid.vkgroup.model.view.NewsItemFooterViewModel;
import com.adnroid.vkgroup.model.view.NewsItemHeaderViewModel;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.adnroid.vkgroup.rest.api.WallApi;
import com.adnroid.vkgroup.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    WallApi mWallApi;

    public NewsFeedPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
// -22741624 22741624 -147166906
// -16108331 - It brains
        return mWallApi.get(new WallGetRequestModel(-147166906).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                });
    }

}
