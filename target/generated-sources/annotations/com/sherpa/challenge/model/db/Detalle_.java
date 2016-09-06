package com.sherpa.challenge.model.db;

import com.sherpa.challenge.model.db.Master;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-07T00:56:27")
@StaticMetamodel(Detalle.class)
public class Detalle_ { 

    public static volatile SingularAttribute<Detalle, String> zipcode;
    public static volatile SingularAttribute<Detalle, String> city;
    public static volatile CollectionAttribute<Detalle, Master> masters;
    public static volatile SingularAttribute<Detalle, Long> id;

}