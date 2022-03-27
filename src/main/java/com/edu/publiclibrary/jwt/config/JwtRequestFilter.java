/**
 * 
 */
package com.edu.publiclibrary.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edu.publiclibrary.jwt.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Class copied and modified from javainuse.com
 * 
 * The JwtRequestFilter extends the Spring Web Filter OncePerRequestFilter class. 
 * For any incoming request this Filter class gets executed. It checks if the 
 * request has a valid JWT token. If it has a valid JWT Token then it sets the 
 * Authentication in the context, to specify that the current user is authenticated.
 * 
 * @author	eduardomendes
 * @date	27 Mar 2022
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtUserDetailsService jwtUserDetailsService;

	private final JwtUtil jwtUtil;
	
	public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, 
							JwtUtil jwtUtil) {
		super();
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain) throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				username = jwtUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException iaXcp) {
				logger.error("Unable to get JWT Token:\n" + iaXcp);
			} catch (ExpiredJwtException eJwtXcp) {
				logger.warn("JWT Token has expired :\n" + eJwtXcp);
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtUtil.isValidToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, 
																	null, 
																	userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}
