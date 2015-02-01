package com.annuaire.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * La classe personne
 * @author michaelplong amaury doudement
 *
 */
@Entity
public class Personne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )

	/**
     * L'ID de la personne. Cet ID est modifiable.
     * 
     * @see #setId(Long id)
     * @see #getId()
     */
    private Long   id;
	
	/**
     * Le nom de la personne. Ce nom est modifiable.
     * 
     * @see #setNom(String nom)
     * @see #getNom()
     */
    private String nom;
    
    /**
     * Le prenom de la personne. Ce prenom est modifiable.
     * 
     * @see #setPrenom(String prenom)
     * @see #getPrenom()
     */
    private String prenom;
    
    /**
     * L'email de la personne. L'email est modifiable.
     * 
     * @see #setEmail(String email)
     * @see #getEmail()
     */
    private String email;
    
    /**
     * Le site Web de la personne. Le site Web est modifiable.
     * 
     * @see #setSiteWeb(String siteWeb)
     * @see #getSiteWeb()
     */
    private String siteWeb;
    
    /**
     * La Date de Naissance de la personne. La Date de Naissance est modifiable.
     * 
     * @see #setDateNaissance(String DateNaissance)
     * @see #getDateNaissance()
     */
    private String dateNaissance;
    
    private String groupe;
    
    /**
     * Le mot de passe de la personne. Le MP est modifiable.
     * 
     * @see #setMp(String mp)
     * @see #getMp()
     */
    private String mp;
    
    /**
    * Met à jour l'Id de la personne.
    * @param id
    * Le nouveau Id de la personne.
    */
	public void setId( Long id ) {
        this.id = id;
    }

	/**
     * Retourne l'Id de la personne.
     * 
     * @return id, qui correspond à l'identifiant d'une personne.
     */
    public Long getId() {
        return id;
    }

    /**
     * Met à jour le nom de la personne.
     * @param nom
     * Le nouveau nom de la personne.
     */
    public void setNom( String nom ) {
        this.nom = nom;
    }
    
    /**
     * Retourne le nom de la personne.
     * 
     * @return nom, qui correspond au nom d'une personne.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met a jour le prenom de la personne.
     * @param prenom
     * Le nouveau prenom de la personne.
     */
    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    /**
     * Retourne le prenom de la personne.
     * 
     * @return prenom, qui correspond au prenom d'une personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Met a jour l'email de la personne.
     * @param email
     * Le nouveau email de la personne.
     */
    public void setEmail( String email ) {
        this.email = email;
    }
    
    /**
     * Retourne l'email de la personne.
     * 
     * @return email, qui correspond a l'email d'une personne.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Retourne le mot de passel de la personne.
     * 
     * @return mp, qui correspond au mot de passe de la personne.
     */
    public String getMp() {
		return mp;
	}

    /**
     * Met a jour le mot de passe de la personne.
     * @param mp
     * Le nouveau mot de passe de la personne.
     */
	public void setMp(String mp) {
		this.mp = mp;
	}

	/**
     * Retourne le site web de la personne.
     * 
     * @return siteWeb, qui correspond au site web de la personne.
     */
	public String getSiteWeb() {
		return siteWeb;
	}

	/**
     * Met a jour le site web de la personne.
     * @param siteWeb
     * Le nouveau site web de la personne.
     */
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	
	/**
     * Retourne la date de naissance de la personne.
     * 
     * @return dateNaissance, qui correspond a la date de naissance de la personne.
     */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
     * Met a jour la date de naissance de la personne.
     * @param dateNaissance
     * La nouvelle date de naissance de la personne.
     */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	/**
	 * 
	 *  Retourne le groupe de la personne.
	 * 
	 * @return groupe,qui correspond au groupe de la personne.
	 */
	public String getGroupe() {
		return groupe;
	}

	/**
	 * 
	 * Met a jour le groupe de la personne.
	 * 
	 * @param groupe
	 * Le nouveau groupe de la personne.
	 */
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

}