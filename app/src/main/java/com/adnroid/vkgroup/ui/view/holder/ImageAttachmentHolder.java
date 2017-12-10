package com.adnroid.vkgroup.ui.view.holder;


import android.view.View;
import android.widget.ImageView;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.manager.MyFragmentManager;
import com.adnroid.vkgroup.model.view.attachment.ImageAttachmentViewModel;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.fragment.ImageFragment;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachmentViewModel> {

    @BindView(R.id.iv_attachment_image)
    public ImageView image;
    @Inject
    MyFragmentManager mFragmentManager;

    public ImageAttachmentHolder(View itemView) {
        super(itemView);
        MyApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(ImageAttachmentViewModel imageAttachmentViewModel) {
        if (imageAttachmentViewModel.needClick) {
            itemView.setOnClickListener(view -> mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                    ImageFragment.newInstance(imageAttachmentViewModel.getPhotoUrl()), R.id.main_wrapper));
        }
        Glide.with(itemView.getContext()).load(imageAttachmentViewModel.getPhotoUrl()).into(image);
    }

    @Override
    public void unbindViewHolder() {

        itemView.setOnClickListener(null);
        image.setImageBitmap(null);
    }

}