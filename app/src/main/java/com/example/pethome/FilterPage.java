package com.example.pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import com.google.firebase.firestore.FirebaseFirestore;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

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
    private Button selectedButtonSet0;
    private Button selectedButtonSet1;
    private Button selectedButtonSet2;
    private Button selectedButtonSet3;
    private Button selectedButtonSet4;

    private AppCompatButton reset;
    private TextView seekBar_text_month;
    private TextView seekBar_text_year;
    private SeekBar seekBar;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayAdapter<Pet> arrayAdapter;
    List<Pet> data;
    SwipeFlingAdapterView flingAdapterView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_page);

        seekBar_text_month = findViewById(R.id.seekbar_text_month);
        seekBar_text_year = findViewById(R.id.seekbar_text_year);
        seekBar = findViewById(R.id.seekbar);
        reset = findViewById(R.id.btn_reset);

        selectedButtonSet0 = null;
        selectedButtonSet1 = null;
        selectedButtonSet2 = null;
        selectedButtonSet3 = null;
        selectedButtonSet4 = null;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedButtonSet0.setBackgroundColor(getResources().getColor(R.color.border_color));
                selectedButtonSet1.setBackgroundColor(getResources().getColor(R.color.border_color));
                selectedButtonSet2.setBackgroundColor(getResources().getColor(R.color.border_color));
                selectedButtonSet3.setBackgroundColor(getResources().getColor(R.color.border_color));
                selectedButtonSet4.setBackgroundColor(getResources().getColor(R.color.border_color));
                seekBar.setProgress(0);
            }
        });

        seekBar.setMax(20*12); //12 months and cap at 20 years old max.

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int years = progress / 12; // it will return years.
                int months = (progress % 12); // here will be months.
                seekBar_text_month.setText(months+" months");
                seekBar_text_year.setText("from "+years+" year");
                    }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void onButtonClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.btn0_1 ||
                clickedButton.getId() == R.id.btn0_2) {

            // Check if a button is already selected in Set 1
            if (selectedButtonSet0 != null) {
                selectedButtonSet0.setSelected(false);
                selectedButtonSet0.setBackgroundColor(getResources().getColor(R.color.border_color));
            }

            // Set the clicked button as selected in Set 1
            clickedButton.setSelected(true);
            selectedButtonSet0 = clickedButton;
            clickedButton.setBackgroundColor(getResources().getColor(R.color.white));

        } else if (clickedButton.getId() == R.id.btn1_1 ||
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



    private void filterPets(String filterText, String selectedPetType, String selectedPetGender, int minAge, int maxAge) {
        // Access the "Pet" collection in Firestore
        CollectionReference petCollection = db.collection("Pet");

        // Construct a Firestore query based on filter criteria
        Query query = petCollection;

        // Filter by PetType (if selected)
        if (!selectedPetType.isEmpty()) {
            query = query.whereEqualTo("PetType", selectedPetType);
        }

        // Filter by PetGender (if selected)
        if (!selectedPetGender.isEmpty()) {
            query = query.whereEqualTo("PetGender", selectedPetGender);
        }

        // Filter by age range (if minAge and maxAge are specified)
        if (minAge > 0) {
            query = query.whereGreaterThanOrEqualTo("PetAge", minAge);
        }
        if (maxAge > 0) {
            query = query.whereLessThanOrEqualTo("PetAge", maxAge);
        }

        // Execute the query
        query.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Pet> filteredPets = new ArrayList<>();

                        // Loop through the documents and populate the filteredPets list
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Pet pet = document.toObject(Pet.class);
                            filteredPets.add(pet);
                        }

                        // Update the petList with the filtered results
                        // petList.clear();
                        // petList.addAll(filteredPets);

                        // Notify the ArrayAdapter that the data has changed
                        // adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error filtering pet data: " + e.getMessage());
                    }
                });
    }

 

}