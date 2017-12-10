package com.adnroid.vkgroup.ui.view.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.manager.MyFragmentManager;
import com.adnroid.vkgroup.common.utils.UiHelper;
import com.adnroid.vkgroup.model.view.NewsItemBodyViewModel;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    @BindView(R.id.tv_text)
    public TextView tvText;
    @BindView(R.id.tv_attachments)
    public TextView tvAttachments;

    @Inject
    protected Typeface mFontGoogle;
    @Inject
    MyFragmentManager myFragmentManager;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        MyApplication.getApplicationComponent().inject(this);

        tvText = (TextView) itemView.findViewById(R.id.tv_text);
        tvAttachments = (TextView) itemView.findViewById(R.id.tv_attachments);

        if (tvAttachments != null) {
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        tvText.setText(item.getText());
        tvAttachments.setText(item.getmAttachmentString());

        itemView.setOnClickListener(view -> myFragmentManager.addFragment((BaseActivity) view.getContext(),
                OpenedPostFragment.newInstance(item.getId()),
                R.id.main_wrapper));
        UiHelper.getInstance().setUpTextViewWithVisibility(tvText, item.getText());
        UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getmAttachmentString());
    }

    @Override
    public void unbindViewHolder() {
        tvText.setText(null);
        tvAttachments.setText(null);
        itemView.setOnClickListener(null);
    }
}
