package se.contribe.mattiaspettersson.bookstore.load;

import java.util.ArrayList;

import se.contribe.mattiaspettersson.bookstore.model.Book;
import se.contribe.mattiaspettersson.bookstore.model.BookStore;

public class BookStoreParser {

	public BookStore parse(String bookStoreData) {
		
		BookStore bookStore = new BookStore();
		String[] bookChunks = bookStoreData.split("\n");

		if (bookChunks != null && bookChunks.length > 0) {
			for (String bookChunk : bookChunks) {
				String[] bookAttributeChunk = bookChunk.split(";");
				int quantity = Integer.parseInt(bookAttributeChunk[3]);
				bookStore.add(new Book(bookAttributeChunk), quantity);
			}
		} else {
			System.out.println("No books in bookStoreData");
		}
		
		return bookStore;
	}
}