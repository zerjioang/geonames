/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.model.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author .local
 */
public class DBManager {
    
    private static final String PERSISTENCE_UNIT_NAME = "sherpa.challenge.jpa";
    private static EntityManagerFactory factory;
    private final EntityManager em;
    
    private static DBManager instance;

    private DBManager() {
        factory = Persistence.createEntityManagerFactory( "sherpa.challenge.jpa" );
        em = factory.createEntityManager();
    }
    
    public static DBManager getInstance(){
        if(instance==null)
            instance = new DBManager();
        return instance;
    }
    
    public void begin(){
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();
    }
    
    public void finish(){
        // Commit the transaction, which will cause the entity to
        // be stored in the database
        em.getTransaction().commit();

        // It is always good practice to close the EntityManager so that
        // resources are conserved.
        em.close();
    }
    
    public void save(Object o){
        em.persist(o);
    }
}
