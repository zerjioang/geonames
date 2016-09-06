package com.sherpa.challenge.model.db;

import com.sherpa.challenge.model.db.Detalle;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-09-06T02:27:15")
@StaticMetamodel(Master.class)
public class Master_ { 

    public static volatile SingularAttribute<Master, Long> id;
    public static volatile SingularAttribute<Master, String> username;
    public static volatile SingularAttribute<Master, Detalle> detalle;

}