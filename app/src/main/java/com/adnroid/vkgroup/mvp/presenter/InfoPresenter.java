package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.common.Const;
import com.adnroid.vkgroup.model.Group;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.model.view.InfoContactsViewModel;
import com.adnroid.vkgroup.model.view.InfoLinksViewModel;
import com.adnroid.vkgroup.model.view.InfoStatusViewModel;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.adnroid.vkgroup.rest.api.GroupsApi;
import com.adnroid.vkgroup.rest.model.request.GroupsGetByIdRequestModel;
import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupApi;

    public InfoPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {

        return mGroupApi.getById(new GroupsGetByIdRequestModel(Const.GROUP_ID_VK_FEST).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb)
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }


    private List<BaseViewModel> parsePojoModel(Group group) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new InfoStatusViewModel(group));
        items.add(new InfoContactsViewModel());
        items.add(new InfoLinksViewModel());

        return items;
    }


    public Callable<Group> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(Const.GROUP_ID_VK_FEST))
                    .findFirst();
            return realm.copyFromRealm(result);
        };
    }
}
