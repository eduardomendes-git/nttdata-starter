/**
 * 
 */
package com.edu.publiclibrary.api.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.publiclibrary.service.UserService;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@RequestMapping(UserController.API_BASE_URL)
@RestController
public class UserController {

	public static final String API_BASE_URL = "public-library/api/v1/users/";

	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
}
