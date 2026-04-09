package com.gestionnotes.dao;

import com.gestionnotes.model.Etudiant;
import com.gestionnotes.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    // Récupérer tous les étudiants
    public static List<Etudiant> getAllEtudiants() throws Exception {
        List<Etudiant> liste = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(new Etudiant(
                        rs.getInt("id"),
                        rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDouble("moyenne")
                ));
            }
        }
        return liste;
    }

    // Récupérer un étudiant par ID (ajouté)
    public static Etudiant getById(int id) throws Exception {
        String sql = "SELECT * FROM etudiant WHERE id=?";
        Etudiant e = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e = new Etudiant(
                            rs.getInt("id"),
                            rs.getString("matricule"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getDouble("moyenne")
                    );
                }
            }
        }
        return e;
    }

    // Ajouter un étudiant
    public static void ajouterEtudiant(Etudiant e) throws Exception {
        String sql = "INSERT INTO etudiant (matricule, nom, prenom, moyenne) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getMatricule());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getPrenom());
            ps.setDouble(4, e.getMoyenne());
            ps.executeUpdate();
        }
    }

    // Modifier un étudiant
    public static void modifierEtudiant(Etudiant e) throws Exception {
        String sql = "UPDATE etudiant SET matricule=?, nom=?, prenom=?, moyenne=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getMatricule());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getPrenom());
            ps.setDouble(4, e.getMoyenne());
            ps.setInt(5, e.getId());
            ps.executeUpdate();
        }
    }

    // Supprimer un étudiant
    public static void supprimerEtudiant(int id) throws Exception {
        String sql = "DELETE FROM etudiant WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}