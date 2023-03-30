package com.example.examen3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Contact implements Parcelable {

    private long id;
    private String prenom;
    private String nom;
    private String telephone;

    public Contact() {
    }

    public Contact(long id, String nom, String prenom, String telephone) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    ///////////////////////////////////

    protected Contact(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        prenom = in.readString();
        telephone = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(telephone);
    }
}
