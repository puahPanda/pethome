package com.example.pethome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    private ArrayAdapter<String> arrayAdapter;
    List<String> data;
    SwipeFlingAdapterView flingAdapterView;
    ImageView like,dislike;

    TextView petname;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        View view = inflater.inflate(R.layout.fragment_swipe, container, false);
        flingAdapterView=view.findViewById(R.id.swipe);
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("Pet");
        LayoutInflater inflater2 = getLayoutInflater();
        View anotherLayout = inflater2.inflate(R.layout.item, null);
        like=anotherLayout.findViewById(R.id.like);
        dislike=anotherLayout.findViewById(R.id.dislike);
        petname=view.findViewById(R.id.petnameid);



        String documentId = "RmSC0pgXL6UaMvcl9bNG";


        ListenerRegistration listenerRegistration = collectionRef.document(documentId)
                .addSnapshotListener((documentSnapshot, e) -> {
                    if (e != null) {
                        // Handle any errors that occurred during the listener registration
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        // Get the data from "field1"
                        String field1Data = documentSnapshot.getString("Name");

                        // Now you can use field1Data in your application
                        // Example: display it in a TextView

                    } else {
                        // The document does not exist or has been deleted
                    }

                });


        PetFunction.readCollectionAndReturnNames("Pet", new PetFunction.FirestoreDataCallback() {
            @Override
            public void onDataReceived(List<String> names) {
                String a="";
                for (String name : names) {
                    a+=name;
                }
                // Handle the names list, which contains "Name" field values from each document

            }

            @Override
            public void onError(Exception e) {
                // Handle errors here
            }
        });


        String extractedData = petname.getText().toString();

        String[] parts = extractedData.split("(?=[A-Z])");
        
        // Add the split strings to the ArrayList
        data=new ArrayList<>();
        data.add("Joshua");
        data.add("Mary");
        data.add("Elicia");
        data.add("David");











        arrayAdapter=new ArrayAdapter<>(getContext(), R.layout.item, R.id.data, data);

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
                readUser("V55wAs8ZTFCo9C6Dzvnr", "xgCa73GNI1A4DhZtmsZh");
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
                Toast.makeText(getContext(), "data is "+data.get(i),Toast.LENGTH_SHORT).show();
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
        return view;

    }
}