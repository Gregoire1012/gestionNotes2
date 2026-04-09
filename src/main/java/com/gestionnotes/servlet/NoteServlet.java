package com.gestionnotes.servlet;

import com.gestionnotes.dao.NoteDAO;
import com.gestionnotes.dao.AuditDAO;
import com.gestionnotes.model.Note;
import com.gestionnotes.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/note")
public class NoteServlet extends HttpServlet {

    private NoteDAO noteDAO = new NoteDAO();

    // ================= GET =================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            // ===== EDIT =====
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Note n = noteDAO.getById(id);

                if (n != null) {
                    request.setAttribute("id", n.getId());
                    request.setAttribute("etudiant", n.getEtudiant());
                    request.setAttribute("matiere", n.getMatiere());
                    request.setAttribute("note", n.getNote());
                }
            }

            // ===== DELETE =====
            else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Note old = noteDAO.getById(id);

                noteDAO.supprimer(id);

                // Audit suppression
                if (old != null) {
                    AuditDAO.ajouterAudit(
                        "SUPPRESSION NOTE",
                        "note",
                        "Etudiant=" + old.getEtudiant() + ", Matiere=" + old.getMatiere() + ", Note=" + old.getNote(),
                        "-"
                    );
                }

                response.sendRedirect("note");
                return;
            }

            // ===== LISTE DES NOTES =====
            List<Note> listeNotes = noteDAO.getAll();
            request.setAttribute("listeNotes", listeNotes);

            // ===== LISTE ETUDIANTS =====
            List<String> etudiants = new ArrayList<>();
            List<String> matieres = new ArrayList<>();

            try (Connection con = DBConnection.getConnection();
                 Statement stmt1 = con.createStatement();
                 Statement stmt2 = con.createStatement();
                 ResultSet rs1 = stmt1.executeQuery("SELECT matricule FROM etudiant");
                 ResultSet rs2 = stmt2.executeQuery("SELECT num_matiere FROM matiere")) {

                while (rs1.next()) {
                    etudiants.add(rs1.getString("matricule"));
                }

                while (rs2.next()) {
                    matieres.add(rs2.getString("num_matiere"));
                }
            }

            request.setAttribute("etudiants", etudiants);
            request.setAttribute("matieres", matieres);

            request.getRequestDispatcher("/views/notes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur dans NoteServlet", e);
        }
    }

    // ================= POST =================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String idStr = request.getParameter("id");
            int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;

            String etudiant = request.getParameter("etudiant");
            String matiere = request.getParameter("matiere");
            double noteValue = Double.parseDouble(request.getParameter("note"));

            Note n = new Note();
            n.setEtudiant(etudiant);
            n.setMatiere(matiere);
            n.setNote(noteValue);

            // ===== INSERT =====
            if (id == 0) {
                noteDAO.ajouter(n);

                AuditDAO.ajouterAudit(
                    "AJOUT NOTE",
                    "note",
                    "-",
                    "Etudiant=" + etudiant + ", Matiere=" + matiere + ", Note=" + noteValue
                );
            }

            // ===== UPDATE =====
            else {
                Note old = noteDAO.getById(id);

                n.setId(id);
                noteDAO.modifier(n);

                if (old != null) {
                    AuditDAO.ajouterAudit(
                        "MODIFICATION NOTE",
                        "note",
                        "Etudiant=" + old.getEtudiant() + ", Matiere=" + old.getMatiere() + ", Note=" + old.getNote(),
                        "Etudiant=" + etudiant + ", Matiere=" + matiere + ", Note=" + noteValue
                    );
                }
            }

            response.sendRedirect("note");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de l'enregistrement", e);
        }
    }
}