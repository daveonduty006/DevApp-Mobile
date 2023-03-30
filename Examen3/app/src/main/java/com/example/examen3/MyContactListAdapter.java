package com.example.examen3;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyContactListAdapter extends RecyclerView.Adapter<MyContactViewHolder> implements Parcelable {

    Context context;
    List<Contact> contacts;
    MyPhoneSelectListener myPhoneSelectListener;
    MyDeleteSelectListener myDeleteSelectListener;

    public MyContactListAdapter(Context context, List<Contact> contacts, MyPhoneSelectListener myPhoneSelectListener, MyDeleteSelectListener myDeleteSelectListener) {
        this.context = context;
        this.contacts = contacts;
        this.myPhoneSelectListener = myPhoneSelectListener;
        this.myDeleteSelectListener = myDeleteSelectListener;
    }

    @NonNull
    @Override
    public MyContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyContactViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nom_textview.setText(contact.getPrenom()+"  "+contact.getNom());
        holder.phone_textView.setText(contact.getTelephone());
        holder.phone_imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPhoneSelectListener.onPhoneImgBtnClicked(contact.getTelephone());
            }
        });
        holder.delete_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDeleteSelectListener.onDeleteImgBtnClicked(contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    /////////////////////////////////////////

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeTypedList(contacts);
    }

    protected MyContactListAdapter(Parcel in) {
        in.readTypedList(contacts, Contact.CREATOR);
    }

    public static final Parcelable.Creator<MyContactListAdapter> CREATOR = new Parcelable.Creator<MyContactListAdapter>() {
        @Override
        public MyContactListAdapter createFromParcel(Parcel in) {
            return new MyContactListAdapter(in);
        }

        @Override
        public MyContactListAdapter[] newArray(int size) {
            return new MyContactListAdapter[size];
        }
    };

}
