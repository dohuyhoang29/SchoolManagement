package com.schoolmanagement.config.factory;


import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

// ..
public class SessionFactorys extends MySQL8Dialect {
	public SessionFactorys() {
		super();

		registerFunction("GROUP_CONCAT", new StandardSQLFunction("GROUP_CONCAT", StandardBasicTypes.STRING));
//		registerFunction("CAST", new CastFunction());
	}

}