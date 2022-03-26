/**
 * 
 */
package com.edu.publiclibrary.api.v1.model;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public class Book {

	private Long id;
	private String title;
	private User reader;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getReader() {
		return reader;
	}
	public void setReader(User reader) {
		this.reader = reader;
	}
}
