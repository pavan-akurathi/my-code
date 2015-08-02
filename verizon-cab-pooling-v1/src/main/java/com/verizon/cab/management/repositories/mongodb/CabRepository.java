package com.verizon.cab.management.repositories.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.cab.management.domain.User;

@Repository
public interface CabRepository extends MongoRepository<User, String>,CabRepositoryCustom {	
	
	public List<User> findByProviderUserId(String providerUserId);
	
	public List<User> findByLocationNear(Point location, Distance distance);
	
}
