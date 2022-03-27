/**
 * 
 */
package com.edu.publiclibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.edu.publiclibrary.domain.User;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
}
