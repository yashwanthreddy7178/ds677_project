package org.opsart.event.core.repo;

import org.opsart.event.core.dao.AuthDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AuthRepository extends MongoRepository<AuthDAO, String> {

	@Query("{token:'?0'}")
	AuthDAO findAuthByToken(String token);
	
}
