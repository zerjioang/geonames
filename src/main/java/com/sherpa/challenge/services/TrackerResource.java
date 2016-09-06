/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.services;

import com.sherpa.challenge.model.db.DBManager;
import com.sherpa.challenge.model.db.Detalle;
import com.sherpa.challenge.model.db.Master;
import com.sherpa.challenge.model.http.GeoRequest;
import com.sherpa.challenge.util.ApiErrors;
import com.sherpa.challenge.util.JsonConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author .local
 */
@Path("tracker")
public class TrackerResource implements ICityResolverService{

    @Context
    private UriInfo context;
    private DBManager manager;

    /**
     * Creates a new instance of TrackerResource
     */
    public TrackerResource() {
    }
    
    /**
     * Retrieves representation of an instance of com.sherpa.geonames.services.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Tracker resource is up and running!";
    }
    
    /**
     * Retrieves representation of an instance of com.sherpa.geonames.services.GenericResource
     * @param username username
     * @param zipcode usernames zipcode
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/city/{username}/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String demo(@PathParam("username") final String username, @PathParam("zipcode") final String zipcode) {
        
        //parse get values
        try{
            GeoRequest request = new GeoRequest(username, zipcode);
            //request ok

            /*
            //do the magic stuff
            CityNameResolver resolver = new CityNameResolver(request);
            String city = resolver.getCityName();

            request.setCity(city);
            */

            //save it on db
            persistentStorage(request);

            //magic done
            //convert result to json string
            try {
                return JsonConverter.convert(request);
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(TrackerResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ApiErrors.JSON_CONVERSION_ERROR;
            }
        catch(NumberFormatException e){
            return ApiErrors.ZIPCODE_NO_NUMERIC_ERROR;
        }
        catch(NullPointerException e){
            return ApiErrors.NULL_ARGUMENT_ERROR;
        }
    }

    private void persistentStorage(GeoRequest request) {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "sherpa.challenge.jpa" );
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        manager = DBManager.getInstance();
        
        
        Detalle d = new Detalle();
        Master m = new Master();
        
        //detalle data
        d.setCity(request.getCity());
        d.setZipcode(String.valueOf(request.getZipCode()));
        d.addMaster(m);
        
        //master data
        m.setUsername(request.getUsername());
        m.setDetalle(d);
        
        //save data
        manager.begin();
        System.out.println("Making persistent "+d);
        manager.save(d);
        System.out.println("Making persistent "+m);
        manager.save(m);
        manager.finish(); //save resources
    }
    
}
