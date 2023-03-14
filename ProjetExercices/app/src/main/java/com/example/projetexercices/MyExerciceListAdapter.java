package com.example.projetexercices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }

}
