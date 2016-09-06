/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.test;

import com.sherpa.challenge.model.db.Detalle;
import com.sherpa.challenge.model.db.Master;
import com.sherpa.challenge.model.http.GeoRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author .local
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenceTest {

    private EntityManager entityManager;
    
    @Before
    public void _0_dbTest(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "sherpa.challenge.jpa" );
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    @Test
    public void _1_insertTest(){
        
        System.out.println("Insert one element in each table");
        
        GeoRequest request = new GeoRequest("asergio", "48003");
        request.setCity("Pais Vasco");
                
        Detalle d = new Detalle();
        Master m = new Master();
        
        //detalle data
        d.setCity(request.getCity());
        d.setZipcode(String.valueOf(request.getZipCode()));
        d.addMaster(m);
        
        //master data
        m.setUsername(request.getUsername());
        m.setDetalle(d);
        
        entityManager.getTransaction().begin();
        
        entityManager.persist(m);
        entityManager.persist(d);
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    @Test
    public void _2_selectAllMaster(){
        
        //ables are previously empty
        Query q = entityManager.createQuery("SELECT x FROM Master x");
        List<Master> results = (List<Master>) q.getResultList();
        System.out.println("Select Master Results: "+results.size());
        
        for(Master m : results){
            System.out.println(m.getUsername());
        }
        
        TestCase.assertEquals(results.size(), 1);
    }
    
    @Test
    public void _3_selectAllDetalle(){
        
        //ables are previously empty
        Query q = entityManager.createQuery("SELECT x FROM Detalle x");
        List<Detalle> results = (List<Detalle>) q.getResultList();
        System.out.println("Select detalle Results: "+results.size());
        
        for(Detalle m : results){
            System.out.println(m.getCity());
        }
        
        TestCase.assertEquals(results.size(), 1);
    }
    
    @Test
    public void _4_removeAllMaster(){
        
        //delete existing data
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Master x");
        
        //remove all
        q.executeUpdate();
        
        q = entityManager.createQuery("SELECT x FROM Master x");
        List<Master> results = (List<Master>) q.getResultList();
        System.out.println("Master Results: "+results.size());
        
        entityManager.getTransaction().commit();
        entityManager.close();
        
        TestCase.assertEquals(results.size(), 0);
    }
    
    @Test
    public void _5_removeAllDetalle(){
        
        //delete existing data
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Detalle x");
        
        //remove all
        q.executeUpdate();
        
        q = entityManager.createQuery("SELECT x FROM Detalle x");
        List<Master> results = (List<Master>) q.getResultList();
        System.out.println("Detalle Results: "+results.size());
        
        entityManager.getTransaction().commit();
        entityManager.close();
        
        TestCase.assertEquals(results.size(), 0);
    }
}
