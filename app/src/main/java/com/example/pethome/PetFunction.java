package com.example.pethome;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class PetFunction {

    public interface FirestoreDataCallback {
        void onDataReceived(List<String> nameValues);
        void onError(Exception e);
    }

    public static void readCollectionAndReturnNames(String collectionName, FirestoreDataCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection(collectionName);

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {
                        List<String> nameValues = new ArrayList<>();

                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            String name = document.getString("Name");
                            if (name != null) {
                                nameValues.add(name);
                            }
                        }

                        callback.onDataReceived(nameValues);
                    } else {
                        callback.onError(new Exception("Query snapshot is null"));
                    }
                } else {
                    callback.onError(task.getException());
                }
            }
        });
    }
}


