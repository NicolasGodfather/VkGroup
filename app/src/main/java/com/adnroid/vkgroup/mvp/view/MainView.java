package com.adnroid.vkgroup.mvp.view;

import com.adnroid.vkgroup.model.Profile;
import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void startSignIn();

    void signedId();

    void showCurrentUser(Profile profile);

}
