package com.example.exercice_intentexplicite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Personne implements Parcelable {

    private String nom, prenom, sexe;
    private int age;

    public Personne(String nom, String prenom, int age, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSexe(String sexe) {
        if(sexe.length() == 1) {
            this.sexe = sexe;
        }
    }

    //////////////////////////////////////////////////////////////////////

    protected Personne(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        age = in.readInt();
        sexe = in.readString();
    }

    public static final Creator<Personne> CREATOR = new Creator<Personne>() {
        @Override
        public Personne createFromParcel(Parcel in) {
            return new Personne(in);
        }

        @Override
        public Personne[] newArray(int size) {
            return new Personne[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeInt(age);
        parcel.writeString(sexe);
    }

}
