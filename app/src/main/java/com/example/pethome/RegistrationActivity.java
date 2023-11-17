package com.example.pethome;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailET, passwordET;
    private Button registerButton;
    private TextView loginRedirectText;

    AppCompatButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        auth =FirebaseAuth.getInstance();
        EditText numberEditText = findViewById(R.id.numberEditText);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = emailET.getText().toString().trim();
                String pass = passwordET.getText().toString().trim();

                if (user.isEmpty()){
                    emailET.setError("Email cannot be empty");
                }
                if(pass.isEmpty()){
                    passwordET.setError("Password cannot be empty");
                } else{
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, registration_confirm.class));
                            } else{
                                Toast.makeText(RegistrationActivity.this, "Signup Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
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
        emailET.addTextChangedListener(textWatcher);
        passwordET.addTextChangedListener(textWatcher);
    }
    private void checkFieldsAndEnableButton() {
        EditText numberEditText = findViewById(R.id.numberEditText);
        EditText emailET = findViewById(R.id.emailET);
        EditText passwordET = findViewById(R.id.passwordET);
        Button registerButton = findViewById(R.id.registerButton);

        boolean allFieldsFilled = true;

        // Check if all required fields are filled
        for (EditText editText : new EditText[]{numberEditText, emailET, passwordET}) {
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