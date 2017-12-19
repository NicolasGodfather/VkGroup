package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.common.utils.VkListHelper;
import com.adnroid.vkgroup.model.CommentItem;
import com.adnroid.vkgroup.model.Place;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.model.view.CommentBodyViewModel;
import com.adnroid.vkgroup.model.view.CommentFooterViewModel;
import com.adnroid.vkgroup.model.view.CommentHeaderViewModel;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.adnroid.vkgroup.rest.api.WallApi;
import com.adnroid.vkgroup.rest.model.WallGetCommentsRequestModel;
import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class CommentsPresenter extends BaseFeedPresenter<BaseFeedView> {

    private Place mPlace;

    @Inject
    WallApi mWallApi;

    public CommentsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.getComments(new WallGetCommentsRequestModel(
                Integer.parseInt(mPlace.getOwnerId()), Integer.parseInt(mPlace.getPostId()), offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response)))
                .doOnNext(commentItem -> commentItem.setPlace(mPlace))
                .doOnNext(this::saveToDb)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.mPlace) && !commentItem.isFromTopic)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new CommentHeaderViewModel(commentItem));
        baseItems.add(new CommentBodyViewModel(commentItem));
        baseItems.add(new CommentFooterViewModel(commentItem));
        return baseItems;
    }

    public Callable<List<CommentItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"id"};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<CommentItem> results = realm.where(CommentItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }

    public void setPlace(Place place) {
        this.mPlace = place;
    }

}
