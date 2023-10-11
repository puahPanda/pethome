package com.example.pethome;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class make_appt_pet extends Activity {
    private Button btnApptSet;
    //private Button btnConfirmAppt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appt_pet);

        btnApptSet = null;
        //btnConfirmAppt = findViewById(R.id.confirmTimeAppt);
    }

    public void onButtonClick(View view) {
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.btn_appt_1 ||
                clickedButton.getId() == R.id.btn_appt_2 ||
                clickedButton.getId() == R.id.btn_appt_3 ||
                clickedButton.getId() == R.id.btn_appt_4 ||
                clickedButton.getId() == R.id.btn_appt_5 ||
                clickedButton.getId() == R.id.btn_appt_6 ||
                clickedButton.getId() == R.id.btn_appt_7 ||
                clickedButton.getId() == R.id.btn_appt_8 ||
                clickedButton.getId() == R.id.btn_appt_9 ||
                clickedButton.getId() == R.id.btn_appt_10 ||
                clickedButton.getId() == R.id.btn_appt_11 ||
                clickedButton.getId() == R.id.btn_appt_12) {

            // Check if a button is already selected in Set 1
            if (btnApptSet != null) {
                btnApptSet.setSelected(false);
                btnApptSet.setBackgroundColor(getResources().getColor(R.color.border_color));
            }

            // Set the clicked button as selected in Set 1
            clickedButton.setSelected(true);
            btnApptSet = clickedButton;
            clickedButton.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
}