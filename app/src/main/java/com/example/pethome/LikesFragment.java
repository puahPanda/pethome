package com.example.pethome;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikesFragment extends Fragment {
    FirebaseFirestore db;
    List<Pet>likesList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter likedPetsAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<String>names;
    List<Integer>age;
    int [] images = {R.drawable.cat1, R.drawable.duck1, R.drawable.gecko, R.drawable.babydog, R.drawable.gecko, R.drawable.cat1, R.drawable.duck1, R.drawable.gecko};

    private void findPet(String petID){
        Log.d("DBA", "onSuccess: Pet " + petID);
        DocumentReference petRef = db.collection("Pet").document(petID);
        petRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot2) {
                Pet pet = documentSnapshot2.toObject(Pet.class);
                likesList.add(pet);
                Log.d("DBA", "onSuccess: Pet added to list " + pet.getName() + " " + documentSnapshot2.getId());
            }
        });
    }

    private void readUser(String userID, View view) {
        DocumentReference docRef = db.collection("User").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                Log.d("DBA", "onSuccess: user likes" + user.getLikes());

                for (String petID : user.getLikes()) {
//                    findPet(petID);
                    Log.d("DBA", "onSuccess: Pet " + petID);
                    DocumentReference petRef = db.collection("Pet").document(petID);
                    petRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot2) {
                            Pet pet = documentSnapshot2.toObject(Pet.class);
                            likesList.add(pet);
                            names.add(pet.getName());
                            age.add(pet.getAge());
                            Log.d("DBA", "onSuccess: Pet added to list " + pet.getName() + " " + documentSnapshot2.getId());
                        }
                    }).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            showView(view);
                        }
                    });
                }
            }
        });
    }

    private void showView(View view){
        Log.d("DBA", "names: " + names);
        recyclerView = view.findViewById(R.id.likesRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        likedPetsAdapter = new LikedPetsAdapter(getContext(), names,age,images);
        recyclerView.setAdapter(likedPetsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_likes, container, false);

        likesList = new ArrayList<>();
        names=new ArrayList<>();
        age=new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        readUser("V55wAs8ZTFCo9C6Dzvnr",view);

        // Find the sort button by its ID
        Button sortAge = view.findViewById(R.id.SortAge);

        // Set an OnClickListener for the sort button
        sortAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort the liked pets by age
                Collections.sort(likesList, new Comparator<Pet>() {
                    @Override
                    public int compare(Pet pet1, Pet pet2) {
                        // Compare pets by age
                        return Integer.compare(pet1.getAge(), pet2.getAge());
                    }
                });

                // Update the RecyclerView with the sorted list
                likedPetsAdapter.notifyDataSetChanged();
            }
        });

        // Find the sort button by its ID
        Button sortLeastRecent = view.findViewById(R.id.sortLeastRecent);

        // Set an OnClickListener for the sort button
        sortLeastRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort the liked pets by least recent timestamp
                Collections.sort(likesList, new Comparator<Pet>() {
                    @Override
                    public int compare(Pet pet1, Pet pet2) {
                        // Compare pets by timestamp (least recent first)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date date1 = dateFormat.parse(pet1.getTimestamp());
                            Date date2 = dateFormat.parse(pet2.getTimestamp());
                            return date1.compareTo(date2); // Compare in ascending order
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }
                });

                // Update the RecyclerView with the sorted list
                likedPetsAdapter.notifyDataSetChanged();
            }
        });

        // Find the sort button by its ID
        Button sortMostRecent = view.findViewById(R.id.sortMostRecent);
        // Set an OnClickListener for the sort button
        sortMostRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort the liked pets by most recent timestamp
                Collections.sort(likesList, new Comparator<Pet>() {
                    @Override
                    public int compare(Pet pet1, Pet pet2) {
                        // Compare pets by timestamp (most recent first)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date date1 = dateFormat.parse(pet1.getTimestamp());
                            Date date2 = dateFormat.parse(pet2.getTimestamp());
                            return date2.compareTo(date1); // Compare in descending order
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }
                });

                // Update the RecyclerView with the sorted list
                likedPetsAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

}
