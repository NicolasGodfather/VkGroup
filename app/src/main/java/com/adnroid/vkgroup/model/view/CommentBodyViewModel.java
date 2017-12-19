package com.adnroid.vkgroup.model.view;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.manager.MyFragmentManager;
import com.adnroid.vkgroup.common.utils.UiHelper;
import com.adnroid.vkgroup.model.CommentItem;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.fragment.OpenedCommentFragment;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentBodyViewModel extends BaseViewModel {

    private int mId;
    private String mText;
    private String mAttachmentsString;

    public CommentBodyViewModel(CommentItem commentItem) {
        this.mId = commentItem.getId();
        this.mText = commentItem.getDisplayText();
        this.mAttachmentsString = commentItem.getDisplayAttachmentsString();
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CommentBody;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentBodyViewHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public String getAttachmentsString() {
        return mAttachmentsString;
    }

    public static class CommentBodyViewHolder extends BaseViewHolder<CommentBodyViewModel> {

        @Inject
        Typeface mGoogleFont;

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.tv_text)
        public TextView tvText;

        @BindView(R.id.tv_attachments)
        public TextView tvAttachments;

        public CommentBodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
            tvAttachments.setTypeface(mGoogleFont);
        }

        @Override
        public void bindViewHolder(CommentBodyViewModel commentBodyViewModel) {
            UiHelper.getInstance().setUpTextViewWithMessage(tvText, commentBodyViewModel.getText(), "");
            UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, commentBodyViewModel.getAttachmentsString());
            itemView.setOnClickListener(view -> mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                    OpenedCommentFragment.newInstance(commentBodyViewModel.getId()), R.id.main_wrapper));
        }

        @Override
        public void unbindViewHolder() {
            tvText.setText(null);
            tvAttachments.setText(null);
        }
    }
}
