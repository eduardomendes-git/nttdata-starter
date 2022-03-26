/**
 * 
 */
package com.edu.publiclibrary.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.edu.publiclibrary.domain.Book;
import com.edu.publiclibrary.repository.BookRepository;
import com.edu.publiclibrary.service.BookService;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public Set<Book> findAll() {
		Set<Book> books = new HashSet<Book>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}

	@Override
	public Book findById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(Book book) {
		bookRepository.delete(book);
	}

	@Override
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}

}
