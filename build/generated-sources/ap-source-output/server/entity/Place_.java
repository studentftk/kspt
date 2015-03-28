package server.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Place.class)
public abstract class Place_ {

	public static volatile SingularAttribute<Place, String> houseCorp;
	public static volatile SingularAttribute<Place, Long> id;
	public static volatile SingularAttribute<Place, String> title;
	public static volatile SingularAttribute<Place, String> geo;
	public static volatile SingularAttribute<Place, String> street;
	public static volatile SingularAttribute<Place, String> about;
	public static volatile SingularAttribute<Place, Long> houseNumber;
	public static volatile SingularAttribute<Place, String> type;

}

