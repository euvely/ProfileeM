package com.example.profileem.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.profileem.R;
import com.example.profileem.fragment.CreateCardFragment1;

public class CreateCardActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private CreateCardFragment1 createCardFragment1 = new CreateCardFragment1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_card_common);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.createCard, createCardFragment1).commit();
    }
}
