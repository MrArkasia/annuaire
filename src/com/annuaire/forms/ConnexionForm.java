package com.annuaire.forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.annuaire.entities.Personne;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    public static final String SESSION_CLIENTS = "personnes";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

	/**
	 * 
	 * @return resultat
	 */
    public String getResultat() {
        return resultat;
    }
    
    /**
     * 
     * @return erreur
     */
    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    /**
     * Permet la connection d'une personne 
     * 
     * @param request
     * @return utilisteur, retourne la personne qui s'est connecter
     */
    public Personne connecterPersonne( HttpServletRequest request ) {
        /* R�cup�ration des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Personne utilisateur = new Personne();

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse (motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMp( motDePasse );
        
        /* Validation de la presence en base. */
        try {
            validationPresence( email , motDePasse, request  );
        } catch ( Exception e ) {
        	setErreur( CHAMP_EMAIL, e.getMessage());
            setErreur( CHAMP_PASS, e.getMessage() );
        }

        /* Initialisation du r�sultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s de la connexion.";
        } else {
            resultat = "�chec de la connexion.";
        }

        return utilisateur;
    }


    /**
     * 
     * Valide l'adresse email saisie.
     * 
     * @param email
     * @throws Exception
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null ){
        	if(!email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) )
        		throw new Exception( "Merci de saisir une adresse mail valide." );		  
        } else {
        	throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    /**
     * 
     * Valide le mot de passe saisie.
     * 
     * @param motDePasse
     * @throws Exception
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caract�res." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /**
     * 
     * Valide la presence en base.
     * 
     * @param email
     * @param motDePasse
     * @param request
     * @throws Exception
     */
    private void validationPresence( String email, String motDePasse, HttpServletRequest request ) throws Exception {
    	
    	boolean trouver		= false;
    	HttpSession session = request.getSession();
    	
	    @SuppressWarnings("unchecked")
		Map<Long, Personne> personnes = (HashMap<Long, Personne>) session.getAttribute( SESSION_CLIENTS );
	    
		@SuppressWarnings("rawtypes")
		Set listKeys = personnes.keySet();

		if(email.equals("admin@mail.com") && motDePasse.equals("password")){
			Personne admin = new Personne();
			admin.setEmail("admin@mail.com");
			admin.setNom("Administrateur");
			session.setAttribute("sessionAdmin", admin);
			session.setAttribute("sessionId", "-1");
			trouver = true;
		} 
		else {
			@SuppressWarnings("rawtypes")
			Iterator iterateur = listKeys.iterator();
			while(iterateur.hasNext()) {
				Object key= iterateur.next();
				if( personnes.get(key).getEmail().equals(email) && personnes.get(key).getMp().equals(motDePasse) ){
					trouver = true;
					session.setAttribute("sessionId", key);
				}
			}
			if(!trouver){
	    		throw new Exception( "L'email et/ou le mot de passe sont incorrecte" );
			}
		}
    }


    /**
     * Ajoute un message correspondant au champ specifique a la map des erreurs.
     * @param champ
     * @param message
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }


    /**
     * 
     * Methode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     * @param request
     * @param nomChamp
     * @return
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
