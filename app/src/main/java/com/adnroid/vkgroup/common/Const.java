package com.adnroid.vkgroup.common;

import com.vk.sdk.VKScope;

public class Const {

    public static final String[] DEFAULT_LOGIN_SCOPE = {VKScope.AUDIO, VKScope.DIRECT, VKScope.VIDEO,
            VKScope.WALL, VKScope.MESSAGES, VKScope.PHOTOS, VKScope.PAGES, VKScope.GROUPS, VKScope.EMAIL,
            VKScope.STATS, VKScope.DOCS};
    public static final Double DEFAULT_VERSION = 5.68;
    public static final int DEFAULT_COUNT = 10; //for concrete number notes on wall
    public static final int GROUP_ID_IT_BRAINS = -16108331; // interface Iterator - null
    public static final int GROUP_ID_FLYING_FUN = -22741624;
    public static final int GROUP_ID_2 = -147166906;
    public static final int GROUP_ID_VK_FEST = -86529522; // Likes : getCount - null
    public static final String DEFAULT_USER_FIELDS = "photo_100";
    public static final String DEFAULT_MEMBER_FIELDS = "name,photo_100";
    public static final String DEFAULT_GROUP_FIELDS = "status,description,site,links,contacts";
    public static final String VIDEOS = "videos";
    public static final String POSTS = "posts";
    public static final String EXTENDED = "extended";
    public static final String OWNER_ID = "owner_id";
    public static final String POST_ID = "post_id";
    public static final String COUNT = "count";
    public static final String OFFSET = "offset";
    public static final String NEED_LIKES = "need_likes";
    public static final String GROUP_ID = "group_id";
    public static final String TOPIC_ID = "topic_id";

    public Const() {
    }
}
