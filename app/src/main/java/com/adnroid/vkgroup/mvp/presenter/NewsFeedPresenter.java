package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.common.CurrentUser;
import com.adnroid.vkgroup.common.utils.VkListHelper;
import com.adnroid.vkgroup.model.WallItem;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.model.view.NewsItemBodyViewModel;
import com.adnroid.vkgroup.model.view.NewsItemFooterViewModel;
import com.adnroid.vkgroup.model.view.NewsItemHeaderViewModel;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.adnroid.vkgroup.rest.api.WallApi;
import com.adnroid.vkgroup.rest.model.request.WallGetRequestModel;
import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.adnroid.vkgroup.common.Const.GROUP_ID_VK_FEST;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    WallApi mWallApi;
    private boolean enableIdFiltering = false;

    public void setEnableIdFiltering(boolean enableIdFiltering) {
        this.enableIdFiltering = enableIdFiltering;
    }

    public NewsFeedPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.get(new WallGetRequestModel(GROUP_ID_VK_FEST, count, offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .compose(applyFilter()) //
                .doOnNext(this::saveToDb)
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                });
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
    }

    private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new NewsItemHeaderViewModel(wallItem));
        baseItems.add(new NewsItemBodyViewModel(wallItem));
        baseItems.add(new NewsItemFooterViewModel(wallItem));
        return baseItems;
    }

    /**
     * @return List Observable from DB
     */
    public Callable<List<WallItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"date"};
            Sort[] sortOrder = {Sort.DESCENDING};
            Realm realm = Realm.getDefaultInstance();
            RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(realmResults);
        };
    }

    /**
     * @return отфильтрованный по id текущего пользователя Observable (если true) или не измененный Observable (если false)
     */
    protected ObservableTransformer<WallItem, WallItem> applyFilter() {
        if (enableIdFiltering && CurrentUser.getId() != null) {
            return baseItemObservable -> baseItemObservable.
                    filter(wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId())));
        } else {
            return baseItemObservable -> baseItemObservable;
        }
    }

}
