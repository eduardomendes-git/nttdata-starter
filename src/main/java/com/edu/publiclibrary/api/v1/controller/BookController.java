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
import com.edu.publiclibrary.domain.User;
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

	@GetMapping("borrow/{bookId}")
	public @ResponseBody Book borrowBook(@PathVariable Long bookId) {
		
		Book book = bookService.findAllAvailable()
							.stream()
							.filter(b -> b.getId() == bookId)
							.findFirst().orElse(null);
		
		if (book == null) {
			throw new RuntimeException("The book with id '" + bookId + "' is not available");
		}
		
		User user = new User();
		user.setUsername("userA");
		user.setId(1L);
		
		return bookService.borrowBook(book, user);
	}
}
