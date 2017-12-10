package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.common.Const;
import com.adnroid.vkgroup.model.Member;
import com.adnroid.vkgroup.model.Topic;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.model.view.TopicViewModel;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.adnroid.vkgroup.rest.api.BoardApi;
import com.adnroid.vkgroup.rest.model.request.BoardGetTopicsRequestModel;
import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView> {
    @Inject
    BoardApi mBoardApi;

    public BoardPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getTopics(new BoardGetTopicsRequestModel(
                Const.GROUP_ID_VK_FEST, count, offset).toMap())
                .flatMap(baseItemResponseFull -> Observable.fromIterable(baseItemResponseFull.response.getItems()))
                .doOnNext(topic -> topic.setGroupId(Const.GROUP_ID_VK_FEST))
                .doOnNext(this::saveToDb)
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }


    public Callable<List<Topic>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.DESCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Topic> results = realm.where(Topic.class)
                    .equalTo("groupId", Const.GROUP_ID_VK_FEST)
                    .findAllSorted(sortFields, sortOrder);

            return realm.copyFromRealm(results);
        };
    }
}
