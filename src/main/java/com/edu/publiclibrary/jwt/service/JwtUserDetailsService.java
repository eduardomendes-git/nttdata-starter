/**
 * 
 */
package com.edu.publiclibrary.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.publiclibrary.service.UserService;

/**
 * Class copied and modified from javainuse.com
 * 
 * JWTUserDetailsService implements the Spring Security UserDetailsService interface. 
 * It overrides the loadUserByUsername for fetching user details from the database 
 * using the username.
 *  
 * The Spring Security Authentication Manager calls this method for getting the 
 * user details from the database when authenticating the user details provided by 
 * the user. 
 * 
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	private final UserService userService;
	
	public JwtUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Checks if the user with username indicated exists in the database. If it does,
	 * encapsulate it and return.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.edu.publiclibrary.domain.User domainUser = userService.findByUsername(username);
		
		User user = null;
		
		if (domainUser != null) {
			
			user = new User(domainUser.getUsername(), domainUser.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return user;
	}

}
