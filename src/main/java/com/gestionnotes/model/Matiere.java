package com.gestionnotes.model;

public class Matiere {
    private int id;
    private String numMatiere;
    private String nom;
    private int coefficient;

    public Matiere() {}

    public Matiere(int id, String numMatiere, String nom, int coefficient) {
        this.id = id;
        this.numMatiere = numMatiere;
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumMatiere() { return numMatiere; }
    public void setNumMatiere(String numMatiere) { this.numMatiere = numMatiere; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getCoefficient() { return coefficient; }
    public void setCoefficient(int coefficient) { this.coefficient = coefficient; }
}