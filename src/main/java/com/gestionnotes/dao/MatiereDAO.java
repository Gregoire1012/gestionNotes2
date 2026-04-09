package com.gestionnotes.dao;

import com.gestionnotes.model.Matiere;
import com.gestionnotes.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDAO {

	public static List<Matiere> getAllMatieres() throws Exception {
	    List<Matiere> liste = new ArrayList<>();

	    String sql = "SELECT id, num_matiere, nom, coefficient FROM matiere";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Matiere m = new Matiere(
	                rs.getInt("id"),
	                rs.getString("num_matiere"),
	                rs.getString("nom"),
	                rs.getInt("coefficient")
	            );
	            liste.add(m);
	        }
	    }

	    return liste;
	}

    public static void ajouterMatiere(Matiere m) throws Exception {
        String sql = "INSERT INTO matiere (num_matiere, nom, coefficient) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNumMatiere());
            ps.setString(2, m.getNom());
            ps.setInt(3, m.getCoefficient());

            ps.executeUpdate();
        }
    }

    public static void modifierMatiere(Matiere m) throws Exception {
        String sql = "UPDATE matiere SET num_matiere=?, nom=?, coefficient=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNumMatiere());
            ps.setString(2, m.getNom());
            ps.setInt(3, m.getCoefficient());
            ps.setInt(4, m.getId());

            ps.executeUpdate();
        }
    }

    public static void supprimerMatiere(int id) throws Exception {
        String sql = "DELETE FROM matiere WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static Matiere getMatiereById(int id) throws Exception {
        String sql = "SELECT * FROM matiere WHERE id=?";
        Matiere m = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Matiere(
                    rs.getInt("id"),
                    rs.getString("num_matiere"),
                    rs.getString("nom"),
                    rs.getInt("coefficient")
                );
            }
        }
        return m;
    }
}