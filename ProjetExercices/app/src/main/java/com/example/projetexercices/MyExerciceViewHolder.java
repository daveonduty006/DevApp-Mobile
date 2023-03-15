package com.example.projetexercices;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyExerciceViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public Button button;
    public ImageButton edit_imgBtn;
    public ImageButton delete_imgBtn;
    public TextView textView;

    public MyExerciceViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.exercice_imageView);
        button = itemView.findViewById(R.id.exercice_btn);
        edit_imgBtn = itemView.findViewById(R.id.edit_btn);
        delete_imgBtn = itemView.findViewById(R.id.delete_btn);
        textView = itemView.findViewById(R.id.exercice_desc_txtview);
    }
}
