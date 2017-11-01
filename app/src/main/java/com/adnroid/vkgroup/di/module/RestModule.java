package com.adnroid.vkgroup.di.module;

import com.adnroid.vkgroup.rest.RestClient;
import com.adnroid.vkgroup.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {

    private RestClient restClient;

    public RestModule() {
        this.restClient = new RestClient();
    }

    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return restClient;
    }

    @Provides
    @Singleton
    public WallApi provideWallApi() {
        return restClient.createService(WallApi.class);
    }
}
