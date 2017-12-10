package com.adnroid.vkgroup.model.view;

import android.view.View;
import android.widget.TextView;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.model.Topic;
import com.adnroid.vkgroup.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewModel extends BaseViewModel {
    private int mId;
    private int mGroupId;
    private String mTitle;
    private String mCommentsCount;

    public TopicViewModel() {
    }

    public TopicViewModel(Topic topic) {
        this.mId = topic.getId();
        this.mGroupId = topic.getGroupId();

        this.mTitle = topic.getTitle();

        this.mCommentsCount = topic.getComments() + " сообщений";
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    public int getId() {
        return mId;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }


    public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

        @BindView(R.id.tv_title)
        public TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        public TextView tvCommentsCount;


        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(TopicViewModel topicViewModel) {
            tvTitle.setText(topicViewModel.getTitle());
            tvCommentsCount.setText(topicViewModel.getCommentsCount());
        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvCommentsCount.setText(null);
        }
    }
}
