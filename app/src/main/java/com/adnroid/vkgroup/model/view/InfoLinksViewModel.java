package com.adnroid.vkgroup.model.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoLinksViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public InfoLinksViewHolder onCreateViewHolder(View view) {
        return new InfoLinksViewHolder(view);
    }

    static class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {
        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
