package com.gestionnotes.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Affichage de la page login
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    // Traitement du formulaire
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des données
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérification simple (à remplacer par base de données plus tard)
        if ("admin".equals(username) && "1234".equals(password)) {

            // Création de session
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // Redirection vers page principale
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } else {
            // Message d'erreur
            request.setAttribute("erreur", "Nom d'utilisateur ou mot de passe incorrect !");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}