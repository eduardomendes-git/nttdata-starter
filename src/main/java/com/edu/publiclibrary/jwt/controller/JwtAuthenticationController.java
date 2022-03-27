/**
 * 
 */
package com.edu.publiclibrary.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.publiclibrary.jwt.config.JwtUtil;
import com.edu.publiclibrary.jwt.model.JwtRequest;
import com.edu.publiclibrary.jwt.model.JwtResponse;
import com.edu.publiclibrary.jwt.service.JwtUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Class copied and modified from javainuse.com
 * 
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Api("The Authentication controller")
@CrossOrigin
@RestController
public class JwtAuthenticationController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final JwtUserDetailsService userDetailsService;

	public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			JwtUserDetailsService userDetailsService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@ApiOperation("Create a JWT token for the authenticated user")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
