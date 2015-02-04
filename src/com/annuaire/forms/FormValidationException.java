package com.annuaire.forms;

/**
 * Classe de validation pour le formulaire de creation de personne
 * 
 * @author Amaury Doudement
 * @author Michael Plong
 *
 */

public class FormValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur
	 * 
	 * @param message
	 */
    public FormValidationException( String message ) {
        super( message );
    }
}
