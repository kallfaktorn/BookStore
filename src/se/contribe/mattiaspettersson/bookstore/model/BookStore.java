package se.contribe.mattiaspettersson.bookstore.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import se.contribe.mattiaspettersson.bookstore.util.Utils;

public class BookStore implements BookList {

	private Map<Book, Integer> booksAndCounts;
	
	
	public BookStore() {
		booksAndCounts = new HashMap<Book, Integer>();
	}
	
	public BookStore(HashMap<Book, Integer> booksAndCounts) {
		this.booksAndCounts= booksAndCounts;
	}
	
	@Override
	public Book[] list(String searchString) {
		String searchStringToLower = searchString.toLowerCase();
		ArrayList<Book> matches = new ArrayList<Book>();		
			
		for (Book book : booksAndCounts.keySet()) {
			String title = book.getTitle().toLowerCase();
			String author = book.getAuthor().toLowerCase();
			
			if (title.contains(searchStringToLower) || author.contains(searchStringToLower)) {
				matches.add(book);
			}
		}
				
		return new Utils().bookArrayListToArray(matches);
	}

	@Override
	public boolean add(Book book, int quantity) {
		Integer count = booksAndCounts.get(book);
		if (count != null) {
			booksAndCounts.put(book, count + quantity);
			return true;
		} else {
			booksAndCounts.put(book, quantity);
			return true;
		}
	}

	@Override
	public int[] buy(Book... books) {
		
		ArrayList<Integer> status = new ArrayList<Integer>();
		for (Book book : books) {
			Book foundBook = null;
			for (Map.Entry<Book, Integer> entry : booksAndCounts.entrySet()) {
				Book key = entry.getKey();
				if (key.equals(book)) {
					foundBook = key;
					break;
				}
			}
			
			if (foundBook != null) {
				if (booksAndCounts.get(foundBook) > 0) {
					booksAndCounts.replace(foundBook, booksAndCounts.get(foundBook) - 1);
					status.add(0);
				} else {
					status.add(1);
				}
			} else {
				status.add(2);
			}
		}
		
		int statusArray[] = new int[status.size()];
		for (int i = 0; i < status.size(); i++) {
			statusArray[i] = status.get(i);
		}
		
		return statusArray;
	}
}
