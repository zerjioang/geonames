/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.test;

import com.sherpa.challenge.model.db.Detalle;
import com.sherpa.challenge.util.JsonConverter;
import java.io.IOException;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author .local
 */
public class JsonTest {
    
    @Test
    public void testJsonToObject() throws IOException{
        String data = "{\"id\":0,\"zipcode\":\"0000\",\"city\":\"Bilbao\",\"masters\":[]}";
        Detalle dj = (Detalle) JsonConverter.toObject(data, Detalle.class);
        
        Detalle d = new Detalle();
        d.setId(0l);
        d.setCity("Bilbao");
        d.setZipcode("0000");
        
         TestCase.assertEquals(d.toString(), dj.toString());
    }
    
    @Test
    public void objectToJson() throws NullPointerException, IOException{
        Detalle d = new Detalle();
        d.setId(0l);
        d.setCity("Bilbao");
        d.setZipcode("0000");
        String data = JsonConverter.convert(d);
        TestCase.assertEquals(data, "{\"id\":0,\"zipcode\":\"0000\",\"city\":\"Bilbao\",\"masters\":[]}");
    }
}
