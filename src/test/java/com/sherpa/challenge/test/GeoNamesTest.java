/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.test;

import com.sherpa.challenge.CityNameResolver;
import com.sherpa.challenge.model.http.GeoRequest;
import com.sherpa.challenge.model.json.GeoNameResponse;
import com.sherpa.challenge.util.JsonConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import junit.framework.TestCase;

/**
 *
 * @author .local
 */
public class GeoNamesTest {
    
    //THIS CLASS DOES NOT PASS THE TEST BECAUSE BECAUSE JERSEY LOADS SOME CODE USING REFLECTION AND THIS PROCESS THROWS A CLASSNOTFOUNDEXCEPTION.
    
    private static final String BASE = "http://api.geonames.org/findNearbyPostalCodesJSON";
    
    //params
    private static final String POSTAL = "postalcode", COUNTRY = "country", RADIUS = "radius", USERNAME = "username";
    private static final String DEFAULT_RADIUS = "2";
    private static final String DEFAULT_COUNTRY = "ES";
    
    private Client client;
    private WebTarget target;
    
    private String resolvedCity;
    
    //@Test
    public void geoNamesTest(){
        CityNameResolver resolver = new CityNameResolver(new GeoRequest("asergio", "48003"));
        TestCase.assertEquals(resolver.getCityName(), "Pais Vasco");
    }
    
    //@Test
    public void test(){
        GeoRequest request = new GeoRequest("asergio", "48003");
        this.client = ClientBuilder.newClient();
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
            resolvedCity = geoResponse.getPostalCodes().get(0).getAdminName1();
            TestCase.assertEquals(resolvedCity, "Pais Vasco");
        } catch (IOException ex) {
            Logger.getLogger(CityNameResolver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
