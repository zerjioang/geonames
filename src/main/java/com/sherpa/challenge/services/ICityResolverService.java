/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.services;

/**
 *
 * @author .local
 */
public interface ICityResolverService {
    
    String getCity(final String username, final String zipcode);
    
}
