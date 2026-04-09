package com.gestionnotes.model;

import java.sql.Timestamp;

public class Audit {
    private int id;
    private String operation;
    private String tableName;
    private String ancienValeur;
    private String nouvelleValeur;
    private Timestamp dateOperation;

    // GETTERS & SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }

    public String getAncienValeur() { return ancienValeur; }
    public void setAncienValeur(String ancienValeur) { this.ancienValeur = ancienValeur; }

    public String getNouvelleValeur() { return nouvelleValeur; }
    public void setNouvelleValeur(String nouvelleValeur) { this.nouvelleValeur = nouvelleValeur; }

    public Timestamp getDateOperation() { return dateOperation; }
    public void setDateOperation(Timestamp dateOperation) { this.dateOperation = dateOperation; }

    // ===== BONUS pour JSP =====
    public String getTypeOperation() {
        return operation;
    }

    public String getUtilisateur() {
        return tableName;
    }

    public String getAncienEtat() {
        return ancienValeur;
    }

    public String getNouvelEtat() {
        return nouvelleValeur;
    }
}