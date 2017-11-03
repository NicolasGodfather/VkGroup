package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.mvp.view.BaseFeedView;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseFeedPresenter<V extends BaseFeedView> extends MvpPresenter<V> {

    public static final int START_PAGE_SIZE = 15;
    public static final int NEXT_PAGE_SIZE = 5;

    private boolean mIsInLoading;

    public void loadData(ProgressType progressType, int offset, int count) {
        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;

        onCreateLoadDataObservable(count, offset)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    onLoadingStart(progressType);
                })
                .doFinally(() -> {
                    onLoadingFinish(progressType);
                })
                .subscribe(repositories -> {
                    onLoadingSuccess(progressType, repositories);
                }, error -> {
                    error.printStackTrace();
                    onLoadingFailed(error);
                });
    }

    public abstract Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset);

    // by first load
    public void loadStart() {
        loadData(ProgressType.ListProgress, 0, START_PAGE_SIZE);
    }

    // load new items
    public void loadNext(int offset) {
        loadData(ProgressType.Paging, offset, NEXT_PAGE_SIZE);
    }

    // by refreshing list
    public void loadRefresh() {
        loadData(ProgressType.Refreshing, 0, START_PAGE_SIZE);
    }


    public void onLoadingStart(ProgressType progressType) {
        showProgress(progressType);
    }

    public void onLoadingFinish(ProgressType progressType) {
        mIsInLoading = false;
        hideProgress(progressType);
    }

    public void onLoadingFailed(Throwable throwable) {
        getViewState().showError(throwable.getMessage());
    }

    public void onLoadingSuccess(ProgressType progressType, List<BaseViewModel> items) {
        if (getViewState() != null) {
            if (progressType == ProgressType.Paging) {
                getViewState().addItems(items);
            } else {
                getViewState().setItems(items);
            }
        }
    }

    public enum ProgressType {
        Refreshing, ListProgress, Paging
    }

    public void showProgress(ProgressType progressType) {
        if (getViewState() != null) {
            switch (progressType) {
                case Refreshing:
                    getViewState().showRefreshing();
                    break;
                case ListProgress:
                    getViewState().showListProgress();
                    break;
            }
        }
    }

    public void hideProgress(ProgressType progressType) {
        if (getViewState() != null) {
            switch (progressType) {
                case Refreshing:
                    getViewState().hideRefreshing();
                    break;
                case ListProgress:
                    getViewState().hideListProgress();
                    break;
            }
        }
    }
}
