package server.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Checkin.class)
public abstract class Checkin_ {

	public static volatile SingularAttribute<Checkin, Long> id;
	public static volatile SingularAttribute<Checkin, Timestamp> time;
	public static volatile SingularAttribute<Checkin, Long> idUser;
	public static volatile SingularAttribute<Checkin, Long> idPlace;

}

