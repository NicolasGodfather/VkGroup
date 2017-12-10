package com.adnroid.vkgroup.model.view;

import android.view.View;

import com.adnroid.vkgroup.model.WallItem;
import com.adnroid.vkgroup.model.counter.CommentCounterViewModel;
import com.adnroid.vkgroup.model.counter.LikeCounterViewModel;
import com.adnroid.vkgroup.model.counter.RepostCounterViewModel;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;
import com.adnroid.vkgroup.ui.view.holder.NewsItemFooterHolder;

public class NewsItemFooterViewModel extends BaseViewModel{

    private int mId;
    private int ownerId;
    private long mDateLong;
    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mreposts;

    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.mDateLong = wallItem.getDate();
        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());
        this.mreposts = new RepostCounterViewModel(wallItem.getReposts());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }

    public long getDateLong() {
        return mDateLong;
    }

    public void setDateLong(long mDateLong) {
        this.mDateLong = mDateLong;
    }

    public int getId() {
        return mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public RepostCounterViewModel getReposts() {
        return mreposts;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setLikes(LikeCounterViewModel mLikes) {
        this.mLikes = mLikes;
    }

    public void setComments(CommentCounterViewModel mComments) {
        this.mComments = mComments;
    }

    public void setMreposts(RepostCounterViewModel mreposts) {
        this.mreposts = mreposts;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }

}
