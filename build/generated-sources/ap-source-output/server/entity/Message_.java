package server.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, String> message;
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, Timestamp> time;
	public static volatile SingularAttribute<Message, Long> source;
	public static volatile SingularAttribute<Message, Long> destination;

}

