package com.adnroid.vkgroup.mvp.view;

import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.arellomobile.mvp.MvpView;

import java.util.List;

/**
 * To display lists: wall, comments, talks
 */
public interface BaseFeedView extends MvpView {

    void showRefreshing(); // show animation loading in header

    void hideRefreshing();

    void showListProgress(); // show animation loading in center, when list appears on the screen

    void hideListProgress();

    void showError(String message);

    void setItems(List<BaseViewModel> items); // replacing an existing list with a new one

    void addItems(List<BaseViewModel> items);

}
