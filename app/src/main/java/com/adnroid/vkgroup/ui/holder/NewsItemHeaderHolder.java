package com.adnroid.vkgroup.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.model.view.NewsItemHeaderViewModel;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeaderViewModel>{

    private CircleImageView civProfileImage;
    private TextView tvName;
    private ImageView ivRepostedIcon;
    private TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);

        civProfileImage = (CircleImageView) itemView.findViewById(R.id.civ_profile_image);
        tvName = (TextView) itemView.findViewById(R.id.tv_profile_name);
        ivRepostedIcon = (ImageView) itemView.findViewById(R.id.iv_reposted_icon);
        tvRepostedProfileName = (TextView) itemView.findViewById(R.id.tv_reposted_profile_name);
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