package com.example.pethome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

}
