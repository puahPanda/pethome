package com.example.pethome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.Query;
import com.google.gson.Gson;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SwipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SwipeFragment extends Fragment {
    private ArrayAdapter<Pet> arrayAdapter;
    List<Pet> data = new ArrayList<Pet>();;
    List<Pet> filter_data = new ArrayList<>();


    SwipeFlingAdapterView flingAdapterView;
    ImageView like,dislike;
    TextView check;

    Integer count;
    String[]petids = {"jT44R9VIsaolRNNxCOkr", "RmSC0pgXL6UaMvcl9bNG", "u4rlGVHHG3gCknz1mO4c", "xgCa73GNI1A4DhZtmsZh", "yuigRukodFMhTxQB3EWq"};;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton arBtn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SwipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SwipeFragment newInstance(String param1, String param2) {
        SwipeFragment fragment = new SwipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SwipeFragment() {
        // Required empty public constructor
    }

    private void readUser(String userID, String petString) {
        DocumentReference docRef = db.collection("User").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                Log.d("DATABASE", "onSuccess: user" + user.getLikes());
                if(!user.likedBefore(petString)){
                    user.addLikes(petString);
                    likePet(user.getLikes());
                }
            }
        });
    }

    private void likePet(List<String> petString){
        //TODO add to database with current userID
        // Create a new user with a first and last name
        Map<String, Object> data = new HashMap<>();
        data.put("Likes", petString);

        db.collection("User").document("V55wAs8ZTFCo9C6Dzvnr")
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("db", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("db", "Error writing document", e);
                    }
                });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        count=0;
        View view = inflater.inflate(R.layout.fragment_swipe, container, false);
        flingAdapterView=view.findViewById(R.id.swipe);
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("Pet");
        //LayoutInflater inflater2 = getLayoutInflater();
        //View anotherLayout = inflater2.inflate(R.layout.item, null);

        like=view.findViewById(R.id.like);
        dislike=view.findViewById(R.id.dislike);


        filter_data = new ArrayList<Pet>();


            // Construct a Firestore query based on filter criteria
        Bundle args = getArguments();


        if (args != null) {
            String typeFilter = args.getString("type");
            String genderFilter = args.getString("gender");
            String vaccineFilter = args.getString("vaccine");

            Query query = collectionRef;

            if (typeFilter != null) {
                query = query.whereEqualTo("Type", typeFilter);
            }

            if (genderFilter != null) {
                query = query.whereEqualTo("Gender", genderFilter);
            }

            if (vaccineFilter != null) {
                if(vaccineFilter == "Yes") {
                    query = query.whereEqualTo("Vaccine", true);
                }
            }


            query.get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        // Handle the query results here
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Pet pet = document.toObject(Pet.class);
                            pet.setId(document.getId().toString());
                            Log.d("onItemClicked", "onCreateView: ADDING ");
                            // Add the pet to the data ArrayList
                            filter_data.add(pet);
                        }
                        arrayAdapter.notifyDataSetChanged();


                        // Now, the data ArrayList contains the filtered pets
                        // You can use the data ArrayList as needed.
                    })
                    .addOnFailureListener(e -> {
                        // Handle any errors that occur during the query
                    });



            // Add the split strings to the ArrayList




                arrayAdapter = new arrayAdapter(getContext(), R.layout.item, filter_data);
                flingAdapterView.setAdapter(arrayAdapter);

                flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                    @Override
                    public void removeFirstObjectInAdapter() {
                        filter_data.remove(0);
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onLeftCardExit(Object o) {

                        Toast.makeText(getContext(),"dislike",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightCardExit(Object o) {

                        Pet p = (Pet)o;
                        readUser("V55wAs8ZTFCo9C6Dzvnr", p.getId());
                        count++;
                        Toast.makeText(getContext(),"liked ",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), "data is "+filter_data.get(i).getId(),Toast.LENGTH_SHORT).show();
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
        } else {

            data = new ArrayList<Pet>();

            collectionRef
                    .addSnapshotListener((querySnapshot, error) -> {
                        if (error != null) {
                            // Handle error
                            return;
                        }

                        for (QueryDocumentSnapshot document : querySnapshot) {
                            String imageUrl = "default";
                            if (document.contains("ImageUrl")) {
                                imageUrl = document.getString("ImageUrl");
                            }
                            Integer age = document.getLong("Age").intValue();

                            Pet pet_item = new Pet(document.getString("Name"), imageUrl, document.getString("Breed"), document.getString("Gender"), age, document.getBoolean("Vaccine"));
                            pet_item.setId(document.getId());
                            data.add(pet_item);
                        }

                        // Notify your adapter (you should replace 'arrayAdapter' with your actual adapter)
                        arrayAdapter.notifyDataSetChanged();
                    });

            arrayAdapter = new arrayAdapter(getContext(), R.layout.item, data);

            flingAdapterView.setAdapter(arrayAdapter);

            flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                @Override
                public void removeFirstObjectInAdapter() {
                    data.remove(0);
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onLeftCardExit(Object o) {

                    Toast.makeText(getContext(),"dislike",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRightCardExit(Object o) {
                    Pet p = (Pet)o;
                    readUser("V55wAs8ZTFCo9C6Dzvnr", p.getId());

                    count++;
                    Toast.makeText(getContext(),"like",Toast.LENGTH_SHORT).show();
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
                    Log.d("onItemClicked", "onItemClicked: filterdaat " + data.toString());
                    Toast.makeText(getContext(), "data is "+data.get(i).getId(),Toast.LENGTH_SHORT).show();
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



        return view;

    }
}