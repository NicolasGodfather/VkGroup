package com.adnroid.vkgroup.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// root level response
public class Full<T> {

    @SerializedName("response")
    @Expose
    public T response;
}
