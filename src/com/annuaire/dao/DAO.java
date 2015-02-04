package com.annuaire.dao;

import java.util.List;

import com.annuaire.entities.Personne;

/**
 * Interface DAO
 * 
 * @author Amaury Doudement
 * @author Michael Plong
 *
 */

public interface DAO {
	
	
    /**
	 * Methode de recherche de personne
	 * @param id
	 * identifiant de la personne
	 * @throws DAOException
	 * @see #DAOException( String message )
	 */ 
	public Personne trouver( long id );
	
	/**
	 * Méthode de creation des personnes
	 * @param personne
	 * @throws DAOException
	 * @see #DAOException( String message )
	 */
	public void creer( Personne personne );
	
	
    /**
     * Methode pour obtenir la liste des personnes
     * @return
     * @throws DAOException
     * @see #DAOException( String message )
     */
	public List<Personne> lister();
	
    /**
     * Methode de suppression des personnes
     * @param personne
     * @throws DAOException
     * @see #DAOException( String message )
     */
	public void supprimer( Personne personne );

}
