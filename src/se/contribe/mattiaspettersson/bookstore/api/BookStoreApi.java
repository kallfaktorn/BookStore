package se.contribe.mattiaspettersson.bookstore.api;

import java.math.BigDecimal;
import java.util.ArrayList;

import se.contribe.mattiaspettersson.bookstore.model.Book;
import se.contribe.mattiaspettersson.bookstore.model.BookCart;
import se.contribe.mattiaspettersson.bookstore.model.BookStore;
import se.contribe.mattiaspettersson.bookstore.util.Utils;

public class BookStoreApi {
	
	public int[] buyBooks (BookStore bookStore, BookCart bookCart) {
		return bookStore.buy(new Utils().bookArrayListToArray(bookCart.listCart()));
	}
	
	public Book[] listBookCart(BookCart bookCart) {
		return new Utils().bookArrayListToArray(bookCart.listCart());
	}
	
	public void addBookToCart(Book book, BookStore bookStore, BookCart bookCart) {
		bookCart.addToCart(book);
	}
	
	public void removeBookFromCart(Book book, BookCart bookCart) {
		bookCart.removeFromCart(book);
	}
	
	public void addBookToStore(Book book, int quantity, BookStore bookStore) {
		bookStore.add(book, quantity); 
	}
	
	public ArrayList<Book> findBooksByTitle(String title, Book[] books) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
	
	public ArrayList<Book> findBooksByAuthor(String author, Book[] books) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getAuthor().equals(author)) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
	
	public ArrayList<Book> findBooksByPrice(BigDecimal price, Book[] books) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getPrice().equals(price)) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
}
