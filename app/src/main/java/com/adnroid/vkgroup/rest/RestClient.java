package com.adnroid.vkgroup.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String VK_BASE_URL = "hhtp://api.vk.method/";

    private Retrofit retrofit;

    public RestClient() {
        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(VK_BASE_URL)
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
