package com.adnroid.vkgroup.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.ui.holder.BaseViewHolder;

public abstract class BaseViewModel {

    public abstract LayoutTypes getType();

    public BaseViewHolder createViewHolder(ViewGroup parent) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getType().getValue(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public boolean isItemDecorator() {
        return false;
    }

    public enum LayoutTypes {
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer),
        Member(R.layout.item_member),
        Topic(R.layout.item_topic),
        InfoStatus(R.layout.item_info_status),
        InfoContacts(R.layout.item_info_contacts),
        InfoLinks(R.layout.item_info_links);

        private final int id;

        LayoutTypes(int resId) {
            this.id = resId;
        }

        @LayoutRes
        public int getValue() {
            return id;
        }
    }
}
