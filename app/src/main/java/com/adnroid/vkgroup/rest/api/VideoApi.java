package com.adnroid.vkgroup.rest.api;

import com.adnroid.vkgroup.rest.model.response.Full;
import com.adnroid.vkgroup.rest.model.response.VideoResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface VideoApi {

    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideoResponse>> get(@QueryMap Map<String, String> map);

}
