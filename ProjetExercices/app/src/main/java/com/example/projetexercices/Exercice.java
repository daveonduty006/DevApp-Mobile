package com.example.projetexercices;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Exercice implements Parcelable {

    private long id;
    private String nom;
    private String categorie;
    private String description;
    private String instructions;
    private String urlVideo;
    private String cheminImage;

    public Exercice() {
    }

    public Exercice(long id, String nom, String categorie, String description, String instructions, String urlVideo, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.instructions = instructions;
        this.urlVideo = urlVideo;
        this.cheminImage = cheminImage;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", description='" + description + '\'' +
                ", instructions='" + instructions + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", cheminImage='" + cheminImage + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    //////////////////////////////////////////////////////////////////////////

    protected Exercice(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        categorie = in.readString();
        description = in.readString();
        instructions = in.readString();
        urlVideo = in.readString();
        cheminImage = in.readString();
    }

    public static final Creator<Exercice> CREATOR = new Creator<Exercice>() {
        @Override
        public Exercice createFromParcel(Parcel in) {
            return new Exercice(in);
        }

        @Override
        public Exercice[] newArray(int size) {
            return new Exercice[size];
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
        parcel.writeString(categorie);
        parcel.writeString(description);
        parcel.writeString(instructions);
        parcel.writeString(urlVideo);
        parcel.writeString(cheminImage);
    }
}
