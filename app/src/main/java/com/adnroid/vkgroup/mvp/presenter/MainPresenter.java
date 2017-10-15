package com.adnroid.vkgroup.mvp.presenter;

import com.adnroid.vkgroup.CurrentUser;
import com.adnroid.vkgroup.mvp.view.MainView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState // for
public class MainPresenter extends MvpPresenter<MainView> {

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else  {
            getViewState().signedId();
        }
    }
}
