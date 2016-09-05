/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.model;

/**
 *
 * @author .local
 */
public class GeoRequest implements IGeoRequest{

    private final String username;
    private final int zipcode;
    
    private String city;
    
    public GeoRequest(){
        this.username = "";
        this.zipcode = -1;
        this.city = "unknown";
    }
    
    public GeoRequest(final String username, final String zipcode) throws NullPointerException, NumberFormatException{
        
        //security check
        if(username == null || zipcode == null)
            throw new NullPointerException("One or more arguments are not valid because are null references");
        
        //set fields
        this.username = username;
        this.city = "unknown";
        try{
            this.zipcode = Integer.parseInt(zipcode);
        }
        catch(NumberFormatException e){
            throw e;
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getZipCode() {
        return zipcode;
    }

    @Override
    public String getCity() {
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
}
