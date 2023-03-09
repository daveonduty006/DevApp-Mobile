package com.example.examen2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Film implements Parcelable {

    private int numFilm;
    private String titre, categorie;
    private double classement;

    public Film() {}

    public Film(int numFilm, String titre, double classement, String categorie) {
        this.numFilm = numFilm;
        this.titre = titre;
        this.classement = classement;
        this.categorie = categorie;
    }

    public int getNumFilm() {
        return numFilm;
    }

    public String getTitre() {
        return titre;
    }

    public double getClassement() {
        return classement;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setNumFilm(int numFilm) {
        this.numFilm = numFilm;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setClassement(double classement) {
        this.classement = classement;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String afficherFilm() {
        return " Film("+numFilm+","+titre+","+classement+","+categorie+");";
    }

    //////////////////////////////////////////////////////////////////////////

    protected Film(Parcel in) {
        numFilm = in.readInt();
        titre = in.readString();
        classement = in.readDouble();
        categorie = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(numFilm);
        parcel.writeString(titre);
        parcel.writeDouble(classement);
        parcel.writeString(categorie);
    }
}
