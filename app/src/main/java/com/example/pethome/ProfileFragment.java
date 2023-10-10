package com.example.pethome;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.*;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    Button btn_make_appt;
    Button btnswitch2user;
    Button edit_profile;
    public ProfileFragment(){
        // require a empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btnswitch2user = view.findViewById(R.id.Switch2U);
        edit_profile = view.findViewById(R.id.edit_profile);



        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToEditProfile = new Intent(getContext(), EditProfileActivity.class);
                startActivity(goToEditProfile);
            }
        });

        btnswitch2user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToUser = new Intent(getContext(),VetFragmentProfile.class);
                startActivity(goToUser);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
