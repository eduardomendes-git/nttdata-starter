/**
 * 
 */
package com.edu.publiclibrary.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Entity
@Table(name = "book")
public class Book extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "reader_id")
	private User reader;
	
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
