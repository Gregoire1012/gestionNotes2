package com.gestionnotes.servlet;

import com.gestionnotes.dao.AuditDAO;
import com.gestionnotes.model.Audit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/audit")
public class AuditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les filtres depuis la requête (optionnel)
        String utilisateur = request.getParameter("utilisateur"); // correspond à table_name si tu veux
        String typeOp = request.getParameter("typeOp"); // correspond à operation
        String date = request.getParameter("date"); // yyyy-MM-dd

        // Récupérer la liste depuis DAO
        List<Audit> liste = AuditDAO.getAll(utilisateur, typeOp, date);

        // Passer à la JSP
        request.setAttribute("listeAudit", liste);

        request.getRequestDispatcher("/views/audit.jsp").forward(request, response);
    }
}