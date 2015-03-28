package server.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Integer> instituteId;
	public static volatile SingularAttribute<User, String> socialToken;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> about;
	public static volatile SingularAttribute<User, String> socialType;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, Long> socialId;
	public static volatile SingularAttribute<User, String> group;
	public static volatile SingularAttribute<User, String> photo;

}

