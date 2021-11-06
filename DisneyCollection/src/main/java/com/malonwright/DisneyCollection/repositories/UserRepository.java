package com.malonwright.DisneyCollection.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.malonwright.DisneyCollection.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	List<User> findAll();
	List<User> findByOrderByUsernameDesc();
	List<User> findByOrderByUsernameAsc();

}
