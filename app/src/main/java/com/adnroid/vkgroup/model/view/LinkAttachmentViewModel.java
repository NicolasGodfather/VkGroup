package com.adnroid.vkgroup.model.view;

import android.view.View;

import com.adnroid.vkgroup.model.attachmaent.Link;
import com.adnroid.vkgroup.ui.view.holder.attachment.LinkAttachmentViewHolder;

public class LinkAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public LinkAttachmentViewModel(Link link) {
        if (link.getTitle() == null || link.getTitle().equals("")) {
            if (link.getName() != null) {
                mTitle = link.getName();
            } else {
                mTitle = "Link";
            }
        } else {
            mTitle = link.getTitle();
        }
        mUrl = link.getUrl();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLink;
    }

    @Override
    public LinkAttachmentViewHolder onCreateViewHolder(View view) {
        return new LinkAttachmentViewHolder(view);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
