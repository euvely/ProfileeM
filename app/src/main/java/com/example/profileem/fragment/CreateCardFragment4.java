package com.example.profileem.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.profileem.R;

public class CreateCardFragment4 extends Fragment {

    Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.create_card_4, container, false);

        nextButton = rootView.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //CreateCardFragment3 createCardFragment3 = new CreateCardFragment3();
                //transaction.replace(R.id.createCard, createCardFragment3);
                //transaction.commit();
            }
        });

        return rootView;
    }
}
