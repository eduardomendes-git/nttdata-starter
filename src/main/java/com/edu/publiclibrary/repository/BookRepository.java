/**
 * 
 */
package com.edu.publiclibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.edu.publiclibrary.domain.Book;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
