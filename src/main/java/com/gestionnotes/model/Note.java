package com.gestionnotes.model;

public class Note {
    private int id;
    private String etudiant; // matricule
    private String matiere;  // numéro matière
    private double note;

    public Note() { }

    public Note(int id, String etudiant, String matiere, double note) {
        this.id = id;
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.note = note;
    }

    // GETTERS & SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEtudiant() { return etudiant; }
    public void setEtudiant(String etudiant) { this.etudiant = etudiant; }

    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }

    public double getNote() { return note; }
    public void setNote(double note) { this.note = note; }
}