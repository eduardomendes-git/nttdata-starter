/**
 * 
 */
package com.edu.publiclibrary.api.v1.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public class User {

	private Long id;
	private String name;
	private Set<Book> books = new HashSet<Book>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
