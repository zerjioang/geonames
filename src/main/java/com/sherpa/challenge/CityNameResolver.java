/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge;

import com.sherpa.challenge.model.http.GeoRequest;
import com.sherpa.challenge.model.json.GeoNameResponse;
import com.sherpa.challenge.model.json.PostalCode;
import com.sherpa.challenge.util.JsonConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author .local
 */
public class CityNameResolver {

    private static final String BASE = "http://api.geonames.org/findNearbyPostalCodesJSON";
    
    //params
    private static final String POSTAL = "postalcode", COUNTRY = "country", RADIUS = "radius", USERNAME = "username";
    private static final String DEFAULT_RADIUS = "2";
    private static final String DEFAULT_COUNTRY = "ES";
    
    private Client client;
    private WebTarget target;

    private final GeoRequest request;
    
    private String resolvedCity;
    
    public CityNameResolver(GeoRequest request) {
        this.request = request;
        this.client = ClientBuilder.newClient();
        
        //example
        //full:  "http://api.geonames.org/findNearbyPostalCodesJSON?postalcode=48003&country=ES&radius=2&username=demo"
        //url : "http://api.geonames.org/findNearbyPostalCodesJSON"
        //query params:  "postalcode=48003&country=ES&radius=2&username=demo"
        target = client.target(BASE)
                .queryParam(POSTAL, String.valueOf(request.getZipCode()))
                .queryParam(COUNTRY, DEFAULT_COUNTRY)
                .queryParam(RADIUS, DEFAULT_RADIUS)
                .queryParam(USERNAME, request.getUsername());
        
       Response response = target.request(MediaType.APPLICATION_JSON).get();
       String jsonString = response.readEntity(String.class);
       System.out.println(jsonString);
        
        try {
            //convert to object
            GeoNameResponse geoResponse = (GeoNameResponse) JsonConverter.toObject(jsonString, GeoNameResponse.class);
            resolvedCity = resolve(geoResponse);
        } catch (IOException ex) {
            Logger.getLogger(CityNameResolver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String resolve(GeoNameResponse response) {
        
        if(response == null || response.getPostalCodes() == null || response.getPostalCodes().isEmpty()){
            return "unknown city";
        }
        else{
            for( PostalCode pc : response.getPostalCodes()){
                //return the first result
                return pc.getAdminName1();
            }
            return "no matches";
        }
    }

    public String getCityName() {
        return resolvedCity;
    }
    
    
    
}
