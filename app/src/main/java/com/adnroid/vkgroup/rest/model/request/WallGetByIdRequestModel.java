package com.adnroid.vkgroup.rest.model.request;

import com.adnroid.vkgroup.common.Const;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WallGetByIdRequestModel extends BaseRequestModel {

    @SerializedName(Const.POSTS)
    private String posts;

    @SerializedName(Const.EXTENDED)
    private int extended = 1;

    public WallGetByIdRequestModel(int ownerId, int postId) {
        this.posts = ownerId + "_" + postId;
    }

    private String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    private int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }


    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(Const.POSTS, getPosts());
        map.put(Const.EXTENDED, String.valueOf(getExtended()));
    }
}