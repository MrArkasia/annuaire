package com.annuaire.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.annuaire.dao.DAOException;
import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;

public class CreationPersonneBootstrap {

	public static final String PARAM_ID_CLIENT = "idPersonne";
    public static final String SESSION_CLIENTS = "personnes";
    public static final String ATT_CLIENT      = "personne";
    public static final String ATT_FORM        = "form";

    /*********************************** AJOUTER PERSONNE ****************************************/
    
	public String ajouterPersonne(HttpServletRequest request, PersonneDao personneDao) {
		CreationPersonneForm form = new CreationPersonneForm( personneDao );
	    Personne personne = form.creerPersonne( request );
	    System.out.println(request.getSession().getAttribute("nomPersonne"));
	
	    request.setAttribute( ATT_CLIENT, personne );
	    request.setAttribute( ATT_FORM, form );

	    if ( form.getErreurs().isEmpty() ) {
	        HttpSession session = request.getSession();
	        @SuppressWarnings("unchecked")
			Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	       
	        if ( personnes == null ) {
	            personnes = new HashMap<Long, Personne>();
	        }
	        personnes.put( personne.getId(), personne );
	        session.setAttribute( SESSION_CLIENTS, personnes );
	        
	        return "listerPersonnes";
	       
	    } else {
	    	return "creerPersonne";
	    }
	}
	
	/*********************************** TROUVER PERSONNE ****************************************/
	
	public String trouverPersonne(HttpServletRequest request, PersonneDao personneDao, String page) {
		Personne personne = null;
		String idPersonne = getValeurParametre( request, PARAM_ID_CLIENT );
	    Long id = Long.parseLong( idPersonne );
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
		Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	    
	    if ( id != null && personnes != null ) {
	        try {
	             personne = personneDao.trouver( personnes.get( id ).getId() );
	             request.setAttribute( ATT_CLIENT, personne );
	        } catch ( DAOException e ) {
	            e.printStackTrace();
	        } 
	    }
	    if(page.equals("modifierPersonne"))	
	    	return "modifierPersonne";
	    else if(page.equals("afficherPersonne"))
	    	return "afficherPersonne";
	    return "listerPersonnes";
	}
	

	/********************************** SUPPRIMER PERSONNE ***************************************/
	
	public String supprimerPersonne(HttpServletRequest request, PersonneDao personneDao) {
		String idPersonne = getValeurParametre( request, PARAM_ID_CLIENT );
	    Long id = Long.parseLong( idPersonne );
	
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
		Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	
	    if ( id != null && personnes != null ) {
	        try { 
	            personneDao.supprimer( personnes.get( id ) );
	            personnes.remove( id );
	        } catch ( DAOException e ) {
	            e.printStackTrace();
	        }
	        session.setAttribute( SESSION_CLIENTS, personnes );
	    }
	    return "listerPersonnes";
	}
	
	/********************************** MODIFIER PERSONNE ***************************************/
	
	public String modifierPersonne(HttpServletRequest request, PersonneDao personneDao, String idtmp) {
		String idPersonne = idtmp;
	    Long id = Long.parseLong( idPersonne );
	
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
		Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	
	    if ( id != null && personnes != null ) {
	        try { 
	            personneDao.supprimer( personnes.get( id ) );
	            personnes.remove( id );
	        } catch ( DAOException e ) {
	            e.printStackTrace();
	        }
	        session.setAttribute( SESSION_CLIENTS, personnes );
	    }

	    CreationPersonneForm form = new CreationPersonneForm( personneDao );
	    Personne personne = form.creerPersonne( request );
	    System.out.println(request.getSession().getAttribute("nomPersonne"));
	
	    request.setAttribute( ATT_CLIENT, personne );
	    request.setAttribute( ATT_FORM, form );
	
	    if ( form.getErreurs().isEmpty() ) {
	        session = request.getSession();
	        @SuppressWarnings("unchecked")
			Map<Long, Personne> personness = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	       
	        if ( personness == null ) {
	            personness = new HashMap<Long, Personne>();
	        }
	        personness.put( personne.getId(), personne );
	        session.setAttribute( SESSION_CLIENTS, personness );
	        
	        return "listerPersonnes";
	       
	    } else {
	    	return "creerPersonne";
	    }

	}

	/************************************* UTILITAIRE ******************************************/	
	
	//Méthode utilitaire qui retourne null si un paramètre est vide, et son contenu sinon.
    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
 
}
