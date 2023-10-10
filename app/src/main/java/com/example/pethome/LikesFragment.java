package com.example.pethome;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
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
import java.util.Locale;

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
    TextInputEditText searchTE;
    private static final String SORT_NAME = "Sort Name";
    private static final String MOST_RECENT = "Most Recent";
    private static final String AGE = "Age";
    private static final String LEAST_RECENT = "Least Recent";

    public static final String[]sortTypes = new String[]{ MOST_RECENT,AGE,LEAST_RECENT };
    TextView tvSort;
//    List<String>names;
//    List<Integer>age;
//    int [] images = {R.drawable.cat1, R.drawable.duck1, R.drawable.gecko, R.drawable.babydog, R.drawable.gecko, R.drawable.cat1, R.drawable.duck1, R.drawable.gecko};

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
                            Log.d("DBA", "onSuccess: Pet added to list " + pet.getName() + " " + documentSnapshot2.getId());
                        }
                    }).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            showView(view,likesList);
                        }
                    });
                }
            }
        });
    }

    private void showView(View view, List<Pet>petList){
        recyclerView = view.findViewById(R.id.likesRecyclerView);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(null);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        likedPetsAdapter = new LikedPetsAdapter(getContext(), petList);
        recyclerView.setAdapter(likedPetsAdapter);
    }

    private void sortLeastRecent(){
        List<Pet>petList = new ArrayList<>(likesList);
//        Collections.sort(petList, new Comparator<Pet>() {
//            @Override
//            public int compare(Pet pet1, Pet pet2) {
//                // Compare pets by timestamp (least recent first)
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    Date date1 = dateFormat.parse(pet1.getTimestamp());
//                    Date date2 = dateFormat.parse(pet2.getTimestamp());
//                    return date1.compareTo(date2); // Compare in ascending order
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    return 0;
//                }
//            }
//        });
        for (Pet p :
                petList) {
            Log.d("SORT", "sortLeastRecent: " + p.getName());
        }

        Collections.reverse(petList);
        Log.d("SORT","laksjdlaskjdlajkd");
        for (Pet p :
                petList) {
            Log.d("SORT", "sortLeastRecent: " + p.getName());
        }

        // Update the RecyclerView with the sorted list
        showView(getView(),petList);
    }

    private void sortMostRecent(){
//        Collections.sort(petList, new Comparator<Pet>() {
//                    @Override
//                    public int compare(Pet pet1, Pet pet2) {
//                        // Compare pets by timestamp (most recent first)
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        try {
//                            Date date1 = dateFormat.parse(pet1.getTimestamp());
//                            Date date2 = dateFormat.parse(pet2.getTimestamp());
//                            return date2.compareTo(date1); // Compare in descending order
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            return 0;
//                        }
//                    }
//                });
        Log.d("SORT","laksjdlaskjdlajkd");
        for (Pet p :
                likesList) {
            Log.d("SORT", "sortMostRecent: " + p.getName());
        }
        // Update the RecyclerView with the sorted list
        showView(getView(),likesList);
    }

    private void sortAge(){
        List<Pet>petList = new ArrayList<>(likesList);
        Collections.sort(petList, new Comparator<Pet>() {
            @Override
            public int compare(Pet pet1, Pet pet2) {
                // Compare pets by age
                return Integer.compare(pet1.getAge(), pet2.getAge());
            }
        });

        // Update the RecyclerView with the sorted list
        showView(getView(),petList);
    }

    private void sortName(String searchString){
        List<Pet>petList = new ArrayList<>();

        Log.d("Search", "sortName: " + searchString);
        for (Pet p :
                likesList) {

            Log.d("Search", "sortName > petName: " + p.getName());
            if (p.getName().toUpperCase().contains(searchString.toUpperCase())) {
                Log.d("Search", "inside");
                petList.add(p);
            }
        }


        // Update the RecyclerView with the sorted list
        showView(getView(),petList);
    }

    private void conductSort(String sortType, String searchString){

        switch(sortType){
            case SORT_NAME:
                sortName(searchString);
                break;
            default:
                break;
        }
    }

    private void conductSort(String sortType){

        switch(sortType){
            case LEAST_RECENT:
                sortLeastRecent();
                break;
            case MOST_RECENT:
                sortMostRecent();
                break;
            case AGE:
                sortAge();
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_likes, container, false);
        likesList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        readUser("V55wAs8ZTFCo9C6Dzvnr",view);
        tvSort = view.findViewById(R.id.tvSort);
        searchTE = view.findViewById(R.id.searchTE);
        searchTE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                conductSort(SORT_NAME,searchTE.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Find the sort button by its ID
//        Button sortAge = view.findViewById(R.id.SortAge);

        // Set an OnClickListener for the sort button
        tvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go between 3 types
                // Sort the liked pets by age
                Log.d("FILTER", "onClick filter: FILTER CHANGE");
                for (int i = 0; i < sortTypes.length; i++) {
                    if (tvSort.getText().equals(LEAST_RECENT)){
                        tvSort.setText(MOST_RECENT);
                        conductSort(MOST_RECENT);
                        break;
                    }
                    if(tvSort.getText().equals(sortTypes[i])){
                        Log.d("FILTER", "equals :" + tvSort.getText() + " | " + sortTypes[i]);
                        tvSort.setText(sortTypes[i+1]);
                        conductSort(sortTypes[i+1]);
                        break;
                    }

                }
            }
        });

        // Find the sort button by its ID
//        Button sortLeastRecent = view.findViewById(R.id.sortLeastRecent);
//
//        // Set an OnClickListener for the sort button
//        sortLeastRecent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Sort the liked pets by least recent timestamp
//                Collections.sort(likesList, new Comparator<Pet>() {
//                    @Override
//                    public int compare(Pet pet1, Pet pet2) {
//                        // Compare pets by timestamp (least recent first)
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        try {
//                            Date date1 = dateFormat.parse(pet1.getTimestamp());
//                            Date date2 = dateFormat.parse(pet2.getTimestamp());
//                            return date1.compareTo(date2); // Compare in ascending order
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            return 0;
//                        }
//                    }
//                });
//
//                // Update the RecyclerView with the sorted list
//                likedPetsAdapter.notifyDataSetChanged();
//            }
//        });
//
//        // Find the sort button by its ID
//        Button sortMostRecent = view.findViewById(R.id.sortMostRecent);
//        // Set an OnClickListener for the sort button
//        sortMostRecent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Sort the liked pets by most recent timestamp
//                Collections.sort(likesList, new Comparator<Pet>() {
//                    @Override
//                    public int compare(Pet pet1, Pet pet2) {
//                        // Compare pets by timestamp (most recent first)
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        try {
//                            Date date1 = dateFormat.parse(pet1.getTimestamp());
//                            Date date2 = dateFormat.parse(pet2.getTimestamp());
//                            return date2.compareTo(date1); // Compare in descending order
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            return 0;
//                        }
//                    }
//                });
//
//                // Update the RecyclerView with the sorted list
//                likedPetsAdapter.notifyDataSetChanged();
//            }
//        });

        return view;
    }

}
