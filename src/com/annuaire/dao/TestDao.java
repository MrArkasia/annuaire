package com.annuaire.dao;

import static org.junit.Assert.*;

import javax.naming.InitialContext;

import junit.framework.Assert;

import org.apache.openejb.core.ivm.naming.NamingException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.annuaire.entities.Personne;

/**
 * Classe TestDao
 * 
 * @author amaury doudement michaelplong
 *
 */
public class TestDao {

   static PersonneDao dao;
   Personne p;


   @Before
   public void setUp() {
      // pour plus tard
   }

	@AfterClass
	public static void Tear_Down() {
		System.out.println("Cloture de la phase de test");
	}
/**
 * 
 * Test de GetNom
 */
   @Test
   public void testGetNom(){
	   p = new Personne();
	   p.setNom("toto");
	   assertNotNull(p.getNom());
   }
   
   /**
    * 
    * Test de GetPrenom
    */
   @Test
   public void testGetPrenom(){
	   p = new Personne();
	   p.setPrenom("toto");
	   assertNotNull(p.getPrenom());
   }
   
   /**
    * 
    * Test non null
    */
   @Test
	public void testNonNull(){
	   p = new Personne();
	   p.setNom("toto");
		assertNotNull(p);	
	}
   
   /**
    * 
    * Test de GetMp
    */
   @Test
   public void testGetMp(){
	   p = new Personne();
	   p.setMp("password");
	   assertNotNull(p.getMp());
   }
   
    
    @Test
    public void testGetEmail(){
 	   p = new Personne();
 	   p.setEmail("toto@mail.com");
 	   assertNotNull(p.getEmail());
    }
   
   
   @Test
   public void testReadAccess() throws Exception {
	    Runnable execution = new Runnable() {
	        public void run() {
	        	 // prepare client context
	            InitialContext context = null;
				try {
					context = new InitialContext();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (javax.naming.NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // lookup EJB
	            Object ref = null;
				try {
					ref = context.lookup("storageLocalBean");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (javax.naming.NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            // test and use it
	            Assert.assertTrue(ref instanceof PersonneDao);
	            Assert.assertNotNull(dao.trouver(4));
	        }
	    };
	    


}}