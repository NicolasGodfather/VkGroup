package com.adnroid.vkgroup.di.module;

import com.adnroid.vkgroup.rest.RestClient;
import com.adnroid.vkgroup.rest.api.BoardApi;
import com.adnroid.vkgroup.rest.api.GroupsApi;
import com.adnroid.vkgroup.rest.api.UsersApi;
import com.adnroid.vkgroup.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {

    private RestClient mRestClient;

    public RestModule() {
        this.mRestClient = new RestClient();
    }

    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Provides
    @Singleton
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Provides
    @Singleton
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }

    @Provides
    @Singleton
    public BoardApi provideBoardApi() {
        return mRestClient.createService(BoardApi.class);
    }

}
