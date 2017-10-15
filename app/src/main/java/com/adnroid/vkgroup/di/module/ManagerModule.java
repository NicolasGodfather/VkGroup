package com.adnroid.vkgroup.di.module;

import com.adnroid.vkgroup.common.manager.MyFragmentManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Singleton
    @Provides
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }
}
