package com.adnroid.vkgroup.mvp.view;

import com.adnroid.vkgroup.model.WallItem;
import com.adnroid.vkgroup.model.counter.LikeCounterViewModel;

public interface PostFooterView {

    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);

}
