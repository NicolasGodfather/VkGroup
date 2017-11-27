package com.adnroid.vkgroup.mvp.view;

import com.adnroid.vkgroup.model.Profile;
import com.adnroid.vkgroup.ui.fragment.BaseFragment;
import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void startSignIn();

    void signedId();

    void showCurrentUser(Profile profile);

    void showFragmentFromDrawer(BaseFragment baseFragment);

}
