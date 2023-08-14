package com.yg.blog.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yg.blog.entities.User;
import com.yg.blog.exceptions.ResourceNotFoundException;
import com.yg.blog.repository.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	
	public CustomUserDetailsService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database by username
		User user= this.userRepo.findByEmail(username).orElseThrow(
				()->new ResourceNotFoundException("User ", "email : "+username, 0));
		
		return user;
		//return new org.springframework.security.core.userdetails.UserDetails.User(user.getEmail(),user.getPassword(),user.getAuthorities(user.getRoles()));
	}
	
}

