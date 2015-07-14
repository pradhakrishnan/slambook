package org.slambook.application.repository;

import java.util.List;

import org.slambook.application.domain.SlamBookUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserAccountRepository extends MongoRepository<SlamBookUser, String> {
	public SlamBookUser findByUsername(final String username);
	public List<SlamBookUser> findByUsernameAndPassword(final String username, final String password);
	@Query("{ 'username' : ?0, 'password' : ?1 }")
	List<SlamBookUser> findByUsernameAndPasswordQuery(final String username, final String password);
	public SlamBookUser insert(SlamBookUser user);
}
