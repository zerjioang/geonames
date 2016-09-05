/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge;

import com.sherpa.challenge.model.GeoRequest;
import com.sherpa.challenge.util.ApiErrors;
import com.sherpa.challenge.util.JsonConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TrackerResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TrackerResource
     */
    public TrackerResource() {
    }

    /**
     * Retrieves representation of an instance of com.sherpa.challenge.TrackerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello world";
    }
    
    /**
     * Retrieves representation of an instance of com.sherpa.geonames.services.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Generic resource is up and running!";
    }
    
    /**
     * Retrieves representation of an instance of com.sherpa.geonames.services.GenericResource
     * @param username
     * @param zipcode
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
        //do the magi stuff
        
        //magic done
        //convert result to json string
        Object result = null;
        try {
            return JsonConverter.convert(result);
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
    
}
