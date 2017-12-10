package com.adnroid.vkgroup.rest.api;

import com.adnroid.vkgroup.model.Topic;
import com.adnroid.vkgroup.rest.model.response.BaseItemResponse;
import com.adnroid.vkgroup.rest.model.response.Full;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {

    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);
}
