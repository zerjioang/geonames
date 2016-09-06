/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.model.http;

/**
 *
 * @author .local
 */
public interface IGeoRequest {
    
    String getUsername();
    int getZipCode();
    String getCity();
    void setCity(String city);
}
