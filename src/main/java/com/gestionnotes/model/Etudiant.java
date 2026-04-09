package com.gestionnotes.model;

public class Etudiant {
    private int id;
    private String matricule; // Nouveau champ
    private String nom;
    private String prenom;
    private double moyenne;

    // Constructeur par défaut
    public Etudiant() {}

    // Constructeur sans id (pour ajout)
    public Etudiant(String matricule, String nom, String prenom, double moyenne) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
    }

    // Constructeur complet avec id
    public Etudiant(int id, String matricule, String nom, String prenom, double moyenne) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public double getMoyenne() { return moyenne; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }
}