package com.example.pethome;


import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

/**
 * Created by manel on 9/5/2017.
 */

public class arrayAdapter extends ArrayAdapter<Pet>{

    //test push code
    Context context;

    public arrayAdapter(Context context, int resourceId, List<Pet> items){
        super(context, resourceId, items);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Pet pet_item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.petname);
        TextView age = (TextView) convertView.findViewById(R.id.petage);
        TextView info = (TextView) convertView.findViewById(R.id.petinfo);
        ImageView image = (ImageView) convertView.findViewById(R.id.petimage);

        name.setText(pet_item.getName());
        age.setText(pet_item.getAge().toString() + " months");
        if(pet_item.isVaccine()) {
            info.setText(pet_item.getGender() + ", " + pet_item.getBreed() + ", Vaccinated");
        } else {
            info.setText(pet_item.getGender() + ", " + pet_item.getBreed() + ", Non-vaccinated");
        }
        switch(pet_item.getImageUrl()){
            case "default":
                Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
                break;
            default:
                Glide.with(convertView.getContext()).clear(image);
                Glide.with(convertView.getContext()).load(pet_item.getImageUrl()).into(image);
                break;
        }


        return convertView;

    }
}