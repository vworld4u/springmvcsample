package com.vworld4u.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vworld4u.models.User;

@Repository("usersEAO")
public interface UserRepository extends CrudRepository<User, Long>{
	public User findByEmail(String email);
}
