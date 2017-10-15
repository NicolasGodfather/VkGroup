package com.adnroid.vkgroup.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.adnroid.vkgroup.App;
import com.adnroid.vkgroup.Const;
import com.adnroid.vkgroup.CurrentUser;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.mvp.presenter.MainPresenter;
import com.adnroid.vkgroup.mvp.view.MainView;
import com.adnroid.vkgroup.ui.fragment.NewsFeedFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter // for manage life cycle of presenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getApplicationComponent().inject(this);

        presenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                presenter.checkAuth();
            }

            @Override
            public void onError(VKError error) {
            // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSignIn() {
        VKSdk.login(this, Const.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedId() {
        Toast.makeText(this, "Current user id: " + CurrentUser.getId(), Toast.LENGTH_SHORT).show();

        setContent(new NewsFeedFragment());
    }
}
