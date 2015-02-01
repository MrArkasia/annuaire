package com.annuaire.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;

@WebFilter( urlPatterns = { "/*" } )
public class PrechargementFilter implements Filter {
	
    public static final String ATT_SESSION_CLIENTS   = "personnes";

    @EJB
    private PersonneDao          personneDao;

    /**
     * 
     */
    public void init( FilterConfig filterConfig ) throws ServletException {
    }
	/**
	 * 
	 */
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast de l'objet request */
        HttpServletRequest request = (HttpServletRequest) req;

        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();

        /*
         * Si la map des personnes n'existe pas en session, alors l'utilisateur se
         * connecte pour la premi�re fois et nous devons pr�charger en session
         * les infos contenues dans la BDD.
         */
        if ( session.getAttribute( ATT_SESSION_CLIENTS ) == null ) {
            /*
             * R�cup�ration de la liste des personnes existants, et enregistrement
             * en session
             */
            List<Personne> listePersonnes = personneDao.lister();
            Map<Long, Personne> mapPersonnes = new HashMap<Long, Personne>();
            for ( Personne personne : listePersonnes ) {
                mapPersonnes.put( personne.getId(), personne );
            }
            session.setAttribute( ATT_SESSION_CLIENTS, mapPersonnes );
        }

        /* Pour terminer, poursuite de la requ�te en cours */
        chain.doFilter( request, res );
    }

    public void destroy() {
    }
}