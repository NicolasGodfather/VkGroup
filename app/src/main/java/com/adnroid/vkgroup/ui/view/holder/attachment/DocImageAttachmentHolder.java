package com.adnroid.vkgroup.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.view.attachment.DocImageAttachmentViewModel;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocImageAttachmentHolder extends BaseViewHolder<DocImageAttachmentViewModel> {

    @BindView(R.id.tv_attachment_title)
    public TextView title;

    @BindView(R.id.tv_attachment_ext)
    public TextView ext;

    @BindView(R.id.tv_attachment_size)
    public TextView size;

    @BindView(R.id.iv_attachment_image)
    public ImageView image;

    public DocImageAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(DocImageAttachmentViewModel docImageAttachmentViewModel) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInActionView(docImageAttachmentViewModel.getmUrl(), view.getContext());
            }
        });

        title.setText(docImageAttachmentViewModel.getTitle());

        size.setText(docImageAttachmentViewModel.getSize());

        ext.setText(docImageAttachmentViewModel.getExt());

        Glide.with(itemView.getContext()).load(docImageAttachmentViewModel.getImage()).into(image);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
        size.setText(null);
        ext.setText(null);
        image.setImageBitmap(null);
    }

}
