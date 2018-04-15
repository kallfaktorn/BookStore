package se.contribe.mattiaspettersson.bookstore.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import se.contribe.mattiaspettersson.bookstore.model.Book;

public class Utils {
	public Book[] bookArrayListToArray(ArrayList<Book> books) {
		Book bookArray[] = new Book[books.size()];
		return books.toArray(bookArray);
	}
	
	public String readString(BufferedReader bufferedReader) {
		String searchString = null;
		try {
			searchString = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println("There was an error reading the line.");
			e.printStackTrace();
		}
		return searchString;
	}
	
	public BigDecimal stringToBigDecimal(String value) {
		return  new BigDecimal(value.replace(",", ""));
	}
}
