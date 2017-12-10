package com.adnroid.vkgroup.model.view.attachment;

import android.view.View;

import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.attachmaent.video.Video;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.ui.view.holder.attachment.VideoAttachmentHolder;

public class VideoAttachmentViewModel extends BaseViewModel {

    private int id;
    private int ownerId;

    private String mTitle;
    private String mViewCount;
    private String mDuration;
    private String mImageUrl;

    public VideoAttachmentViewModel(Video video) {
        this.id = video.getId();
        this.ownerId = video.getOwnerId();

        if (video.getTitle().equals("")) {
            mTitle = "Video";
        } else {
            mTitle = video.getTitle();
        }

        mViewCount = Utils.formatViewsCount(video.getViews());

        mDuration = Utils.parseDuration(video.getDuration());

        mImageUrl = video.getPhoto320();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentVideo;
    }

    @Override
    public VideoAttachmentHolder onCreateViewHolder(View view) {
        return new VideoAttachmentHolder(view);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getViewCount() {
        return mViewCount;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
