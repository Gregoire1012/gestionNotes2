package com.gestionnotes.servlet;

import com.gestionnotes.dao.MatiereDAO;
import com.gestionnotes.dao.NoteDAO;
import com.gestionnotes.dao.AuditDAO;
import com.gestionnotes.model.Matiere;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/matiere")
public class MatiereServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String action = request.getParameter("action");

            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Matiere old = MatiereDAO.getMatiereById(id);

                if (old != null) {
                    // Supprimer toutes les notes liées à cette matière
                    NoteDAO.supprimerParMatiere(old.getNumMatiere());

                    // Supprimer la matière
                    MatiereDAO.supprimerMatiere(id);

                    AuditDAO.ajouterAudit(
                        "DELETE",
                        "matiere",
                        "NumMatiere=" + old.getNumMatiere() + ", Nom=" + old.getNom() + ", Coef=" + old.getCoefficient(),
                        null
                    );
                }

                response.sendRedirect("matiere");
                return;
            }

            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Matiere m = MatiereDAO.getMatiereById(id);

                request.setAttribute("id", m.getId());
                request.setAttribute("num_matiere", m.getNumMatiere());
                request.setAttribute("nom", m.getNom());
                request.setAttribute("coefficient", m.getCoefficient());
            }

            request.setAttribute("listeMatieres", MatiereDAO.getAllMatieres());
            request.getRequestDispatcher("/views/matieres.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idStr = request.getParameter("id");
            String numMatiere = request.getParameter("num_matiere");
            String nom = request.getParameter("nom");
            int coefficient = Integer.parseInt(request.getParameter("coefficient"));

            if (idStr == null || idStr.isEmpty()) {
                // INSERT
                Matiere m = new Matiere();
                m.setNumMatiere(numMatiere);
                m.setNom(nom);
                m.setCoefficient(coefficient);

                MatiereDAO.ajouterMatiere(m);

                AuditDAO.ajouterAudit(
                    "INSERT",
                    "matiere",
                    null,
                    "NumMatiere=" + numMatiere + ", Nom=" + nom + ", Coef=" + coefficient
                );

            } else {
                // UPDATE
                int id = Integer.parseInt(idStr);
                Matiere old = MatiereDAO.getMatiereById(id);

                Matiere updated = new Matiere();
                updated.setId(id);
                updated.setNumMatiere(numMatiere);
                updated.setNom(nom);
                updated.setCoefficient(coefficient);

                MatiereDAO.modifierMatiere(updated);

                // Mettre à jour les notes si le numéro de matière a changé
                if (!old.getNumMatiere().equals(numMatiere)) {
                    NoteDAO.modifierMatiere(old.getNumMatiere(), numMatiere);
                }

                AuditDAO.ajouterAudit(
                    "UPDATE",
                    "matiere",
                    "NumMatiere=" + old.getNumMatiere() + ", Nom=" + old.getNom() + ", Coef=" + old.getCoefficient(),
                    "NumMatiere=" + numMatiere + ", Nom=" + nom + ", Coef=" + coefficient
                );
            }

            response.sendRedirect("matiere");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}