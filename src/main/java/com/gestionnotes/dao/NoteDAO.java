package com.gestionnotes.dao;

import com.gestionnotes.model.Note;
import com.gestionnotes.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    public List<Note> getAll() throws Exception {
        List<Note> liste = new ArrayList<>();
        String sql = "SELECT id, etudiant, matiere, note FROM note";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(new Note(
                        rs.getInt("id"),
                        rs.getString("etudiant"),
                        rs.getString("matiere"),
                        rs.getDouble("note")
                ));
            }
        }
        return liste;
    }

    public void ajouter(Note n) throws Exception {
        String sql = "INSERT INTO note (etudiant, matiere, note) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, n.getEtudiant());
            ps.setString(2, n.getMatiere());
            ps.setDouble(3, n.getNote());
            ps.executeUpdate();
        }
    }

    public void modifier(Note n) throws Exception {
        String sql = "UPDATE note SET etudiant=?, matiere=?, note=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, n.getEtudiant());
            ps.setString(2, n.getMatiere());
            ps.setDouble(3, n.getNote());
            ps.setInt(4, n.getId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws Exception {
        String sql = "DELETE FROM note WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Note getById(int id) throws Exception {
        Note n = null;
        String sql = "SELECT * FROM note WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = new Note(
                        rs.getInt("id"),
                        rs.getString("etudiant"),
                        rs.getString("matiere"),
                        rs.getDouble("note")
                );
            }
        }
        return n;
    }

    // ================= NOUVELLES MÉTHODES =================

    // Supprimer toutes les notes d'un étudiant
    public static void supprimerParEtudiant(String matricule) throws Exception {
        String sql = "DELETE FROM note WHERE etudiant=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricule);
            ps.executeUpdate();
        }
    }

    // Supprimer toutes les notes d'une matière
    public static void supprimerParMatiere(String numMatiere) throws Exception {
        String sql = "DELETE FROM note WHERE matiere=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numMatiere);
            ps.executeUpdate();
        }
    }
    
 // Mettre à jour le matricule d'un étudiant dans les notes
    public static void modifierMatriculeEtudiant(String ancienMatricule, String nouveauMatricule) throws Exception {
        String sql = "UPDATE note SET etudiant=? WHERE etudiant=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nouveauMatricule);
            ps.setString(2, ancienMatricule);
            ps.executeUpdate();
        }
    }

    // Mettre à jour le numéro d'une matière dans les notes
    public static void modifierMatiere(String ancienNum, String nouveauNum) throws Exception {
        String sql = "UPDATE note SET matiere=? WHERE matiere=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nouveauNum);
            ps.setString(2, ancienNum);
            ps.executeUpdate();
        }
    }
}


