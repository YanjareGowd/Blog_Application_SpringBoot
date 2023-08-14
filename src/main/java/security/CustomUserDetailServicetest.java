package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.yg.blog.entities.User;
import com.yg.blog.exceptions.ResourceNotFoundException;
import com.yg.blog.repository.UserRepo;


@Service
public class CustomUserDetailServicetest implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database by username
		User user= this.userRepo.findByEmail(username).orElseThrow(
				()->new ResourceNotFoundException("User ", "email : "+username, 0));
		
		return user;
	}
	
}
