package com.annuaire.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.annuaire.entities.Personne;

@Stateless
@LocalBean()
@Startup
public class PersonneDao {

    @PersistenceContext( unitName = "myJta" )
    private EntityManager em;

    public Personne trouver( long id ) throws DAOException {
        try {
            return em.find( Personne.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void creer( Personne personne ) throws DAOException {
        try {
            em.persist( personne );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public List<Personne> lister() throws DAOException {
        try {
            TypedQuery<Personne> query = em.createQuery( "SELECT c FROM Personne c ORDER BY c.nom", Personne.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void supprimer( Personne personne ) throws DAOException {
        try {
            em.remove( em.merge( personne ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

}