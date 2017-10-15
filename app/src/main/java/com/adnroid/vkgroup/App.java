package com.adnroid.vkgroup;

import android.app.Application;

import com.adnroid.vkgroup.di.component.ApplicationComponent;
import com.adnroid.vkgroup.di.component.DaggerApplicationComponent;
import com.adnroid.vkgroup.di.module.ApplicationModule;
import com.vk.sdk.VKSdk;

public class App extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        VKSdk.initialize(this);
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
