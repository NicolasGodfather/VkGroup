package com.adnroid.vkgroup.model.view;

import android.view.View;

import com.adnroid.vkgroup.model.WallItem;
import com.adnroid.vkgroup.ui.holder.NewsItemBodyHolder;

public class NewsItemBodyViewModel extends BaseViewModel {
    private int mId;

    private String mText;

    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mText = wallItem.getText();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    public NewsItemBodyHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }

    public String getText() {
        return mText;
    }

    public int getId() {
        return mId;
    }
}
