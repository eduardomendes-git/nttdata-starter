/**
 * 
 */
package com.edu.publiclibrary.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Class based on a copy from javainuse.com
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
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("javainuse".equals(username)) {
			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
