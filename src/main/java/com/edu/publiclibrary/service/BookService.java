/**
 * 
 */
package com.edu.publiclibrary.service;

import java.util.Set;

import com.edu.publiclibrary.domain.Book;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public interface BookService extends CrudService<Book, Long> {

	public Set<Book> findAllAvailable();
}
