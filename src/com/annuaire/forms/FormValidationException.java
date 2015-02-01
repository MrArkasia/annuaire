package com.annuaire.forms;

public class FormValidationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur
	 * @param message
	 */
    public FormValidationException( String message ) {
        super( message );
    }
}
