package com.example.pethome;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button backToHomeButton = findViewById(R.id.backButton);
        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the home page (MainActivity)
                Intent homeIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        // Initially, disable the "Register" button
        registerButton.setEnabled(false);

        // Add TextWatchers to monitor changes in the input fields
        usernameEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No action needed before text changes
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No action needed as text changes
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Check if all fields have text, then enable the "Register" button
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            boolean allFieldsFilled = !username.isEmpty() && !password.isEmpty();

            registerButton.setEnabled(allFieldsFilled);
        }
    };
}