package com.example.profileem.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.profileem.R;
import com.example.profileem.fragment.MainGroupFragment;
import com.example.profileem.fragment.MainMyProfileFragment;
import com.example.profileem.fragment.MainProfileWalletFragment;
import com.example.profileem.fragment.MainSettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMyProfileFragment mainMyProfileFragment = new MainMyProfileFragment();
    private MainProfileWalletFragment mainProfileWalletFragment = new MainProfileWalletFragment();
    private MainGroupFragment mainGroupFragment = new MainGroupFragment();
    private MainSettingFragment mainSettingFragment = new MainSettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.containers, mainMyProfileFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_menu_navigation);

        Map<Integer, Fragment> fragments = new HashMap<>();
        fragments.put(R.id.myProfile, mainMyProfileFragment);
        fragments.put(R.id.profileWallet, mainProfileWalletFragment);
        fragments.put(R.id.group, mainGroupFragment);
        fragments.put(R.id.setting, mainSettingFragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = fragments.get(item.getItemId());

                if(fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, fragment)
                            .commit();

                    return true;
                }
                return false;
            }
        });
    }
}