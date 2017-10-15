package com.adnroid.vkgroup.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.adnroid.vkgroup.CurentUser;
import com.adnroid.vkgroup.R;
import com.adnroid.vkgroup.mvp.presenter.MainPresenter;
import com.adnroid.vkgroup.mvp.view.MainView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter // for manage life cycle of presenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.checkAuth();

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
        VKSdk.login(this);

    }

    @Override
    public void signedId() {
        Toast.makeText(this, "Current user id: " + CurentUser.getId(), Toast.LENGTH_SHORT).show();
    }
}
