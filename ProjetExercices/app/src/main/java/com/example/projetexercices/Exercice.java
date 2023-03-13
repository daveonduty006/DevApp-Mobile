package com.example.projetexercices;

import android.widget.ImageView;

public class Exercice {

    private int id;
    private String nom;
    private String categorie;
    private String description;
    private String instructions;
    private String urlVideo;
    private String cheminImage;

    public Exercice() {
    }

    public Exercice(int id, String nom, String categorie, String description, String instructions, String urlVideo, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.instructions = instructions;
        this.urlVideo = urlVideo;
        this.cheminImage = cheminImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
