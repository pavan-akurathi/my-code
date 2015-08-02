package com.verizon.cab.management.repositories.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.verizon.cab.management.domain.User;

public class CabRepositoryImpl  implements CabRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	private Class<User> clazz = User.class;
	
	public List<User> getNearProviders(String[] location, String distance){
		
		mongoTemplate.find(Query.query(Criteria.where("poolMode").is("P").and("availableCount").gt(0)
				  .and("accounts.balance").gt(1000.00d)), User.class);
		return mongoTemplate.findAll(clazz);
	}
	
	public List<User> getNearbyNeed(String[] location, String distance){
		return mongoTemplate.findAll(clazz);
	}
}
