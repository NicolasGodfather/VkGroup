package com.adnroid.vkgroup.di.module;

import com.adnroid.vkgroup.rest.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {

    private RestClient restClient;

    public RestModule() {
        this.restClient = restClient;
    }

    @Singleton
    @Provides
    public RestClient provideRestClient() {
        return restClient;
    }
}
