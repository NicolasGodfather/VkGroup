package com.adnroid.vkgroup.model.view;

import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.model.CommentItem;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentHeaderViewModel extends BaseViewModel {

    private int mId;
    private String mProfilePhoto;
    private String mProfileName;

    public CommentHeaderViewModel(CommentItem commentItem) {
        this.mId = commentItem.getId();
        this.mProfilePhoto = commentItem.getSenderPhoto();
        this.mProfileName = commentItem.getSenderName();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CommentHeader;
    }

    public int getId() {
        return mId;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getProfileName() {
        return mProfileName;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentHeaderHolder(view);
    }

    static class CommentHeaderHolder extends BaseViewHolder<CommentHeaderViewModel> {

        @BindView(R.id.civ_profile_image)
        public CircleImageView civProfileImage;

        @BindView(R.id.tv_profile_name)
        public TextView tvName;

        public CommentHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(CommentHeaderViewModel commentHeaderViewModel) {
            Glide.with(itemView.getContext()).load(commentHeaderViewModel.getProfilePhoto()).into(civProfileImage);

            tvName.setText(commentHeaderViewModel.getProfileName());
        }

        @Override
        public void unbindViewHolder() {
            civProfileImage.setImageBitmap(null);
            tvName.setText(null);
        }
    }

}