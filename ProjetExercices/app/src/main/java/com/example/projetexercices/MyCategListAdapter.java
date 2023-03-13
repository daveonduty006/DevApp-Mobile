package com.example.projetexercices;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.bumptech.glide.*;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class MyCategListAdapter extends RecyclerView.Adapter<MyCategViewHolder> {

    Context context;
    List<Exercice> categories;
    MyCategSelectListener selectListener;

    public MyCategListAdapter(Context context, List<Exercice> categories, MyCategSelectListener selectListener) {
        this.context = context;
        this.categories = categories;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public MyCategViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCategViewHolder(LayoutInflater.from(context).inflate(R.layout.categorie_view, parent,  false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategViewHolder holder, int position) {
        String nomCategorie = categories.get(position).getCategorie();
        //
        int idDrawable = categories.get(position).getIdImage();
        Glide.with(context)
                .load(idDrawable)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(holder.imageView);
        //
        holder.button.setText(nomCategorie);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.onCategBtnClicked(nomCategorie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

}
