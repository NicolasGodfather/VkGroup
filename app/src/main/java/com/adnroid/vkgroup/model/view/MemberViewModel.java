package com.adnroid.vkgroup.model.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.model.Member;
import com.adnroid.vkgroup.ui.holder.BaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberViewModel extends BaseViewModel {

    public int id;

    public int groupId;

    public String photo;

    private String mFullName;

    public MemberViewModel() {

    }

    public MemberViewModel(Member member) {
        this.id = member.getId();
        this.groupId = member.getGroupId();

        this.photo = member.getPhoto();
        this.mFullName = member.getFullName();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Member;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new MemberViewHolder(view);
    }


    static class MemberViewHolder extends BaseViewHolder<MemberViewModel> {

        @BindView(R.id.civ_profile_image)
        public CircleImageView civProfilePhoto;

        @BindView(R.id.tv_profile_name)
        public TextView civProfileName;


        public MemberViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(MemberViewModel memberViewModel) {
            Context context = itemView.getContext();

            Glide.with(context)
                    .load(memberViewModel.getPhoto())
                    .into(civProfilePhoto);
            civProfileName.setText(memberViewModel.getFullName());
        }



        @Override
        public void unbindViewHolder() {
            civProfileName.setText(null);
            civProfilePhoto.setImageBitmap(null);
        }
    }


    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFullName() {
        return mFullName;
    }

}
