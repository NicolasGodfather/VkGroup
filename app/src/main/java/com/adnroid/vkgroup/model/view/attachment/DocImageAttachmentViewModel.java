package com.adnroid.vkgroup.model.view.attachment;

import android.view.View;

import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.attachmaent.doc.Doc;
import com.adnroid.vkgroup.model.attachmaent.doc.Size;
import com.adnroid.vkgroup.model.view.BaseViewModel;
import com.adnroid.vkgroup.ui.view.holder.attachment.DocImageAttachmentHolder;

import java.util.List;

public class DocImageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mSize;
    private String mExt;
    private String mImage;
    private String mUrl;

    public DocImageAttachmentViewModel(Doc doc) {
        if (doc.getTitle().equals("")) {
            mTitle = "Document";
        } else {
            mTitle = Utils.removeExtFromText(doc.getTitle());
        }

        mUrl = doc.getUrl();

        mSize = Utils.formatSize(doc.getSize());

        mExt = "." + doc.getExt();

        List<Size> sizes = doc.getPreview().getPhoto().getSizes();
        mImage = sizes.get(sizes.size() - 1).getSrc();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDocImage;
    }

    @Override
    protected DocImageAttachmentHolder onCreateViewHolder(View view) {
        return new DocImageAttachmentHolder(view);
    }


    public String getmUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSize() {
        return mSize;
    }

    public String getExt() {
        return mExt;
    }

    public String getImage() {
        return mImage;
    }

}