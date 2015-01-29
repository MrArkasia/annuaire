package com.annuaire.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.annuaire.dao.DAOException;
import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;

@WebServlet( urlPatterns = { "/suppressionPersonne" } )
public class SuppressionPersonne extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PARAM_ID_CLIENT = "idPersonne";
    public static final String SESSION_CLIENTS = "personnes";

    public static final String VUE             = "/listePersonnes";

    @EJB
    private PersonneDao          personneDao;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
        /* Récupération du paramètre */
        String idPersonne = getValeurParametre( request, PARAM_ID_CLIENT );
        Long id = Long.parseLong( idPersonne );

        /* Récupération de la Map des personnes enregistrés en session */
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
		Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );

        /* Si l'id du personne et la Map des personnes ne sont pas vides */
        if ( id != null && personnes != null ) {
            try {
                /* Alors suppression du personne de la BDD */
                personneDao.supprimer( personnes.get( id ) );
                /* Puis suppression du personne de la Map */
                personnes.remove( id );
            } catch ( DAOException e ) {
                e.printStackTrace();
            }
            /* Et remplacement de l'ancienne Map en session par la nouvelle */
            session.setAttribute( SESSION_CLIENTS, personnes );
        }

        /* Redirection vers la fiche récapitulative */
        response.sendRedirect( request.getContextPath() + VUE );
    }

    /*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * contenu sinon.
     */
    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}