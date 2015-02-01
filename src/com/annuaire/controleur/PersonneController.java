package com.annuaire.controleur;

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
import com.annuaire.forms.ConnexionForm;
import com.annuaire.forms.CreationPersonneBootstrap;

@Controller()
@RequestMapping("/my")
public class PersonneController {
	
	public static final String PARAM_ID_CLIENT = "idPersonne";
    public static final String SESSION_CLIENTS = "personnes";
    public static final String ATT_CLIENT      = "personne";
    public static final String ATT_FORM        = "form";
    public static final String ATT_USER        = "utilisateur";
    public static final String ATT_SESSION_USER= "sessionPersonne";
    public static final String ATT_SESSION_ADMIN="sessionAdmin";
	@EJB
    private PersonneDao personneDao;

    protected final Log logger = LogFactory.getLog(getClass());
    
    CreationPersonneBootstrap bootstrap = new CreationPersonneBootstrap();

    /**************************** CREER PERSONNE *****************************/

    @RequestMapping(value = "/creationPersonne", method = RequestMethod.GET)
    public String creerPersonneForm(HttpServletRequest request, HttpServletResponse response) {
        return "creerPersonne";
    }

    @RequestMapping(value = "/creationPersonne", method = RequestMethod.POST)
    public String creerPersonne(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
	    return bootstrap.ajouterPersonne(request, personneDao);
	}

    /**************************** LISTE PERSONNE *****************************/
    
    @RequestMapping(value = "/listePersonnes", method = RequestMethod.GET)
    public String listPersonne() {
        return "listerPersonnes";
    }
    
    /************************ SUPPRIMER PERSONNE *****************************/

    @RequestMapping(value = "/suppressionPersonne", method = RequestMethod.GET)
    public String suppressionPersonne(HttpServletRequest request, HttpServletResponse response) {
        return bootstrap.supprimerPersonne(request, personneDao);
    }

    /**************************** FICHE PERSONNE *****************************/
    
    @RequestMapping(value = "/afficherPersonne", method = RequestMethod.GET)
    public String detailPersonne(HttpServletRequest request, HttpServletResponse response) {
    	return bootstrap.trouverPersonne(request, personneDao, "afficherPersonne");
    }
    
    /******************************* CONNEXION *******************************/
    
    @RequestMapping(value = "/connexion", method = RequestMethod.POST)
    public String connexionPersonne(HttpServletRequest request, HttpServletResponse response) {

    	HttpSession session = request.getSession();

    	/* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Personne utilisateur = form.connecterPersonne( request );
        
        /*
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Personne à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        return "listerPersonnes";
    }
    
    /****************************** DECONNEXION ******************************/
    
    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String deconnexionPersonne(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	session.setAttribute( ATT_SESSION_USER , null );
    	session.setAttribute( ATT_SESSION_ADMIN, null );
    	
    	return "listerPersonnes";
    }
    
    /********************* RECUPERATION DE MOT DE PASSE **********************/
    
    @RequestMapping(value = "recuperationMotPasse", method = RequestMethod.GET)
    public String recuperationMotPasseGet(HttpServletRequest request, HttpServletResponse response) {

    	return "recuperationMotPasse";
    }
    
    @RequestMapping(value = "recuperationMotPasse", method = RequestMethod.POST)
    public String recuperationMotPassePost(HttpServletRequest request, HttpServletResponse response) {

    	return "recuperationMotPasse";
    }
    

    /*************************** MODIFIER PERSONNE ***************************/
      
    String idtmp;
    
    @RequestMapping(value = "/modifierPersonne", method = RequestMethod.GET)
    public String modifierPersonneGet(HttpServletRequest request, HttpServletResponse response) {
    	idtmp = request.getParameter("idPersonne");
    	return bootstrap.trouverPersonne(request, personneDao, "modifierPersonne");
    }
    
    @RequestMapping(value = "/modifierPersonne", method = RequestMethod.POST)
    public String modifierPersonnePost(HttpServletRequest request, HttpServletResponse response) {
    	return bootstrap.modifierPersonne(request, personneDao, idtmp);
    }

}