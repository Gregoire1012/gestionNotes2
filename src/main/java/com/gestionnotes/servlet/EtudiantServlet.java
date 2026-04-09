package com.gestionnotes.servlet;

import com.gestionnotes.dao.EtudiantDAO;
import com.gestionnotes.dao.NoteDAO;
import com.gestionnotes.dao.AuditDAO;
import com.gestionnotes.model.Etudiant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/etudiant")
public class EtudiantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Etudiant old = EtudiantDAO.getById(id);

                if (old != null) {
                    // Supprimer toutes les notes liées
                    NoteDAO.supprimerParEtudiant(old.getMatricule());

                    // Supprimer l'étudiant
                    EtudiantDAO.supprimerEtudiant(id);

                    // Audit
                    AuditDAO.ajouterAudit(
                        "DELETE",
                        "etudiant",
                        "Matricule=" + old.getMatricule() + ", Nom=" + old.getNom() + ", Prenom=" + old.getPrenom() + ", Moyenne=" + old.getMoyenne(),
                        null
                    );
                }

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Etudiant e = EtudiantDAO.getById(id);

                request.setAttribute("id", e.getId());
                request.setAttribute("matricule", e.getMatricule());
                request.setAttribute("nom", e.getNom());
                request.setAttribute("prenom", e.getPrenom());
                request.setAttribute("moyenne", e.getMoyenne());
            }

            request.setAttribute("listeEtudiants", EtudiantDAO.getAllEtudiants());
            request.getRequestDispatcher("/views/etudiants.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                ? Integer.parseInt(request.getParameter("id")) : 0;
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        double moyenne = Double.parseDouble(request.getParameter("moyenne"));

        try {
            if (id == 0) {
                // INSERT
                Etudiant e = new Etudiant(0, matricule, nom, prenom, moyenne);
                EtudiantDAO.ajouterEtudiant(e);

                AuditDAO.ajouterAudit(
                    "INSERT",
                    "etudiant",
                    null,
                    "Matricule=" + matricule + ", Nom=" + nom + ", Prenom=" + prenom + ", Moyenne=" + moyenne
                );

            } else {
                // UPDATE
                Etudiant old = EtudiantDAO.getById(id);

                Etudiant updated = new Etudiant(id, matricule, nom, prenom, moyenne);
                EtudiantDAO.modifierEtudiant(updated);

                // Mettre à jour les notes si le matricule a changé
                if (!old.getMatricule().equals(matricule)) {
                    NoteDAO.modifierMatriculeEtudiant(old.getMatricule(), matricule);
                }

                AuditDAO.ajouterAudit(
                    "UPDATE",
                    "etudiant",
                    "Matricule=" + old.getMatricule() + ", Nom=" + old.getNom() + ", Prenom=" + old.getPrenom() + ", Moyenne=" + old.getMoyenne(),
                    "Matricule=" + matricule + ", Nom=" + nom + ", Prenom=" + prenom + ", Moyenne=" + moyenne
                );
            }

            response.sendRedirect(request.getContextPath() + "/etudiant");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}