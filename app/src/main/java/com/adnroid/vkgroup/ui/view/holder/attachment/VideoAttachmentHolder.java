package com.adnroid.vkgroup.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adnroid.vkgroup.MyApplication;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.common.utils.Utils;
import com.adnroid.vkgroup.model.view.attachment.VideoAttachmentViewModel;
import com.adnroid.vkgroup.rest.api.VideoApi;
import com.adnroid.vkgroup.rest.model.request.VideoGetRequestModel;
import com.adnroid.vkgroup.ui.view.holder.BaseViewHolder;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VideoAttachmentHolder extends BaseViewHolder<VideoAttachmentViewModel> {

    @BindView(R.id.tv_attachment_video_title)
    public TextView title;

    @BindView(R.id.tv_attachment_video_duration)
    public TextView duration;

    @BindView(R.id.iv_attachment_video_picture)
    public ImageView image;

    @BindView(R.id.tv_views_count)
    public TextView views;

    @Inject
    VideoApi mVideoApi;

    public VideoAttachmentHolder(View itemView) {
        super(itemView);
        MyApplication.getApplicationComponent().inject(this);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(VideoAttachmentViewModel videoAttachmentViewModel) {

        itemView.setOnClickListener(view -> mVideoApi.get(new VideoGetRequestModel(videoAttachmentViewModel.getOwnerId(), videoAttachmentViewModel.getId()).toMap())
                .flatMap(videosResponseFull -> Observable.fromIterable(videosResponseFull.response.items))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newVideo -> {
                    String url = newVideo.getFiles() == null ? newVideo.getPlayer() : newVideo.getFiles().getExternal();

                    Utils.openUrlInActionView(url, view.getContext());
                }));

        title.setText(videoAttachmentViewModel.getTitle());
        views.setText(videoAttachmentViewModel.getViewCount());
        duration.setText(videoAttachmentViewModel.getDuration());

        Glide.with(itemView.getContext()).load(videoAttachmentViewModel.getImageUrl()).into(image);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);

        title.setText(null);
        views.setText(null);
        duration.setText(null);

        image.setImageBitmap(null);
    }


}