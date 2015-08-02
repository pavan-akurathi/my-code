package com.verizon.cab.management.repositories.mongodb;

import java.util.List;

import com.verizon.cab.management.domain.User;

public interface CabRepositoryCustom {

	public List<User> getNearbyHave(String[] location, String distance);
	
	public List<User> getNearbyNeed(String[] location, String distance);	
	
}
