package com.example.projetexercices;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyExerciceViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public Button button;

    public MyExerciceViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.exercice_imageView);
        button = itemView.findViewById(R.id.exercice_btn);
    }
}