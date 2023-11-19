package com.example.pethome;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pethome.chat.activities.ChatActivityChat;
import com.example.pethome.chat.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

//import com.unity3d.player.UnityPlayerActivity;
public class CardDetailsAdapter {
    public void bindData(View view, Pet pet) {
        // Bind data to the UI components in the details card

        ImageView image = view.findViewById(R.id.image);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvBreed = view.findViewById(R.id.tvBreed);
        TextView tvAge = view.findViewById(R.id.tvAge);
        TextView tvGender = view.findViewById(R.id.tvGender);
        AppCompatImageView messageBtn = view.findViewById(R.id.messageBtn);
        AppCompatImageView arBtn = view.findViewById(R.id.arBtn);

        Picasso.get().load(pet.getImageUrl()).into(image);

        tvName.setText(pet.getName());
        tvBreed.setText(pet.getBreed());
        tvAge.setText(pet.getAge() + "M");
        tvGender.setText(pet.getGender());

        arBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), ChatActivityChat.class);
                User user = new User();
                user.name = "Pets Avenue Clinic";
                user.email = "PAC@shelter.com";
                user.image = "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";
                user.token = "dO1xNC6xRjCKW6WzTaivoe:APA91bEIzFGJW08TYLnlM1RLZcZXv6O_BmQ0TXG4vsOw8uAZBMtb9UGCznPhr6YHhDW0sGBP6JxKRM2pYQfnz9CbESaoQ2RKw8j_lpDdPJJ4h4LSw6aqwVQEjBBQyISeDgDo98Mutp50";
                user.id = "x4XNdj2kBZ0bFxaAJqfc";
                i.putExtra(Constants.KEY_USER, user);
                startActivity(view.getContext(),i,null);
            }
        });


    }


//    private void openAr(){
//        Intent i = new Intent(getContext(), UnityPlayerActivity.class);
//        //send data to unity
//        i.putExtra("result","some Data");
//        startActivity(getContext(),i,null);
//
//    }
}
