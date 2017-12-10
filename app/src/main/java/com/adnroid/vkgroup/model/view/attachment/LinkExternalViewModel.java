package com.adnroid.vkgroup.model.view.attachment;

import android.view.View;

import com.adnroid.vkgroup.model.attachmaent.Link;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.ui.view.holder.attachment.LinkExternalAttachmentHolder;

public class LinkExternalViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;
    private String mImageUrl;

    public LinkExternalViewModel(Link link) {

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

        mImageUrl = link.getPhoto().getPhoto604();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLinkExternal;
    }

    @Override
    protected LinkExternalAttachmentHolder onCreateViewHolder(View view) {
        return new LinkExternalAttachmentHolder(view);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
