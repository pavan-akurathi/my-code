package com.verizon.cab.management.repositories.mongodb;

import java.util.List;
import java.util.Set;

import com.verizon.cab.management.domain.User;

public interface CabRepositoryCustom {

	public List<String> getProvidersList();
	
	public List<User> getAvUsersList(Set<String> avUserSet);
	
}
