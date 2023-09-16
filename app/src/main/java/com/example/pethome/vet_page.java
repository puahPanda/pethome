package com.example.pethome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class vet_page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_page);

        Button btn_makeappt = findViewById(R.id.btn_make_appt);

        btn_makeappt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the ErrorPageActivity when Google login button is clicked
                Intent errorPageIntent = new Intent(vet_page.this, ApptVetServiceLocation.class);
                startActivity(errorPageIntent);
            }
        });
    }
}
