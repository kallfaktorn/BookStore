package se.contribe.mattiaspettersson.bookstore.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import se.contribe.mattiaspettersson.bookstore.model.Book;
import se.contribe.mattiaspettersson.bookstore.model.BookCart;
import se.contribe.mattiaspettersson.bookstore.model.BookStore;
import se.contribe.mattiaspettersson.bookstore.util.Utils;

public class BookStoreHandler {


	public void printRootMenu() {
		System.out.println("**************************************");
		System.out.println("1. User login");
		System.out.println("2. Admin login");
		System.out.println("Type \"quit\" to exit");
		System.out.println("**************************************");
	}
	
	public void printUserMenu() {
		System.out.println("**************************************");
		System.out.println("1. List all books");
		System.out.println("2. Search books by title and/or author");
		System.out.println("3. List cart");
		System.out.println("4. Add to cart");
		System.out.println("5. Remove from cart");
		System.out.println("6. Buy all items in cart");
		System.out.println("7. Logout");
		System.out.println("Type \"quit\" to exit");
		System.out.println("**************************************");
	}
	
	public void printAdminMenu() {
		System.out.println("**************************************");
		System.out.println("1. Add book to book store");
		System.out.println("2. Logout");
		System.out.println("Type \"quit\" to exit");
		System.out.println("**************************************");
	}
	
	public void listAllBooks(BookStore bookStore) {
		listBooks(bookStore, "");
	}
	
	public void searchBooks(BufferedReader bufferedReader, BookStore bookStore) {
		System.out.println("Type search string:");
		String searchString = new Utils().readString(bufferedReader);
		listBooks(bookStore, searchString);
	}
	
	public void listBookCart(BookCart bookCart) {
		
		if (!bookCart.listCart().isEmpty()) {
			for (Book book : bookCart.listCart()) {
				printBook(book);
			}	
		} else {
			System.out.println("The cart is empty");
		}
	}
	
	public void addBookToCart(BufferedReader bufferedReader, BookStore bookStore, BookCart bookCart) {
		ArrayList<Book> foundBooks = searchBooksByAttributes(bufferedReader, bookStore);
		if (foundBooks != null) {
			bookCart.addToCart(foundBooks.get(0));
			System.out.println("Book added to cart.");	
		} else {
			System.out.println("No books found.");
		}
	}
	
	public void removeBookFromCart(BufferedReader bufferedReader, BookStore bookStore, BookCart bookCart) {
		ArrayList<Book> foundBooks = searchBooksByAttributes(bufferedReader, bookStore);
		if (foundBooks != null) {
			bookCart.removeFromCart(foundBooks.get(0));
			System.out.println("Book removed from cart.");	
		} else {
			System.out.println("No books found.");
		}
	}
	
	public void buyBooks (BookStore bookStore, BookCart bookCart) {
		
		int[] status = bookStore.buy(new Utils().bookArrayListToArray(bookCart.listCart()));
		
		int booksBought = 0;
		int booksNotInStock = 0;
		int booksNotFound = 0;
		BigDecimal sum = new BigDecimal(0);
		
		for (int i = 0; i < status.length; i += 1) {
			switch (status[i]) {
			case 0:
				sum = sum.add(bookCart.listCart().get(i).getPrice());
				booksBought++;
				break;
			case 1:
				booksNotInStock++;
				break;
			case 2:
				booksNotFound++;
				break;
			default:
				break;
			}
		}
		
		System.out.println("Nr of books bought: " + booksBought);
		System.out.println("Nr of books not in stock: " + booksNotInStock);
		System.out.println("Nr of books not found: " + booksNotFound);
		System.out.println("");
		System.out.println("Total price of all bought books: " + sum);
	}
	
	public void addBookToStore(BufferedReader bufferedReader, BookStore bookStore) {
		Utils utils = new Utils();
		String title = null;
		String author = null;
		String price = null;
		Integer quantity = null;
				
		System.out.println("Type the book title:");
		title = utils.readString(bufferedReader);
		
		System.out.println("Type the book author:");
		author = utils.readString(bufferedReader);
		
		System.out.println("Type the book price:");
		price = utils.readString(bufferedReader);
		
		System.out.println("Type quantity:");
		quantity = Integer.parseInt(utils.readString(bufferedReader));
		
		bookStore.add(new Book(new String[]{title, author, price}), quantity); 
	}
	
	private ArrayList<Book> searchBooksByAttributes(BufferedReader bufferedReader, BookStore bookStore) {
		System.out.println("Type the book title exactly:");
		String searchString = null;
		Utils utils = new Utils();
		
		searchString = utils.readString(bufferedReader);
		
		ArrayList<Book> foundBooks = findBooksByTitle(searchString, bookStore.list(""));
		
		if (foundBooks.size() == 0) {
			return null;
		}
		
		System.out.println("Type the book author exactly:");
		searchString = utils.readString(bufferedReader);
		
		foundBooks = findBooksByAuthor(searchString, utils.bookArrayListToArray(foundBooks));
		
		if (foundBooks.size() == 0) {
			return null;
		}
		
		System.out.println("Type the book price exactly:");
		searchString = utils.readString(bufferedReader);
		
		foundBooks = findBooksByPrice(utils.stringToBigDecimal(searchString), 
			utils.bookArrayListToArray(foundBooks));
		
		if (foundBooks.size() == 0) {
			return null;
		}
		
		return foundBooks;
	}
	
	private ArrayList<Book> findBooksByTitle(String title, Book[] books) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
	
	private ArrayList<Book> findBooksByAuthor(String author, Book[] books) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getAuthor().equals(author)) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
	
	private ArrayList<Book> findBooksByPrice(BigDecimal price, Book[] books) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getPrice().equals(price)) {
				foundBooks.add(book);
			}
		}
		
		return foundBooks;
	}
	
	private void listBooks(BookStore bookStore, String searchString) {
		for (Book book : bookStore.list(searchString)) {
			printBook(book);
		}
	}
	
	private void printBook(Book book) {
		System.out.println("Title: " + book.getTitle() + "\t" + 
			"Author: " + book.getAuthor() + "\t" + "Price: " + book.getPrice());
	}
}
