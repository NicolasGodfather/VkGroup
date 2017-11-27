package com.adnroid.vkgroup.ui.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.counter.CommentCounterViewModel;
import com.adnroid.vkgroup.model.counter.LikeCounterViewModel;
import com.adnroid.vkgroup.model.counter.RepostCounterViewModel;
import com.adnroid.vkgroup.model.view.NewsItemFooterViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel>{

    @BindView(R.id.tv_date)
    public TextView tvDate;
    @BindView(R.id.tv_likes_count)
    public TextView tvLikesCount;
    @BindView(R.id.tv_likes_icon)
    public TextView tvLikesIcon;
    @BindView(R.id.tv_comments_icon)
    public TextView tvCommentIcon;
    @BindView(R.id.tv_comments_count)
    public TextView tvCommentsCount;
    @BindView(R.id.tv_reposts_icon)
    public TextView tvRepostIcon;
    @BindView(R.id.tv_reposts_count)
    public TextView tvRepostsCount;

    @Inject
    Typeface mGoogleFontTypeface;

    private Resources mResources;
    private Context mContext;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        App.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();


        tvLikesIcon.setTypeface(mGoogleFontTypeface);
        tvCommentIcon.setTypeface(mGoogleFontTypeface);
        tvRepostIcon.setTypeface(mGoogleFontTypeface);
    }

    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

        bindLikes(item.getLikes());
        bindComments(item.getComments());
        bindReposts(item.getReposts());

    }

    private void bindLikes(LikeCounterViewModel likes) {
        tvLikesCount.setText(String.valueOf(likes.getCount()));
        tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
        tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));

    }
    private void bindComments(CommentCounterViewModel comments) {
        tvCommentsCount.setText(String.valueOf(comments.getCount()));
        tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
        tvCommentIcon.setTextColor(mResources.getColor(comments.getIconColor()));

    }
    private void bindReposts(RepostCounterViewModel reposts) {
        tvRepostsCount.setText(String.valueOf(reposts.getCount()));
        tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
        tvRepostIcon.setTextColor(mResources.getColor(reposts.getIconColor()));
    }

    @Override
    public void unbindViewHolder() {
        tvDate.setText(null);
        tvLikesCount.setText(null);
        tvCommentsCount.setText(null);
        tvRepostsCount.setText(null);
    }
}
