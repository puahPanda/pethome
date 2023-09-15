package com.example.pethome;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        // Find the "Back to Home" button and set an OnClickListener
        Button backToHomeButton = findViewById(R.id.backButton);
        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the home page (MainActivity)
                Intent homeIntent = new Intent(ErrorPageActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        // Find the "Go to Registration" button and set an OnClickListener
        Button goToRegistrationButton = findViewById(R.id.actionButton);
        goToRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the registration page (RegistrationActivity)
                Intent registrationIntent = new Intent(ErrorPageActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });
    }
}
