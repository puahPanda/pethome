package com.example.pethome;

import static androidx.core.content.ContextCompat.startActivity;

import static java.security.AccessController.getContext;

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
import com.unity3d.player.UnityPlayerActivity;

import java.util.HashMap;
import java.util.List;

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
                openAr(v);
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ChatActivityChat.class);
                User user = new User();
                user.name = "Pets Avenue Clinic";
                user.email = "PAC@shelter.com";
                user.image = "gs://pethome-24dfc.appspot.com/uploads/pacimage.png";
                user.token = "dIBSAMkIQLW775DHGUnV8Y:APA91bHmiDaQHXB33XzvnsWhoJLRYjnTV6W95JDQyznHFVssASy9pfmlIyFPhQxWuHgrlF6qUlf450ec5hX0EH7eYhwym4UfjD_WGJghxqJGWOXk5GhLixFGYwhr4njFu1QVuF342s";
                user.id = "yWXTtgEbSYMpMXlwLQTK";
                i.putExtra(Constants.KEY_USER, user);
                startActivity(v.getContext(),i,null);
            }
        });


    }



    private void openAr(View v){
        Intent i = new Intent(v.getContext(), UnityPlayerActivity.class);
        //send data to unity
        i.putExtra("result","some Data");
        startActivity(v.getContext(),i,null);

    }
}
