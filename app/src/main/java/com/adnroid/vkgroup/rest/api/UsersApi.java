package com.adnroid.vkgroup.rest.api;

import com.adnroid.vkgroup.model.Profile;
import com.adnroid.vkgroup.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface UsersApi {

    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);

}
