package com.verizon.cab.management.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.verizon.cab.management.domain.User;
public interface CabRepository extends MongoRepository<User, String> {
	
	
}
