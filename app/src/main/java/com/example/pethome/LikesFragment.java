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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
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

        return view;
    }

}
