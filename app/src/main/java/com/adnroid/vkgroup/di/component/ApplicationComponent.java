package com.adnroid.vkgroup.di.component;

import com.adnroid.vkgroup.di.module.ApplicationModule;
import com.adnroid.vkgroup.di.module.ManagerModule;
import com.adnroid.vkgroup.di.module.RestModule;
import com.adnroid.vkgroup.mvp.presenter.NewsFeedPresenter;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.activity.MainActivity;
import com.adnroid.vkgroup.ui.fragment.NewsFeedFragment;
import com.adnroid.vkgroup.ui.holder.NewsItemBodyHolder;
import com.adnroid.vkgroup.ui.holder.NewsItemFooterHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})// bind part that request dependencies
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment newsFeedFragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);

    //presenter
    void inject(NewsFeedPresenter presenter);

}
