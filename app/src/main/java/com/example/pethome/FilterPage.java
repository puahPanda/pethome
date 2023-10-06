package com.example.pethome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.Gson;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import com.google.firebase.firestore.FirebaseFirestore;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import androidx.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.ArrayList;
import android.util.Log;

public class FilterPage extends AppCompatActivity {
    private Button selectedButtonSet1;
    private Button selectedButtonSet2;
    private Button selectedButtonSet3;
    private Button selectedButtonSet4;
    private TextView seekBar_text;
    private SeekBar seekBar;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_page);

        seekBar_text = findViewById(R.id.seekbar_text);
        seekBar = findViewById(R.id.seekbar);

        selectedButtonSet1 = null;
        selectedButtonSet2 = null;
        selectedButtonSet3 = null;
        selectedButtonSet4 = null;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar_text.setText(i + " months");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    public void onButtonClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.btn1_1 ||
                clickedButton.getId() == R.id.btn1_2 ||
                clickedButton.getId() == R.id.btn1_3) {

            // Check if a button is already selected in Set 1
            if (selectedButtonSet1 != null) {
                selectedButtonSet1.setSelected(false);
                selectedButtonSet1.setBackgroundColor(getResources().getColor(R.color.border_color));
            }

            // Set the clicked button as selected in Set 1
            clickedButton.setSelected(true);
            selectedButtonSet1 = clickedButton;
            clickedButton.setBackgroundColor(getResources().getColor(R.color.white));

        } else if (clickedButton.getId() == R.id.btn2_1 ||
                clickedButton.getId() == R.id.btn2_2 ||
                clickedButton.getId() == R.id.btn2_3) {

            // Check if a button is already selected in Set 2
            if (selectedButtonSet2 != null) {
                selectedButtonSet2.setSelected(false);
                selectedButtonSet2.setBackgroundColor(getResources().getColor(R.color.border_color));
            }

            // Set the clicked button as selected in Set 2
            clickedButton.setSelected(true);
            selectedButtonSet2 = clickedButton;
            clickedButton.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (clickedButton.getId() == R.id.btn3_1 ||
                clickedButton.getId() == R.id.btn3_2) {

            // Check if a button is already selected in Set 2
            if (selectedButtonSet3 != null) {
                selectedButtonSet3.setSelected(false);
                selectedButtonSet3.setBackgroundColor(getResources().getColor(R.color.border_color));
            }

            // Set the clicked button as selected in Set 2
            clickedButton.setSelected(true);
            selectedButtonSet3 = clickedButton;
            clickedButton.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (clickedButton.getId() == R.id.btn4_1 ||
                clickedButton.getId() == R.id.btn4_2) {

            // Check if a button is already selected in Set 2
            if (selectedButtonSet4 != null) {
                selectedButtonSet4.setSelected(false);
                selectedButtonSet4.setBackgroundColor(getResources().getColor(R.color.border_color));
            }

            // Set the clicked button as selected in Set 2
            clickedButton.setSelected(true);
            selectedButtonSet4 = clickedButton;
            clickedButton.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }




    public void onSubmitButtonClick(View view) {
        // Add your code here to handle the button click event
        // For example, you can show a toast message:
        Toast.makeText(this, "Submit button clicked", Toast.LENGTH_SHORT).show();

        Bundle filterArgs = new Bundle();
        //filterArgs.putString("breed", selectedButtonSet1.getText().toString());

        filterArgs.putString("gender", selectedButtonSet2.getText().toString());


        Intent intent = new Intent(this, BaseApplication.class);

// Pass the SwipeFragment instance as an extra to the intent
        intent.putExtra("showSwipeFragment", true); // To indicate that you want to show the SwipeFragment
        intent.putExtra("filterArgs", filterArgs); // Pass the Bundle containing filter arguments
        startActivity(intent);
        finish();


// Navigate to the SwipeFragment

    }

    public void redirectToBaseApplication() {
        Intent intent = new Intent(this, BaseApplication.class);
        intent.putExtra("showSwipeFragment", true);
        startActivity(intent);
        finish();

    }





}