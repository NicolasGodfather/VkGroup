package com.adnroid.vkgroup.di.component;

import com.adnroid.vkgroup.di.module.ApplicationModule;
import com.adnroid.vkgroup.di.module.ManagerModule;
import com.adnroid.vkgroup.di.module.RestModule;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.activity.MainActivity;
import com.adnroid.vkgroup.ui.fragment.NewsFeedFragment;

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

}
