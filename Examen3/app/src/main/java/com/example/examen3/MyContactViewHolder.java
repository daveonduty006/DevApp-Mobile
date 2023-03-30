package com.example.examen3;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyContactViewHolder extends RecyclerView.ViewHolder {

    public TextView nom_textview;
    public TextView phone_textView;
    public ImageButton phone_imagebtn;
    public ImageButton delete_image_btn;

    public MyContactViewHolder(@NonNull View itemView) {
        super(itemView);
        nom_textview = itemView.findViewById(R.id.nom_complet_textview);
        phone_textView = itemView.findViewById(R.id.contact_textview);
        phone_imagebtn = itemView.findViewById(R.id.contact_phone_imagebtn);
        delete_image_btn = itemView.findViewById(R.id.contact_delete_imagebtn);
    }
}
