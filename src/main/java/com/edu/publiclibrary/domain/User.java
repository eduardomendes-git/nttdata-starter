/**
 * 
 */
package com.edu.publiclibrary.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "username")
	private String username;
	
	@OneToMany(mappedBy = "reader")
	private Set<Book> books = new HashSet<Book>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
