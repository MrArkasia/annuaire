package com.annuaire.dao;
/**
 * 
 * Une classe d exception pour les fonctions liees au DAO
 * @author amaury doudement michaelplong
 *
 */
public class DAOException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur DAOException avec un message detaille.
	 * @param message
	 * Message detaille
	 * 
	 */
    public DAOException( String message ) {
        super( message );
    }
    
	/**
	 * 
	 * Construicteur DAOException qui est la cause de cette exception avec le message detaille.
	 * @param message
	 * message decrivant le probleme. Doit avoir un contenu.
	 * @param cause
	 * cause sous-jacente du probleme.
	 * 
	 */
    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * constructeur DAOException avec l objet Throwable
     * @param cause
     * objet Throwable
     */
    public DAOException( Throwable cause ) {
        super( cause );
    }
}