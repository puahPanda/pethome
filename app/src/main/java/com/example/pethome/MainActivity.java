package com.example.pethome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button googleLoginButton = findViewById(R.id.googleLoginButton);
        Button registerButton = findViewById(R.id.registerButton);

        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the ErrorPageActivity when Google login button is clicked
                Intent errorPageIntent = new Intent(MainActivity.this, ErrorPageActivity.class);
                startActivity(errorPageIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the RegistrationActivity when Register button is clicked
                Intent registrationIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });
    }
}