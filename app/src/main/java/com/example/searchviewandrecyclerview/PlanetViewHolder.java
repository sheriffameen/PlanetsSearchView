package com.example.searchviewandrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlanetViewHolder extends RecyclerView.ViewHolder {
    private TextView planetName_textView;
    private TextView planetNumber_textView;
    private ImageView planetImage_textView;

    public PlanetViewHolder(@NonNull View itemView) {
        super(itemView);

        planetName_textView = itemView.findViewById(R.id.planetName_textview);
        planetNumber_textView = itemView.findViewById(R.id.planetNumber_textview);
        planetImage_textView = itemView.findViewById(R.id.planetImage_imageview);
    }

    public void onBind(Planet planet){
        planetName_textView.setText(planet.getName());
        planetNumber_textView.setText(planet.getNumber());
        Picasso.get().load(planet.getImage()).into(planetImage_textView);


    }
}
