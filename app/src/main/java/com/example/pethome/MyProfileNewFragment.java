package com.example.pethome;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pethome.R;
import com.example.pethome.make_appt_pet;

public class MyProfileNewFragment extends Fragment {
    AppCompatImageButton btnAddPet;

    public MyProfileNewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_my_profile_new, container, false);
        btnAddPet = view.findViewById(R.id.btn_AddPet);

        btnAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddPetNew.class);
                startActivity(i);
            }
        });

        return view;
    }
}