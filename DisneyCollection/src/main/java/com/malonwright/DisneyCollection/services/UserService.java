package com.malonwright.DisneyCollection.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.malonwright.DisneyCollection.models.User;
import com.malonwright.DisneyCollection.repositories.RoleRepository;
import com.malonwright.DisneyCollection.repositories.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private RoleRepository rRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
		//1
	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(rRepo.findByName("ROLE_USER"));
		uRepo.save(user);
	}
	 // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(rRepo.findByName("ROLE_ADMIN"));
        uRepo.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return uRepo.findByUsername(username);
    }
}
