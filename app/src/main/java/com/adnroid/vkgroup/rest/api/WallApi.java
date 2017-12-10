package com.adnroid.vkgroup.rest.api;

import com.adnroid.vkgroup.rest.model.response.GetWallByIdResponse;
import com.adnroid.vkgroup.rest.model.response.GetWallResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

}
