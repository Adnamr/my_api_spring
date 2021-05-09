package com.api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	Optional<User> findByEmail(String email);

}
