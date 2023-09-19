package com.example.pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.List;
import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;

import android.view.LayoutInflater;

public class SwipePage extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    List<String> data;
    SwipeFlingAdapterView flingAdapterView;
    ImageView like,dislike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_page);

        flingAdapterView=findViewById(R.id.swipe);

        LayoutInflater inflater = getLayoutInflater();
        View anotherLayout = inflater.inflate(R.layout.item, null);
        like=anotherLayout.findViewById(R.id.like);
        dislike=anotherLayout.findViewById(R.id.dislike);

        data=new ArrayList<>();
        data.add("Joshua");
        data.add("Mary");
        data.add("Elicia");
        data.add("David");

        arrayAdapter=new ArrayAdapter<>(SwipePage.this, R.layout.item, R.id.data, data);

        flingAdapterView.setAdapter(arrayAdapter);

        flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                data.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

                Toast.makeText(SwipePage.this,"dislike",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {

                Toast.makeText(SwipePage.this,"like",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });

        flingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int i, Object o) {
                Toast.makeText(SwipePage.this, "data is "+data.get(i),Toast.LENGTH_SHORT).show();
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingAdapterView.getTopCardListener().selectRight();
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingAdapterView.getTopCardListener().selectLeft();
            }
        });

    }
}