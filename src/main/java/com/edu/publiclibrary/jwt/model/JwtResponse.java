/**
 * 
 */
package com.edu.publiclibrary.jwt.model;

import java.io.Serializable;

/**
 * Class copied and modified from javainuse.com
 * 
 * 
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}