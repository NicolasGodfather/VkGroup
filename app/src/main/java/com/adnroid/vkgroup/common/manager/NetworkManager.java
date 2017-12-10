package com.adnroid.vkgroup.common.manager;

import android.content.Context;

import com.adnroid.vkgroup.MyApplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.adnroid.vkgroup.common.utils.Utils.isOnline;

/**
 * Check for access Vk server
 */
public class NetworkManager {

    @Inject
    Context mContext;

    private static final String TAG = "NetworkManager";

    public NetworkManager() {
        MyApplication.getApplicationComponent().inject(this);
    }


    public Callable<Boolean> isVkReachableCallable() {
        return () -> {
            try {
                if (!isOnline(mContext)) {
                    return false;
                }

                URL url = new URL("https://api.vk.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(2000);
                urlc.connect();

                return true;
            } catch (Exception e) {
                return false;
            }
        };
    }

    public Observable<Boolean> getNetworkObservable() {
        return Observable.fromCallable(isVkReachableCallable());
    }

}
