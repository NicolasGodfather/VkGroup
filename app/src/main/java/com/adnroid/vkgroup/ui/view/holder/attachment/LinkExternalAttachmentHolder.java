package com.adnroid.vkgroup.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.view.attachment.LinkExternalViewModel;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkExternalAttachmentHolder extends BaseViewHolder<LinkExternalViewModel> {

    @BindView(R.id.iv_attachment_picture)
    public ImageView image;

    @BindView(R.id.tv_title)
    public TextView title;

    @BindView(R.id.tv_attachment_url)
    public TextView url;

    public LinkExternalAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(LinkExternalViewModel linkExternalViewModel) {
        itemView.setOnClickListener(view -> Utils.openUrlInActionView(linkExternalViewModel.getUrl(), view.getContext()));
        title.setText(linkExternalViewModel.getTitle());
        url.setText(linkExternalViewModel.getUrl());

        Glide.with(itemView.getContext()).load(linkExternalViewModel.getImageUrl()).into(image);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
        title.setText(null);

        image.setImageBitmap(null);
    }

}
