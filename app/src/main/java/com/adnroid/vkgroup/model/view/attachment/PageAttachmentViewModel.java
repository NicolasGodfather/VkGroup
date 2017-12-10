package com.adnroid.vkgroup.model.view.attachment;

import android.view.View;

import com.adnroid.vkgroup.model.attachmaent.Page;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.ui.view.holder.attachment.PageAttachmentHolder;

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    public PageAttachmentHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }

    public String getmUrl() {
        return mUrl;
    }
}
