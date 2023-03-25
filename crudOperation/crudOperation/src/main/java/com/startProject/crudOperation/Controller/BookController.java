package com.startProject.crudOperation.Controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.startProject.crudOperation.model.Book;

@RestController
public class BookController {
	private HashMap<Integer, Book> bookHashMap = new HashMap<Integer, Book>();
	
	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
	//insert book - post - @request body
	//update book - put - @request body
	//delete book - delete - @path variable (BookId)
	//getBookDetails - get - @Pathvariable(id)
	//List<book> bookList - Get - return bookList	
	
	@PostMapping("/insertBook")
	public ResponseEntity insertBook(@RequestBody Book book){
		logger.info("Book is coming for insertion : {}",book);
		if(bookHashMap.containsKey(book.getId())) {
			logger.error("Book already present.");
					return new ResponseEntity<>(book,HttpStatus.BAD_REQUEST);
		}
		bookHashMap .put(book.getId(), book);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/updateBook")
	public Book updateBook(@RequestBody Book book){
		bookHashMap .put(book.getId(), book);
		return bookHashMap.get(book.getId());	
		
	}
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int bookId) {
		bookHashMap.remove(bookId);
		return "Book Id is deleted Successfully";	
	}
	@GetMapping("/book/{bookId}")
	public Book getBookByBookId(@PathVariable("bookId") int bookId) {
		logger.info("bookId Received : {}",bookId);
		return 	bookHashMap.get(bookId);
		
	}
	@GetMapping("/getBooks")
	public List<Book> getBooks(){
		return 	bookHashMap.values()
				.stream().toList();
	}
	
	@PatchMapping("/updateBookDetails/{bookId}")
	public Book updateBookDetails(@RequestBody Book book){
		bookHashMap .put(book.getId(), book);
		return bookHashMap.get(book.getId());	
	}
}