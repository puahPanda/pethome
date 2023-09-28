package com.example.pethome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.*;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class VetFragment extends Fragment {

    AppCompatButton btnMakeAppt;
    public VetFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vet, container, false);
        btnMakeAppt = view.findViewById(R.id.btn_make_appt);
        btnMakeAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getContext(),  make_appt_pet.class);
                startActivity(s);
            }
        });

        return view;
    }
}
