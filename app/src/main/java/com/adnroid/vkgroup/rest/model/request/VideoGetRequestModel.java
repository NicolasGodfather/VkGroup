package com.adnroid.vkgroup.rest.model.request;

import com.adnroid.vkgroup.common.Const;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class VideoGetRequestModel extends BaseRequestModel {

    @SerializedName(Const.VIDEOS)
    String videos;

    public VideoGetRequestModel() {
    }

    public VideoGetRequestModel(String ownerId, String videoId) {
        this.videos = ownerId + "_" + videoId;
    }

    public VideoGetRequestModel(int ownerId, int videoId) {
        this.videos = ownerId + "_" + videoId;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(Const.VIDEOS, getVideos());
    }
}
