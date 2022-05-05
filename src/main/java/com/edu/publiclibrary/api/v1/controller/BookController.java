/**
 * 
 */
package com.edu.publiclibrary.api.v1.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.publiclibrary.domain.Book;
import com.edu.publiclibrary.domain.User;
import com.edu.publiclibrary.service.BookService;
import com.edu.publiclibrary.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Api("The Books Controller")
@RequestMapping(BookController.API_BASE_URL)
@RestController
public class BookController {

	public static final String API_BASE_URL = "public-library/api/v1/books/";
	
	private final BookService bookService;
	
	private final UserService  userService;

	public BookController(BookService bookService, 
						  UserService  userService) {
		super();
		this.bookService = bookService;
		this.userService = userService;
	}
	
	@ApiOperation("Returns the list of all books from the library")
	@GetMapping
	public @ResponseBody Set<Book> getAllBooks() {
		
		return bookService.findAll();
	}
	
	@ApiOperation("Returns the list of all available books to borrow")
	@GetMapping("available")
	public @ResponseBody Set<Book> getAllAvailableBooks() {
		return bookService.findAllAvailable();
	}
	
	@ApiOperation("Returns the book with given bookId")
	@GetMapping("{bookId}")
	public @ResponseBody Book getBookById(@PathVariable Long bookId) {
		
		return bookService.findById(bookId);
	}

	@ApiOperation("Borrow book with bookId to the authenticated user. This service requires previous authentication. A valid bearer token must be placed in the header.")
	@GetMapping("borrow/{bookId}")
	public @ResponseBody Book borrowBook(@RequestHeader("Authorization") String jwtToken, 
										 @PathVariable Long bookId) {

		Book book = bookService.findAllAvailable()
							.stream()
							.filter(b -> b.getId() == bookId)
							.findFirst().orElse(null);
		
		if (book == null) {
			throw new RuntimeException("The book with id '" + bookId + "' is not available");
		}
		
		// TODO Get username from the Principal
		//User user = userService.findByUsername(username);
		User user = new User();
		
		return bookService.borrowBook(book, user);
	}
}
