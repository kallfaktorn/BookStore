package se.contribe.mattiaspettersson.bookstore.model;

import java.util.ArrayList;

public class BookCart {
	private ArrayList<Book> books;
	
	public BookCart() {
		books = new ArrayList<Book>();
	}
	
	public void addToCart(Book book) {
		books.add(book);
	}
	
	public void removeFromCart(Book book) {
		books.remove(book);
	}
	
	public ArrayList<Book> listCart() {
		return books;
	}
}