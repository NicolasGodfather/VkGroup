package com.adnroid.vkgroup;

import android.app.Application;
import android.content.Context;

import com.adnroid.vkgroup.di.component.ApplicationComponent;
import com.adnroid.vkgroup.di.component.DaggerApplicationComponent;
import com.adnroid.vkgroup.di.module.ApplicationModule;
import com.vk.sdk.VKSdk;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static ApplicationComponent applicationComponent;
    @Inject
    Context context;

    Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        VKSdk.initialize(this);

        initRealm();
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
