/**
 * 
 */
package com.edu.publiclibrary.api.v1.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.publiclibrary.domain.Book;
import com.edu.publiclibrary.service.BookService;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@RequestMapping(BookController.API_BASE_URL)
@RestController
public class BookController {

	public static final String API_BASE_URL = "public-library/api/v1/books/";
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@GetMapping
	public @ResponseBody Set<Book> getAllBooks() {
		
		return bookService.findAll();
	}
	
	@GetMapping("available")
	public @ResponseBody Set<Book> getAllAvailableBooks() {
		return bookService.findAllAvailable();
	}
	
	@GetMapping("{bookId}")
	public @ResponseBody Book getBookById(@PathVariable Long bookId) {
		
		return bookService.findById(bookId);
	}
}
