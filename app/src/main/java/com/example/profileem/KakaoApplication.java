package com.example.profileem;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    private static KakaoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "7a7644414dc6c15b436acd21225028bc");
    }
}
