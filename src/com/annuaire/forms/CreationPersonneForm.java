package com.annuaire.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.annuaire.dao.DAOException;
import com.annuaire.dao.PersonneDao;
import com.annuaire.entities.Personne;

public final class CreationPersonneForm {
    private static final String CHAMP_NOM       = "nomPersonne";
    private static final String CHAMP_PRENOM    = "prenomPersonne";
    private static final String CHAMP_EMAIL     = "emailPersonne";
    private static final String CHAMP_MP     	= "mpPersonne";
    private static final String CHAMP_SITE     	= "siteWebPersonne";
    private static final String CHAMP_NAISSANCE	= "naissancePersonne";

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();
    private PersonneDao         personneDao;

    public CreationPersonneForm( PersonneDao personneDao ) {
        this.personneDao = personneDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Personne creerPersonne( HttpServletRequest request) {
    	
        String nom 			= getValeurChamp( request, CHAMP_NOM );
        String prenom 		= getValeurChamp( request, CHAMP_PRENOM );
        String email 		= getValeurChamp( request, CHAMP_EMAIL );
        String mp 			= getValeurChamp( request, CHAMP_MP );
        String site 		= getValeurChamp( request, CHAMP_SITE );
        String naissance 	= getValeurChamp( request, CHAMP_NAISSANCE );
        
        System.out.println("---------------------------");
        System.out.println("Nom       : " + nom);
        System.out.println("Preom     : " + prenom);
        System.out.println("email     : " + email);
        System.out.println("mp        : " + mp);
        System.out.println("site      : " + site);
        System.out.println("naissance : " + naissance);
        System.out.println("---------------------------");
        
        Personne personne = new Personne();

        traiterNom( nom, personne );
        traiterPrenom( prenom, personne );
        traiterEmail( email, personne );
        traiterMp( mp, personne );
        traiterSite( site, personne );
        traiterNaissance( naissance, personne );

        try {
            if ( erreurs.isEmpty() ) {
                personneDao.creer( personne );
                resultat = "Succ�s de la cr�ation de la personne.";
            } else {
                resultat = "�chec de la cr�ation de la personne.";
            }
        } catch ( DAOException e ) {
            setErreur( "impr�vu", "Erreur impr�vue lors de la cr�ation." );
            resultat = "�chec de la cr�ation de la personne : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
            e.printStackTrace();
        }

        return personne;
    }
    
    
    /*--------------------------- TRAITEMENTS ----------------------------*/

    
    private void traiterNom( String nom, Personne personne ) {
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        personne.setNom( nom );
    }

    private void traiterPrenom( String prenom, Personne personne ) {
        try {
            validationPrenom( prenom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        personne.setPrenom( prenom );
    }

    private void traiterEmail( String email, Personne personne ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        personne.setEmail( email );
    }
    
    private void traiterMp( String mp, Personne personne ) {
        try {
            validationMp( mp );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MP, e.getMessage() );
        }
        personne.setMp( mp );
    }
    
    private void traiterSite( String site, Personne personne ) {
        try {
            validationSite( site );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_SITE, e.getMessage() );
        }
        personne.setSiteWeb( site );
    }
    
    private void traiterNaissance( String naissance, Personne personne ) {
        try {
            validationNaissance( naissance );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NAISSANCE, e.getMessage() );
        }
        personne.setDateNaissance( naissance );
    }
    
    
    /*------------------------------ VALIDATIONS ------------------------------*/

    
    private void validationNom( String nom ) throws FormValidationException {
    	if ( nom != null && nom.length() < 2 ) {
    		throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 2 caract�res." );
    	}
    }

    private void validationPrenom( String prenom ) throws FormValidationException {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new FormValidationException( "Le pr�nom d'utilisateur doit contenir au moins 2 caract�res." );
        }
    }

    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new FormValidationException( "Merci de saisir une adresse mail valide." );
        }
    }
    
    private void validationMp( String mp ) throws FormValidationException {
        if ( mp != null ) {
            if ( mp.length() < 8 ) {
                throw new FormValidationException( "Le mot de passe doit contenir au moins 8 caract�res." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un mot de passe." );
        }
    }
    
    private void validationSite( String site ) throws FormValidationException {
    	if ( site != null && site.length() < 3 ) {
        	throw new FormValidationException( "L'adresse du site web doit contenir au moins 3 caract�res.." );
        }
    }
    
    private void validationNaissance( String naissance ) throws FormValidationException {
        if ( naissance != null && naissance.length() < 3 ) {
        	throw new FormValidationException( "Merci d'entrer une date de naissance." );
        }
    }
    

    /*------------------------------ GESTIONS ERREURS ------------------------------*/
    

    /*
     * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}