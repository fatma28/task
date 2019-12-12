package com.home.task.service;

import java.util.List;

import com.home.task.model.Book;

public interface BookService {

	//•	View all books in the database
	Book viewBookDetails(int id);
	List<Book> getAllBooks();
	
	/*•	Add a new book(Prompt the user for the book title, author and description,
	Save their changes to the database)*/
	void addBook(List<Book> books, int flag);
	void addBook(Book book);
	//•	Edit an existing book
	
	//• Search for books using keywords
	Book getBookByTitle(String title);
	Book getBookByTitle(String title, List<Book>b);
	void closeConnection();
	List<Book> removeBookFromListAndFile(String title);
}
