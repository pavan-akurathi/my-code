package com.verizon.cab.management.repositories.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.cab.management.domain.UserRoute;

@Repository
public interface UserRouteRepository extends MongoRepository<UserRoute, String> {	
	
	List<UserRoute> findByLocationNear(Point location, Distance distance);
	
	List<UserRoute> findByUserId(String userId);
	
}
