package com.example.pethome;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pethome.R;
import com.example.pethome.make_appt_pet;

public class MyProfileNewFragment extends Fragment {
    Button btnEditProfile;

    public MyProfileNewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_my_profile_new, container, false);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddPetNew.class);
                startActivity(i);
            }
        });

        return view;
    }
}