package com.adnroid.vkgroup.di.component;

import com.adnroid.vkgroup.common.manager.NetworkManager;
import com.adnroid.vkgroup.di.module.ApplicationModule;
import com.adnroid.vkgroup.di.module.ManagerModule;
import com.adnroid.vkgroup.di.module.RestModule;
import com.adnroid.vkgroup.model.view.CommentBodyViewModel;
import com.adnroid.vkgroup.model.view.CommentFooterViewModel;
import com.adnroid.vkgroup.model.view.TopicViewModel;
import com.adnroid.vkgroup.mvp.presenter.BoardPresenter;
import com.adnroid.vkgroup.mvp.presenter.CommentsPresenter;
import com.adnroid.vkgroup.mvp.presenter.InfoPresenter;
import com.adnroid.vkgroup.mvp.presenter.MainPresenter;
import com.adnroid.vkgroup.mvp.presenter.MembersPresenter;
import com.adnroid.vkgroup.mvp.presenter.NewsFeedPresenter;
import com.adnroid.vkgroup.mvp.presenter.OpenedCommentPresenter;
import com.adnroid.vkgroup.mvp.presenter.OpenedPostPresenter;
import com.adnroid.vkgroup.mvp.presenter.TopicCommentsPresenter;
import com.adnroid.vkgroup.ui.activity.BaseActivity;
import com.adnroid.vkgroup.ui.activity.MainActivity;
import com.adnroid.vkgroup.ui.fragment.CommentsFragment;
import com.adnroid.vkgroup.ui.fragment.NewsFeedFragment;
import com.adnroid.vkgroup.ui.fragment.OpenedCommentFragment;
import com.adnroid.vkgroup.ui.fragment.OpenedPostFragment;
import com.adnroid.vkgroup.ui.fragment.TopicCommentsFragment;
import com.adnroid.vkgroup.ui.view.holder.ImageAttachmentHolder;
import com.adnroid.vkgroup.ui.view.holder.NewsItemBodyHolder;
import com.adnroid.vkgroup.ui.view.holder.NewsItemFooterHolder;
import com.adnroid.vkgroup.ui.view.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})// bind part that request dependencies
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment newsFeedFragment);
    void inject(OpenedPostFragment newsFeedFragment);
    void inject(CommentsFragment newsFeedFragment);
    void inject(OpenedCommentFragment newsFeedFragment);
    void inject(TopicCommentsFragment newsFeedFragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewModel.TopicViewHolder holder);

    //presenter
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsPresenter presenter);

    //managers
    void inject(NetworkManager manager);

}
