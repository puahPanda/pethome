package com.example.pethome;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.*;

public class BaseApplication extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_application);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
    LikesFragment likesFragment = new LikesFragment();
    ChatFragment chatFragment = new ChatFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    VetFragment vetFragment = new VetFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()) {
            case R.id.likes:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, likesFragment)
                        .commit();
                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, vetFragment)
                        .commit();
                return true;

            case R.id.chat:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, chatFragment)
                        .commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, profileFragment)
                        .commit();
                return true;
        }
        return false;
    }
}
