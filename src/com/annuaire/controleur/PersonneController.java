package com.annuaire.controleur;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.annuaire.dao.DAOException;
import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;
import com.annuaire.forms.CreationPersonneForm;

@Controller()
@RequestMapping("/my")
public class PersonneController {
	
	
	public static final String PARAM_ID_CLIENT = "idPersonne";
    public static final String SESSION_CLIENTS = "personnes";
    public static final String ATT_CLIENT      = "personne";
    public static final String ATT_FORM        = "form";
	
	@EJB
    private PersonneDao personneDao;

    protected final Log logger = LogFactory.getLog(getClass());

    
    /**************************** CREER PERSONNE ***************************/

    @RequestMapping(value = "/creationPersonne", method = RequestMethod.GET)
    public String creerPersonneForm(HttpServletRequest request, HttpServletResponse response) {
        return "creerPersonne";
    }

    @RequestMapping(value = "/creationPersonne", method = RequestMethod.POST)
    public String creerPersonne(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
    	
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
	        
	        System.out.println("Creation personne OK");
	        return "listerPersonnes";
	       
	    } else {
	    	System.out.println("ERREUR - Il y a " +  form.getErreurs().size() + " erreur(s) pour la creation d'une personne");
	    	return "creerPersonne";
	    }
	}

    /**************************** LISTE PERSONNE ****************************/
    
    @RequestMapping(value = "/listePersonnes", method = RequestMethod.GET)
    public String listPersonne() {
        return "listerPersonnes";
    }
    
    
    /************************ SUPPRIMER PERSONNE ****************************/
    
    
    @RequestMapping(value = "/suppressionPersonne", method = RequestMethod.GET)
    public String suppressionPersonne(HttpServletRequest request, HttpServletResponse response) {
    	
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


    /**************************** FICHE PERSONNE ****************************/
    
    @RequestMapping(value = "/afficherPersonne", method = RequestMethod.GET)
    
    public String detailPersonne(HttpServletRequest request, HttpServletResponse response) {
    	
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
    	
        //TODO
    	
        return "afficherPersonne";
    }
    
    /******************************* CONNEXION ******************************/
    
    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String connexionPersonne() {
        return "afficherPersonne";
    }

    /************************************************************************/
    
    
    @ModelAttribute("groupe_personne")
    public Map<String, String> productTypes() {
        Map<String, String> types = new LinkedHashMap<String, String>();
        types.put("type1", "Type 1");
        types.put("type2", "Type 2");
        types.put("type3", "Type 3");
        types.put("type4", "Type 4");
        types.put("type5", "Type 5");
        return types;
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