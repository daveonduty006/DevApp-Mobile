package com.example.projetexercices;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
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

public class MyExerciceListAdapter extends RecyclerView.Adapter<MyExerciceViewHolder> implements Parcelable {

    Context context;
    List<Exercice> exercices;
    MyExerciceSelectListener selectListener;
    MyModifierSelectListener selectModifyListener;
    MyDeleteSelectListener selectDeleteListener;

    public MyExerciceListAdapter(Context context, List<Exercice> exercices, MyExerciceSelectListener selectListener, MyModifierSelectListener selectModifyListener, MyDeleteSelectListener selectDeleteListener) {
        this.context = context;
        this.exercices = exercices;
        this.selectListener = selectListener;
        this.selectModifyListener = selectModifyListener;
        this.selectDeleteListener = selectDeleteListener;
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
        holder.edit_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectModifyListener.onModifierImgBtnClicked(exercice);
            }
        });
        holder.delete_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDeleteListener.onSupprimerImgBtnClicked(exercice, position);
            }
        });
        holder.textView.setText(exercice.getDescription());
    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }

    ///////////////////////////////////////////////////////////////////////////////

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeTypedList(exercices);
    }

    protected MyExerciceListAdapter(Parcel in) {
        in.readTypedList(exercices, Exercice.CREATOR);
    }

    public static final Parcelable.Creator<MyExerciceListAdapter> CREATOR = new Parcelable.Creator<MyExerciceListAdapter>() {
        @Override
        public MyExerciceListAdapter createFromParcel(Parcel in) {
            return new MyExerciceListAdapter(in);
        }

        @Override
        public MyExerciceListAdapter[] newArray(int size) {
            return new MyExerciceListAdapter[size];
        }
    };
}
