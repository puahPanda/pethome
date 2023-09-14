package com.example.pethome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter likedPetsAdapter;
    RecyclerView.LayoutManager layoutManager;
    String [] names = {"Joshua", "Zhim", "Lino", "Rach", "Cherry", "Ban", "Lino", "Rach"};

    String [] age = {"6M", "1Y", "2M", "20D", "5Y", "1Y6M","2M", "20D"};
    int [] images = {R.drawable.cat1, R.drawable.duck1, R.drawable.gecko, R.drawable.babydog, R.drawable.gecko, R.drawable.cat1, R.drawable.duck1, R.drawable.gecko};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_likes, container, false);
        recyclerView = view.findViewById(R.id.likesRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        likedPetsAdapter = new LikedPetsAdapter(getContext(), names,age,images);
        recyclerView.setAdapter(likedPetsAdapter);
        return view;
    }
}