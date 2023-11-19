package com.example.pethome;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

public class LikedPetsAdapter extends RecyclerView.Adapter<LikedPetsAdapter.ViewHolder> {

    Context context;
    View view;
    List<Pet>likedList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.liked_card, parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pet pet_item = likedList.get(position);
        Glide.with(view).load(pet_item.getImageUrl()).into(holder.leftImage);
        holder.leftName.setText(pet_item.getName());
        holder.leftAge.setText(pet_item.getAge() + "M");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("likedpets", "onClick: likedpet clicked");
                showDetailsDialog(pet_item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return likedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView leftImage;
        TextView leftName;
        TextView leftAge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftImage = itemView.findViewById(R.id.leftImage);
            leftName = itemView.findViewById(R.id.leftName);
            leftAge = itemView.findViewById(R.id.leftAge);
        }
    }

    public LikedPetsAdapter(Context context, List<Pet>likedList) {
        this.context = context;
        this.likedList = likedList;
    }



    private void showDetailsDialog(
            Pet pet) {
        // Create a dialog to show details
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.TransparentDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.petcardpopup, null);

        // Bind data to the details card using the CardDetailsAdapter
        CardDetailsAdapter detailsAdapter = new CardDetailsAdapter();

        detailsAdapter.bindData(view, pet);

        builder.setView(view);

        // Allow the user to click outside the dialog to dismiss it
        builder.setCancelable(true);

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.show();
    }


}
