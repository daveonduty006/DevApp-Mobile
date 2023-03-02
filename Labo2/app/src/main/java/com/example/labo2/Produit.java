package com.example.labo2;

public class Produit {

    private static final String CODES_CATEG[] = {
            "Boissons",
            "Condiments",
            "Produits secs",
            "Viandes",
            "Produits de la mer"
    };
    private int id, codeCateg, qte_stock;
    private String nom;
    private double prix;

    public Produit() {}

    public Produit(int id, String nom, int codeCateg, double prix, int qte_stock) {
        this.setId(id);
        this.setNom(nom);
        this.setCodeCateg(codeCateg);
        this.setPrix(prix);
        this.setQte_stock(qte_stock);
    }

    public Produit(Produit produit) {
        this.id = produit.getId();
        this.nom = produit.getNom();
        this.codeCateg = produit.getCodeCateg();
        this.prix = produit.getPrix();
        this.qte_stock = produit.getQte_stock();
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public int getCodeCateg() {
        return this.codeCateg;
    }

    public double getPrix() {
        return this.prix;
    }

    public int getQte_stock() {
        return this.qte_stock;
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public void setNom(String nom) {
        if(nom.length() > 0){
            this.nom = nom;
        }
    }

    public void setCodeCateg(int codeCateg) {
        if(codeCateg >= 0 && codeCateg <= 4) {
            this.codeCateg = codeCateg;
        }
    }

    public void setPrix(double prix) {
        if(prix >= 0){
            this.prix = prix;
        }
    }

    public void setQte_stock(int qte_stock) {
        if(qte_stock >= 0){
            this.qte_stock = qte_stock;
        }
    }

    public String getCategString() {
        switch(this.codeCateg){
            case 0:
                return CODES_CATEG[0];
            case 1:
                return CODES_CATEG[1];
            case 2:
                return CODES_CATEG[2];
            case 3:
                return CODES_CATEG[3];
            case 4:
                return CODES_CATEG[4];
            default:
                return "code invalide";
        }
    }

}
