
package com.adnroid.vkgroup.model.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Reposts extends RealmObject {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("user_reposted")
    @Expose
    private int userReposted;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserReposted() {
        return userReposted;
    }

    public void setUserReposted(int userReposted) {
        this.userReposted = userReposted;
    }

}
