
package com.adnroid.vkgroup.model.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Comments extends RealmObject {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("can_post")
    @Expose
    private int canPost;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCanPost() {
        return canPost;
    }

    public void setCanPost(int canPost) {
        this.canPost = canPost;
    }

}
