package com.adnroid.vkgroup.rest.model;

import com.adnroid.vkgroup.common.Const;
import com.adnroid.vkgroup.rest.model.request.BaseRequestModel;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WallGetCommentsRequestModel extends BaseRequestModel {

    @SerializedName(Const.OWNER_ID)
    int ownerId;
    @SerializedName(Const.POST_ID)
    int postId;
    @SerializedName(Const.COUNT)
    int count = Const.DEFAULT_COUNT;
    @SerializedName(Const.OFFSET)
    int offset = 0;
    @SerializedName(Const.EXTENDED)
    int extended = 1;
    @SerializedName(Const.NEED_LIKES)
    int needLikes = 1;

    public WallGetCommentsRequestModel(int ownerId, int postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    public WallGetCommentsRequestModel(int ownerId, int postId, int offset) {
        this.ownerId = ownerId;
        this.postId = postId;
        this.offset = offset;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }

    public int getNeedLikes() {
        return needLikes;
    }

    public void setNeedLikes(int needLikes) {
        this.needLikes = needLikes;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(Const.OWNER_ID, String.valueOf(getOwnerId()));
        map.put(Const.POST_ID, String.valueOf(getPostId()));
        map.put(Const.COUNT, String.valueOf(getCount()));
        map.put(Const.OFFSET, String.valueOf(getOffset()));
        map.put(Const.EXTENDED, String.valueOf(getExtended()));
        map.put(Const.NEED_LIKES, String.valueOf(getNeedLikes()));
    }

}