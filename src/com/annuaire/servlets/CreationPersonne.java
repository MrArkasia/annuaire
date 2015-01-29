package com.annuaire.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;
import com.annuaire.forms.CreationPersonneForm;

@WebServlet( urlPatterns = { "/creationPersonne" }, initParams = @WebInitParam( name = "chemin", value = "/fichiers/images/" ) )
@MultipartConfig( location = "/tmp", maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class CreationPersonne extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//public static final String CHEMIN          = "chemin";
    public static final String ATT_CLIENT      = "personne";
    public static final String ATT_FORM        = "form";
    public static final String SESSION_CLIENTS = "personnes";

    public static final String VUE_SUCCES      = "/WEB-INF/jsp/afficherPersonne.jsp";
    public static final String VUE_FORM        = "/WEB-INF/jsp/creerPersonne.jsp";

    @EJB
    private PersonneDao personneDao;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* � la r�ception d'une requ�te GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Lecture du param�tre 'chemin' pass� � la servlet via la d�claration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( null );

        /* Pr�paration de l'objet formulaire */
        CreationPersonneForm form = new CreationPersonneForm( personneDao );

        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
        Personne personne = form.creerPersonne( request, chemin );

        /* Ajout du bean et de l'objet m�tier � l'objet requ�te */
        request.setAttribute( ATT_CLIENT, personne );
        request.setAttribute( ATT_FORM, form );

        /* Si aucune erreur */
        if ( form.getErreurs().isEmpty() ) {
            /* Alors r�cup�ration de la map des personnes dans la session */
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
			Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( personnes == null ) {
                personnes = new HashMap<Long, Personne>();
            }
            /* Puis ajout du personne courant dans la map */
            personnes.put( personne.getId(), personne );
            /* Et enfin (r�)enregistrement de la map en session */
            session.setAttribute( SESSION_CLIENTS, personnes );

            /* Affichage de la fiche r�capitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {
            /* Sinon, r�-affichage du formulaire de cr�ation avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}