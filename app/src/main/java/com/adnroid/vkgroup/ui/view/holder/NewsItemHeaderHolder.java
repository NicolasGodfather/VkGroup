package com.adnroid.vkgroup.ui.view.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.model.view.NewsItemHeaderViewModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeaderViewModel>{

    @BindView(R.id.civ_profile_image)
    public CircleImageView civProfileImage;
    @BindView(R.id.tv_profile_name)
    public TextView tvName;
    @BindView(R.id.iv_reposted_icon)
    public ImageView ivRepostedIcon;
    @BindView(R.id.tv_reposted_profile_name)
    public TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(NewsItemHeaderViewModel item) {
        Context context = itemView.getContext();

        Glide.with(context)
                .load(item.getProfilePhoto())
                .into(civProfileImage);
        tvName.setText(item.getProfileName());

        if (item.isRepost()) {
            ivRepostedIcon.setVisibility(View.VISIBLE);
            tvRepostedProfileName.setText(item.getRepostProfileName());
        } else {
            ivRepostedIcon.setVisibility(View.GONE);
            tvRepostedProfileName.setText(null);
        }
    }

    @Override
    public void unbindViewHolder() {
        civProfileImage.setImageBitmap(null);
        tvName.setText(null);
        tvRepostedProfileName.setText(null);

    }
}