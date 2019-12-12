package com.home.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.home.task.model.Book;
import com.home.task.service.BookService;
import com.home.task.service.BookServiceImp;

/**
 * @author fatma ramadan
 *
 */
public class App {
	
	private static int idCounter=1;
	
	public static void main(String[] args) {
	
		int enteredNumber= displayMenuAndEnterNumber();
		List<Book> allBookList=new ArrayList<Book>();
		BookService bookService = new BookServiceImp();
		do {
			if ((args != null && args.length > 0) || enteredNumber!=5) {				
				switch (enteredNumber) {
					case 1:
						// 1- View All books
						System.out.println("==== View Books ====");
						allBookList=bookService.getAllBooks();
						for(Book b: allBookList) {
							System.out.println(b.getId() +" - " +b.getTitle());
						}
						enteredNumber= displayMenuAndEnterNumber();
						break;
					case 2:
						// 2- Add New Book
						System.out.println("==== Add a Book ====");
						System.out.println("Please enter the following information:");
						Book newBook= getBookDetailsFromUser();
						newBook.setId(idCounter++);
						bookService.addBook(newBook);
						allBookList.add(newBook);
						enteredNumber= displayMenuAndEnterNumber();
						break;
					case 3:
						// 3- edit existing book
						// 1- get All Books
						if(allBookList==null||allBookList.isEmpty())
							allBookList =bookService.getAllBooks();
							
						if(allBookList!=null && !allBookList.isEmpty()) {
							System.out.print("Please Enter the title of a book: ");
							String enteredBookTitle= getBookTitleFromUser();
							int flag = 0;
							// check if book exits in list
							for(Book b: allBookList) {
								if(b.getTitle().equalsIgnoreCase(enteredBookTitle)) {
									//remove oldBook from list
									allBookList.remove(b);
									flag=1;
									break;
								}
							}
							if(flag==1) {
								allBookList.add(getBookDetailsFromUser());
								//edit
								bookService.addBook(allBookList, 1);	
								}else {
									System.out.println("Book not found!");
								}
				
							}else {
								System.out.println("No Books are found");
							}
							enteredNumber= displayMenuAndEnterNumber();
							break;
						case 4:
							//4- search
	//						if(allBookList==null||allBookList.isEmpty())
	//							allBookList =bookService.getAllBooks();
							
							System.out.println("==== Search ====");
							System.out.println("==== Type Title to search for ====");
							Book b= bookService.getBookByTitle(getBookTitleFromUser());
							if(b!=null) {
								System.out.println("The following books matched your query. Enter the book ID to see more details");
								System.out.println("["+b.getId()+"] "+b.getTitle());
								
								if(b.getId()==getNumberFromUser()) {
									System.out.println("ID: "+b.getId()+"\n Title: "+b.getTitle() +"\n Author: "+b.getAuthor()+"\n Description: "+b.getDescription());
								}else {
									enteredNumber= displayMenuAndEnterNumber();
									break;
								}
							}else {
								System.out.println("Book Not found");
							}
							
							enteredNumber= displayMenuAndEnterNumber();
							break;
						case 5:
							System.out.println("System exit successfully.");
	//						bookService.closeConnection();
	//						enteredNumber= displayMenuAndEnterNumber();
							break;
						default:
							enteredNumber= displayMenuAndEnterNumber();
					}
				}
			} while (enteredNumber!= 5);
	
		
	}

	private static int getNumberFromUser() {
		return new Scanner(System.in).nextInt();
	}
	
	private static Book getBookDetailsFromUser() {
		Scanner s=new Scanner(System.in);
		Book b= new Book();
		System.out.print("Title: ");
		b.setTitle(s.next());
		System.out.print("Author: ");
		b.setAuthor(s.next());
		System.out.print("Description: ");
		b.setDescription(s.next());
	
		return b;
	}
	
	private static int  displayMenuAndEnterNumber() {
		System.out.println("==== Book Manager ====\r\n" 
				+ "	1) View all books\r\n" 
				+ "	2) Add a book\r\n"
				+ "	3) Edit a book\r\n" 
				+ "	4) Search for a book\r\n" 
				+ "	5) Save and exit\r\n" + "");

		System.out.print("Choose [1-5]: ");
		return getNumberFromUser();
	}
	
	private static String getBookTitleFromUser() {
		Scanner s=new Scanner(System.in);
		System.out.print("Title: ");
		return s.next();
	}
}
