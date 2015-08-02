package com.verizon.cab.management.repositories.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.verizon.cab.management.domain.User;

public class CabRepositoryImpl  implements CabRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	private Class<User> clazz = User.class;
	
	
}
