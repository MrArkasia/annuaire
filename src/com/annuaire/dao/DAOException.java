package com.annuaire.dao;
/**
 * 
 * @author michaelplong
 *
 */
public class DAOException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param message
	 */
    public DAOException( String message ) {
        super( message );
    }
    
	/**
	 * 
	 * @param message
	 * @param cause
	 */
    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * 
     * @param cause
     */
    public DAOException( Throwable cause ) {
        super( cause );
    }
}