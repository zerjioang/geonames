
package com.sherpa.challenge.model.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoNameResponse {

    private List<PostalCode> postalCodes = new ArrayList<PostalCode>();

    /**
    * 
    * @return
    * The postalCodes
    */
    public List<PostalCode> getPostalCodes() {
        return postalCodes;
    }

    /**
    * 
    * @param postalCodes
    * The postalCodes
    */
    public void setPostalCodes(List<PostalCode> postalCodes) {
        this.postalCodes = postalCodes;
    }

}
