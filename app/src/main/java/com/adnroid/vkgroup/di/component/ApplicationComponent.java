package com.adnroid.vkgroup.di.component;

import com.adnroid.vkgroup.di.module.ApplicationModule;
import com.adnroid.vkgroup.di.module.ManagerModule;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class})// bind part that request dependencies
public interface ApplicationComponent {

    void inject(BaseActivity activity);
    void inject(MainActivity activity);

}
