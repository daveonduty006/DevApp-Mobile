package com.example.projetexercices;

import android.widget.ImageView;

public class Exercice {

    private int id;
    private String nom;
    private String categorie;
    private String description;
    private String instructions;
    private String urlVideo;
    private int idImage;

    public Exercice() {
    }

    public Exercice(int id, String nom, String categorie, String description, String instructions, String urlVideo, int idImage) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.instructions = instructions;
        this.urlVideo = urlVideo;
        this.idImage = idImage;
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

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

}
