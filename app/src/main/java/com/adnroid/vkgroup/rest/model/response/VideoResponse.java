package com.adnroid.vkgroup.rest.model.response;

import com.adnroid.vkgroup.model.attachmaent.video.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {

    public int count;
    @SerializedName("items")
    @Expose
    public List<Video> items;

}
