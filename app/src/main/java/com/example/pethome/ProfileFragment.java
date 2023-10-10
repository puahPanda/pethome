package com.example.pethome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.*;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    Button btn_make_appt;
    Button btnswitch2user;
    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btn_make_appt = view.findViewById(R.id.btn_make_appt);
        btnswitch2user = view.findViewById(R.id.Switch2U);


        btn_make_appt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAppt = new Intent(getContext(), make_appt_pet.class);
                startActivity(goToAppt);
            }
        });
        btnswitch2user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToUser = new Intent(getContext(),Vet_Fragment_Profile.class);
                startActivity(goToUser);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
