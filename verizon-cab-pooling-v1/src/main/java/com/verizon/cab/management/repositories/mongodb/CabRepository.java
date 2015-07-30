package com.verizon.cab.management.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.cab.management.domain.User;

@Repository
public interface CabRepository extends MongoRepository<User, String>,CabRepositoryCustom {	
	
}
