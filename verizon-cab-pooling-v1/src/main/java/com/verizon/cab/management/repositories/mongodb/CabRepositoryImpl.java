package com.verizon.cab.management.repositories.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.verizon.cab.management.domain.User;

public class CabRepositoryImpl  implements CabRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	private Class<User> clazz = User.class;
	
	public List<User> getNearbyHave(String[] location, String distance){
		return mongoTemplate.findAll(clazz);
	}
	
	public List<User> getNearbyNeed(String[] location, String distance){
		return mongoTemplate.findAll(clazz);
	}
}
