/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author .local
 */
public class JsonConverter {
   
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static final String convert(Object o) throws NullPointerException, IOException{
        try {
            if(o!=null)
                return mapper.writeValueAsString(o);
            throw new NullPointerException("Jackson json can not convert a null reference to a valid String");
        } catch (IOException ex) {
            Logger.getLogger(JsonConverter.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static final Object toObject(String data, Class cls) throws IOException{
        return mapper.readValue(data, cls);
    }
}
