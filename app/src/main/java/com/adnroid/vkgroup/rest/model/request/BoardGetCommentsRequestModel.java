package com.adnroid.vkgroup.rest.model.request;

import com.adnroid.vkgroup.common.Const;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BoardGetCommentsRequestModel extends BaseRequestModel {

    @SerializedName(Const.GROUP_ID)
    int groupId;
    @SerializedName(Const.TOPIC_ID)
    int topicId;
    @SerializedName(Const.COUNT)
    int count = Const.DEFAULT_COUNT;
    @SerializedName(Const.OFFSET)
    int offset = 0;
    @SerializedName(Const.EXTENDED)
    int extended = 1;
    @SerializedName(Const.NEED_LIKES)
    int needLikes = 1;

    public BoardGetCommentsRequestModel(int groupId, int topicId) {
        this.groupId = Math.abs(groupId);
        this.topicId = topicId;
    }

    public BoardGetCommentsRequestModel(int groupId, int topicId, int offset) {
        this.groupId = Math.abs(groupId);
        this.topicId = topicId;
        this.offset = offset;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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
        map.put(Const.GROUP_ID, String.valueOf(getGroupId()));
        map.put(Const.TOPIC_ID, String.valueOf(getTopicId()));
        map.put(Const.COUNT, String.valueOf(getCount()));
        map.put(Const.OFFSET, String.valueOf(getOffset()));
        map.put(Const.EXTENDED, String.valueOf(getExtended()));
        map.put(Const.NEED_LIKES, String.valueOf(getNeedLikes()));
    }

}
