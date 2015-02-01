package com.annuaire.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.annuaire.entities.Personne;

/**
 * 
 * Classe PersonneDao
 * @author amaury doudement michaelplong
 *
 */
@Stateless
public class PersonneDao {

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "myJta" )
    private EntityManager em;
	
    /**
	 * Methode de recherche des personnes
	 * @param id
	 * identifiant de la personne
	 * @throws DAOException
	 * @see #DAOException( String message )
	 */
    public Personne trouver( long id ) throws DAOException {
        try {
            return em.find( Personne.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
	/**
	 * Méthode de creation des personnes
	 * @param personne
	 * @throws DAOException
	 * @see #DAOException( String message )
	 */
    public void creer( Personne personne ) throws DAOException {
        try {
            em.persist( personne );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Méthode de listage des personnes
     * @return
     * @throws DAOException
     * @see #DAOException( String message )
     */
    public List<Personne> lister() throws DAOException {
        try {
            TypedQuery<Personne> query = em.createQuery( "SELECT c FROM Personne c ORDER BY c.nom", Personne.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Methode de suppression des personnes
     * @param personne
     * @throws DAOException
     * @see #DAOException( String message )
     */
    public void supprimer( Personne personne ) throws DAOException {
        try {
            em.remove( em.merge( personne ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}