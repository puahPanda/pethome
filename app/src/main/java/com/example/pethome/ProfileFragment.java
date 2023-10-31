package com.example.pethome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.*;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    Button btn_make_appt;
    Button editButton;
    ImageButton btnAdd;
    Button btnswitch2user;
    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
//        btn_make_appt = view.findViewById(R.id.btn_make_appt);
        //*editButton = view.findViewById(R.id.edit_profile);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditProfileActivity.class);
                startActivity(i);
            }
        });
        //btnAdd = view.findViewById(R.id.addBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddPet.class);
                startActivity(i);
            }
        });
        //*btnswitch2user = view.findViewById(R.id.Switch2U);


//        });
//        btnswitch2user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToUser = new Intent(getContext(),VetFragmentProfile.class);
//                startActivity(goToUser);
//            }
//        });
        // Inflate the layout for this fragment
        return view;
    }
}
