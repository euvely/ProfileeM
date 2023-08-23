package com.example.profileem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.profileem.R;
import com.kakao.sdk.user.UserApiClient;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "KakaoLogin";

    private Button kakaoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_login);

        kakaoBtn = findViewById(R.id.kakaoBtn);

        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(SplashActivity.this)) { // 카톡 어플이 있을 경우
                    login();
                }
                else { // 카톡 어플이 없을 경우 (url로 연결되서 직접 계정 작성해야 함)
                    accountLogin();
                }
            }
        });
    }

    public void login() {
        UserApiClient.getInstance().loginWithKakaoTalk(SplashActivity.this,
                (OAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "로그인 실패");
            } else if (OAuthToken != null) {
                Log.i(TAG, "로그인 성공, 토큰: " + OAuthToken.getAccessToken());
                getUserInfo();

                // 로그인 성공 시 메인화면으로 이동
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
            return null;
        });
    }

    public void accountLogin() {
        UserApiClient.getInstance().loginWithKakaoAccount(SplashActivity.this,
                (OAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "로그인 실패");
            } else if (OAuthToken != null) {
                Log.i(TAG, "로그인 성공, 토큰: " + OAuthToken.getAccessToken());
                getUserInfo();

                // 로그인 성공 시 메인화면으로 이동
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
            return null;
        });
    }

    // 사용자 정보 받아오기
    public void getUserInfo() {
        UserApiClient.getInstance().me((user, error) -> {
            if (error != null) {
                Log.e(TAG, "사용자 정보 받아오기 실패");
            } else {
                Log.i(TAG, "사용자 정보 id: " + user.getId());
                Log.i(TAG, "사용자 정보 name: " + user.getKakaoAccount().getProfile().getNickname());

                System.out.println("사용자 계정" + user.getKakaoAccount());
            }
            return null;
        });
    }
}
