package com.home.task.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.home.task.model.Book;

public class BookServiceImp implements BookService{
	
	List<Book>bookList=null;
	final File file= new File("bookDB.txt");
	
	public BookServiceImp(){
		try {
			bookList= loadFile(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Book viewBookDetails(int id) {
		return null;
	}

	public List<Book> getAllBooks() {
		List<Book> books = null;
		try {
			 books= loadFile(null);
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("An Exception while loading file");
		}
		return books;
	}

	public void addBook(List<Book> books, int flag) {
		writeInFile(null, flag, books);
	}

	// read from file
	private List<Book> loadFile(String optionalParam) throws IOException {
	
		BufferedReader reader= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	
		String line;
		bookList= new ArrayList<Book>();
		while ((line = reader.readLine()) != null) {
				String[] x= line.split(",");
				Book book= new Book();
				if(optionalParam==null) {
					book.setId(Integer.parseInt(x[0]));
					book.setTitle(x[1]);
					book.setAuthor(x[2]);
					book.setDescription(x[3]);
					bookList.add(book);
				}else {
					// skip adding this book in file or list
					if(optionalParam.equalsIgnoreCase(x[0])) {
						
						continue;
					}
				}
		}
		reader.close();
		return bookList;
		
		
	}

	private void writeInFile(Book book, int flag, List<Book> books) {
		try {
//			File file= new File("bookDB.txt");
			BufferedWriter outWriter =null;
			if(flag==0) {
				outWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
				outWriter.write(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+book.getDescription()+"\n");
				System.out.println(book.getTitle()+" is saved.");
			}else {
				outWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
//				outWriter.write("");
				for(Book b: books) {
					outWriter.write(b.getTitle()+","+ b.getAuthor()+","+b.getDescription());
				}
			}
			outWriter.flush();
			
			outWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public Book getBookByTitle(String title) {
		
		if(bookList!=null && !bookList.isEmpty()) {
			for(Book book: bookList) {
				if(title.equalsIgnoreCase(book.getTitle()))
					return book;
			}
		}
		return null;
	}
	
	public Book getBookByTitle(String title, List<Book>books) {
		
		if(books!=null && !books.isEmpty()) {
			for(Book book: books) {
				if(title.equalsIgnoreCase(book.getTitle()))
					return book;
			}
		}
		return null;
	}
	
	public void closeConnection() {
		try {
//			reader.close();
//			outWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> removeBookFromListAndFile(String title) {
		List<Book> bookList=null;
		try {
			bookList= new ArrayList<Book>();
			bookList= loadFile(title);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public void addBook(Book book) {
		writeInFile(book, 0, null);
		
	}
}
