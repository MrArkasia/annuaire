package com.annuaire.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( urlPatterns = { "/listePersonnes" } )
public class ListePersonnes extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public static final String VUE        = "/WEB-INF/jsp/listerPersonnes.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des personnes */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}