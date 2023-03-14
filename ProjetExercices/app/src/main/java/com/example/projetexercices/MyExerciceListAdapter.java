package com.example.projetexercices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.List;

public class MyExerciceListAdapter extends RecyclerView.Adapter<MyExerciceViewHolder> {

    Context context;
    List<Exercice> exercices;
    MyExerciceSelectListener selectListener;

    public MyExerciceListAdapter(Context context, List<Exercice> exercices, MyExerciceSelectListener selectListener) {
        this.context = context;
        this.exercices = exercices;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public MyExerciceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyExerciceViewHolder(LayoutInflater.from(context).inflate(R.layout.exercice_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyExerciceViewHolder holder, int position) {
        Exercice exercice = exercices.get(position);
        File image = new File(exercice.getCheminImage());
        Glide.with(context)
                .load(image)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(holder.imageView);
        //
        holder.button.setText(exercice.getNom());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.onExerciceBtnClicked(exercice);
            }
        });
        holder.textView.setText(exercice.getDescription());
    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }

}
