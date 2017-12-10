package com.adnroid.vkgroup.ui.view.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.view.attachment.PageAttachmentViewModel;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageAttachmentHolder extends BaseViewHolder<PageAttachmentViewModel> {
    @BindView(R.id.tv_title)
    public TextView title;

    public PageAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(PageAttachmentViewModel pageAttachmentViewModel) {
        itemView.setOnClickListener(view -> Utils.openUrlInActionView(pageAttachmentViewModel.getmUrl(), view.getContext()));
        title.setText(pageAttachmentViewModel.getTitle());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
    }


}