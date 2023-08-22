package com.example.profileem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.profileem.R;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "KakaoLogin";

    private Button kakaoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_login);

        kakaoBtn = findViewById(R.id.kakaoBtn);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                Log.e(TAG, "CallBack Method");
                if (oAuthToken != null) {
                    Log.i(TAG, "Token: " + oAuthToken.getAccessToken());
                    updateKakaoLogin();
                } else {
                    Log.e(TAG, "Login failed");
                }
                return null;
            }
        };

        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().loginWithKakaoAccount(SplashActivity.this, callback);
            }
        });
    }

    private void updateKakaoLogin() {

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {
                    Log.d(TAG, "id = " + user.getId());
                    Log.d(TAG, "name = " + user.getKakaoAccount().getProfile().getNickname());

                    // 로그인이 되면 메인화면으로 이동
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                } else {

                }
                return null;
            }
        });
    }
}
