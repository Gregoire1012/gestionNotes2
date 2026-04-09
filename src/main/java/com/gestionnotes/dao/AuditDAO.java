package com.gestionnotes.dao;

import com.gestionnotes.model.Audit;
import com.gestionnotes.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditDAO {

    // Ajouter un audit
    public static void ajouterAudit(String operation, String table, String ancien, String nouveau) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO audit(operation, table_name, ancien_valeur, nouvelle_valeur) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, operation);
            ps.setString(2, table);
            ps.setString(3, ancien);
            ps.setString(4, nouveau);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Récupérer tous les audits
    public static List<Audit> getAllAudit() {
        List<Audit> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM audit ORDER BY date_operation DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Audit a = new Audit();
                a.setId(rs.getInt("id"));
                a.setOperation(rs.getString("operation"));
                a.setTableName(rs.getString("table_name"));
                a.setAncienValeur(rs.getString("ancien_valeur"));
                a.setNouvelleValeur(rs.getString("nouvelle_valeur"));
                a.setDateOperation(rs.getTimestamp("date_operation"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Récupérer les audits avec filtres (table, opération, date)
    public static List<Audit> getAll(String table, String operation, String date) {
        List<Audit> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM audit WHERE table_name LIKE ? AND operation LIKE ? AND date_operation LIKE ? ORDER BY date_operation DESC";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, table != null && !table.isEmpty() ? table + "%" : "%");
            ps.setString(2, operation != null && !operation.isEmpty() ? operation + "%" : "%");
            ps.setString(3, date != null && !date.isEmpty() ? date + "%" : "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Audit a = new Audit();
                a.setId(rs.getInt("id"));
                a.setOperation(rs.getString("operation"));
                a.setTableName(rs.getString("table_name"));
                a.setAncienValeur(rs.getString("ancien_valeur"));
                a.setNouvelleValeur(rs.getString("nouvelle_valeur"));
                a.setDateOperation(rs.getTimestamp("date_operation"));
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}