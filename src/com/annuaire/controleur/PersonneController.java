/**
 * Package contenant le controlleur de l'application
 */
package com.annuaire.controleur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;
import com.annuaire.forms.OutilsController;
/**
 * Controller de l'application
 * @author michaelplong et amaury doudement
 *
 */
@Controller()
@RequestMapping("/my")
public class PersonneController {
	
    public static final String ATT_SESSION_USER= "sessionPersonne";
    public static final String ATT_SESSION_ADMIN="sessionAdmin";
    
	@EJB
    private PersonneDao personneDao;

    protected final Log logger = LogFactory.getLog(getClass());
    
    OutilsController outils = new OutilsController();

    /**************************** CREER PERSONNE *****************************/
   
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return creerPersonne
     */
    @RequestMapping(value = "/creationPersonne", method = RequestMethod.GET)
    public String creerPersonneForm(HttpServletRequest request, HttpServletResponse response) {
        return "creerPersonne";
    }
    
    /**
     * 
     * @param map, Modele utilisé pour les donnees présentées dans la vue. 
     * Permet d’avoir acces aux données contenues dans le modele et de les manipuler.
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/creationPersonne", method = RequestMethod.POST)
    public String creerPersonne(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
	    return outils.ajouterPersonne(request, personneDao);
	}

    /**************************** LISTE PERSONNE *****************************/
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/listePersonnes", method = RequestMethod.GET)
    public String listPersonne() {
        return "listerPersonnes";
    }
    
    /************************ SUPPRIMER PERSONNE *****************************/

    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/suppressionPersonne", method = RequestMethod.GET)
    public String suppressionPersonne(HttpServletRequest request, HttpServletResponse response) {
        return outils.supprimerPersonne(request, personneDao);
    }

    /**************************** FICHE PERSONNE *****************************/
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/afficherPersonne", method = RequestMethod.GET)
    public String detailPersonne(HttpServletRequest request, HttpServletResponse response) {
    	return outils.trouverPersonne(request, personneDao, "afficherPersonne");
    }
    
    /******************************* CONNEXION *******************************/
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String connexionPersonne(HttpServletRequest request, HttpServletResponse response) {
    	return outils.connexion(request, personneDao);
    }
    
    /****************************** DECONNEXION ******************************/
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String deconnexionPersonne(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	session.setAttribute( ATT_SESSION_USER , null );
    	session.setAttribute( ATT_SESSION_ADMIN, null );
    	return "listerPersonnes";
    }
    
    /********************* RECUPERATION DE MOT DE PASSE **********************/
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "recuperationMotPasse", method = RequestMethod.GET)
    public String recuperationMotPasseGet(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	session.setAttribute("notFindMail", null);
    	session.setAttribute("recoveryId", null);
    	return "recuperationMotPasse";
    }
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "recuperationMotPasse", method = RequestMethod.POST)
    public String recuperationMotPassePost(HttpServletRequest request, HttpServletResponse response) {

    	boolean trouver = false;
    	final String SESSION_CLIENTS = "personnes";
    	String email = request.getParameter( "email" );
    	HttpSession session = request.getSession();
    	
    	session.setAttribute("notFindMail", null);
    	session.setAttribute("recoveryId", null);
    	
	    @SuppressWarnings("unchecked")
		Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	    
		@SuppressWarnings("rawtypes")
		Set listKeys = personnes.keySet();
    	
    	@SuppressWarnings("rawtypes")
		Iterator iterateur = listKeys.iterator();
		while(iterateur.hasNext()) {
			Object key= iterateur.next();
			if( personnes.get(key).getEmail().equals(email)){
				trouver = true;
				session.setAttribute("recoveryId", key);
			}
		}
		
		if(!trouver) session.setAttribute("notFindMail", "yes");

    	return "recuperationMotPasse";
    }

    /*************************** MODIFIER PERSONNE ***************************/
      
    String idtmp;
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/modifierPersonne", method = RequestMethod.GET)
    public String modifierPersonneGet(HttpServletRequest request, HttpServletResponse response) {
    	idtmp = request.getParameter("idPersonne");
    	return outils.trouverPersonne(request, personneDao, "modifierPersonne");
    }
    /**
     * 
     * @param request 
     * Requete par l intermediaire de l API Servlet.
     * @param response 
     * Reponse de la requete par l intermediaire de l API Servlet.
     * @return
     */
    @RequestMapping(value = "/modifierPersonne", method = RequestMethod.POST)
    public String modifierPersonnePost(HttpServletRequest request, HttpServletResponse response) {
    	return outils.modifierPersonne(request, personneDao, idtmp);
    }

}