/**
 * 
 */
package com.edu.publiclibrary.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.edu.publiclibrary.domain.User;
import com.edu.publiclibrary.jwt.config.JwtUtil;
import com.edu.publiclibrary.repository.UserRepository;
import com.edu.publiclibrary.service.UserService;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final JwtUtil jwtUtil;
	
	public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public String getUsernameFromToken(String jwtToken) {

		return jwtUtil.getUsernameFromToken(jwtToken);
	}
	
	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public Set<User> findAll() {
		
		Set<User> users = new HashSet<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
