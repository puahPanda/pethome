package com.example.pethome;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

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
  class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

      public String getTimestamp() {
      }
  }

public class LikedSortAge {
    public static void main(String[] args) {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Alice", 30));
        pets.add(new Pet("Bob", 25));
        pets.add(new Pet("Charlie", 35));
        pets.add(new Pet("David", 28));

        // Define a custom comparator to sort by age
        Comparator<Pet> ageComparator = Comparator.comparingInt(Pet::getAge);

        // Sort the list based on age
        Collections.sort(pets, ageComparator);

        // Print the sorted list
        for (Pet pet : pets) {
            System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge() );
        }
    }
}
