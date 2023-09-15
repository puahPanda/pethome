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

        EditText numberEditText = findViewById(R.id.numberEditText);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        // Add text change listeners to the required EditText fields
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkFieldsAndEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        numberEditText.addTextChangedListener(textWatcher);
        usernameEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);
    }
    private void checkFieldsAndEnableButton() {
        EditText numberEditText = findViewById(R.id.numberEditText);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        boolean allFieldsFilled = true;

        // Check if all required fields are filled
        for (EditText editText : new EditText[]{numberEditText, usernameEditText, passwordEditText}) {
            if (editText.getTag() != null && editText.getTag().equals("required")) {
                if (editText.getText().toString().trim().isEmpty()) {
                    allFieldsFilled = false;
                    break;
                }
            }
        }

        // Enable or disable the button based on the state
        if (allFieldsFilled) {
            registerButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.holo_green_light));
            registerButton.setClickable(true);
        } else {
            registerButton.setBackgroundTintList(getResources().getColorStateList(android.R.color.transparent));
            registerButton.setClickable(false);
        }
    }
}