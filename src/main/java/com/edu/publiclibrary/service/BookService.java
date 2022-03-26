/**
 * 
 */
package com.edu.publiclibrary.service;

import java.util.Set;

import com.edu.publiclibrary.domain.Book;
import com.edu.publiclibrary.domain.User;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public interface BookService extends CrudService<Book, Long> {

	public Set<Book> findAllAvailable();

	public Book borrowBook(Book book, User user);
}
