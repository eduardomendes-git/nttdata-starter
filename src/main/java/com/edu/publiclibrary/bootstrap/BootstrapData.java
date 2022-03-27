/**
 * 
 */
package com.edu.publiclibrary.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.edu.publiclibrary.domain.Book;
import com.edu.publiclibrary.domain.User;
import com.edu.publiclibrary.service.BookService;
import com.edu.publiclibrary.service.UserService;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
@Component
public class BootstrapData implements CommandLineRunner {

	private final BookService bookService;
	private final UserService userService;
	
	public BootstrapData(BookService bookService, 
						 UserService userService) {
		super();
		this.bookService = bookService;
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		loadUsers();
		loadBooks();
	}

	private void loadBooks() {
		
		Book book1 = new Book();
		book1.setTitle("Cosmos");
		bookService.save(book1);
		
		Book book2 = new Book();
		book2.setTitle("Norwegian Wood");
		bookService.save(book2);
		
		Book book3 = new Book();
		book3.setTitle("La sombra del viento");
		bookService.save(book3);
		
		Book book4 = new Book();
		book4.setTitle("Almoço de Domingo");
		bookService.save(book4);
		
		Book book5 = new Book();
		book5.setTitle("War and Peace");
		bookService.save(book5);
		
		Book book6 = new Book();
		book6.setTitle("Crime and Punishment");
		bookService.save(book6);
		
		System.out.println("Saved books.");
	}
	
	private void loadUsers() {
		
		User user1 = new User();
		user1.setUsername("eduardo");
		// passwordA
		user1.setPassword("$2a$10$p3tCz/CMEVWqDfjbhMOJ3.zyogAdS.eshGAGZom5BX.XJJhta9/Yi");
		userService.save(user1);
		
		User user2 = new User();
		user2.setUsername("john");
		// passwordB
		user2.setPassword("$2a$10$Bkfel78FnjlpOKzjahIQxeWsIuVXYdsq0s2n2JS/VgTR6qGxt7bCu");
		userService.save(user2);
		
		User user3 = new User();
		user3.setUsername("lewis");
		// passwordC
		user3.setPassword("$2a$10$KiNf2oFzUw6YwLF8x1n/NOgUxm33Vy7NhyU2RcvVPzc9XFM3bkjg.");
		userService.save(user3);
		
		System.out.println("Saved users.");
	}
}
