package se.contribe.mattiaspettersson.bookstore.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import se.contribe.mattiaspettersson.bookstore.util.Utils;

public class Book {
	private String title;
	private String author;
	private BigDecimal price;
	
	public Book(String[] args) {
		this.title = args[0];
		this.author = args[1];
		this.price = new Utils().stringToBigDecimal(args[2]);
	}
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override public boolean equals(Object aThat) {
		if ( this == aThat ) return true;
		if ( !(aThat instanceof Book) ) return false;
		 
		Book that = (Book)aThat;
		 
		return
			this.title.equals(that.getTitle()) &&
			this.author.equals(that.getAuthor()) &&
			this.price.equals(that.getPrice());
	}
}
