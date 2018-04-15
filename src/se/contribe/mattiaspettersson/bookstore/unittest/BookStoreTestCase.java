package se.contribe.mattiaspettersson.bookstore.unittest;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import se.contribe.mattiaspettersson.bookstore.model.Book;
import se.contribe.mattiaspettersson.bookstore.model.BookCart;
import se.contribe.mattiaspettersson.bookstore.model.BookStore;
import se.contribe.mattiaspettersson.bookstore.util.Utils;

class BookStoreTestCase {

	private final String[] bookAttributes = new String[]{"Mastering едц","Average Swede","762.00"};
	
	@Test
	void testAddBookToStore() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","762.00"}), 0);
		assert(bookStore.list("").length == 1);
	}
	
	@Test
	void testAddedBookToStoretest() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{bookAttributes[0], bookAttributes[1], bookAttributes[2]}), 0);
		
		Book book = bookStore.list("")[0];
		assert(book.getTitle().equals(bookAttributes[0]) && 
			book.getAuthor().equals(bookAttributes[1]) && 
			book.getPrice().equals(new Utils().stringToBigDecimal(bookAttributes[2])));
	}

	@Test
	void testSearchBookByAuthor() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","762.00"}), 0);
		bookStore.add(new Book(new String[]{"Generic Title","First Author","185.50"}), 1);
		bookStore.add(new Book(new String[]{"How To Spend Money","Rich Bloke","1,000,000.00"}), 4);
		assert(bookStore.list("loke").length == 1);
	}

	@Test
	void testSearchBookByTitle() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","762.00"}), 0);
		bookStore.add(new Book(new String[]{"Generic Title","First Author","185.50"}), 1);
		bookStore.add(new Book(new String[]{"How To Spend Money","Rich Bloke","1,000,000.00"}), 4);
		assert(bookStore.list("pend").length == 1);
	}
	
	@Test
	void testListAllBooks() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","762.00"}), 0);
		bookStore.add(new Book(new String[]{"Generic Title","First Author","185.50"}), 1);
		bookStore.add(new Book(new String[]{"How To Spend Money","Rich Bloke","1,000,000.00"}), 4);
		assert(bookStore.list("").length == 3);
	}
	
	@Test
	void testAddBookToCart() {
		BookCart bookCart = new BookCart();
		bookCart.addToCart(new Book(new String[]{"Mastering едц","Average Swede","762.00"}));
		assert(bookCart.listCart().size() == 1);
	}
	
	@Test
	void testRemoveBookFromCart() {
		BookCart bookCart = new BookCart();
		bookCart.addToCart(new Book(new String[]{"Mastering едц","Average Swede","762.00"}));
		bookCart.addToCart(new Book(new String[]{"Generic Title","First Author","185.50"}));
		bookCart.addToCart(new Book(new String[]{"How To Spend Money","Rich Bloke","1,000,000.00"}));
		bookCart.removeFromCart(new Book(new String[]{"Generic Title","First Author","185.50"}));
		assert(bookCart.listCart().size() == 2);
	}
	
	@Test
	void testBuyBooks() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","762.00"}), 0);
		bookStore.add(new Book(new String[]{"Generic Title","First Author","185.50"}), 1);
		bookStore.add(new Book(new String[]{"How To Spend Money","Rich Bloke","1,000,000.00"}), 4);
		
		BookCart bookCart = new BookCart();
		bookCart.addToCart(new Book(new String[]{"Mastering едц","Average Swede","762.00"}));
		bookCart.addToCart(new Book(new String[]{"How To Spend Money","Rich Bloke","1,000,000.00"}));
		bookCart.addToCart(new Book(new String[]{"test","Rich Bloke2","1.00"}));
		
		int[] status = bookStore.buy(new Utils().bookArrayListToArray(bookCart.listCart()));
		
		boolean statusCheck = ((status[0] == 1) && (status[1] == 0) && (status[2] == 2));
		assert(statusCheck);
	}
	
	@Test
	void testBookPrice() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","1,000,000.00"}), 0);
		assert(bookStore.list("Master")[0].getPrice().compareTo(new BigDecimal(1000000)) == 0);
	}
	
	@Test
	void testSwedishCharacters() {
		BookStore bookStore = new BookStore();
		bookStore.add(new Book(new String[]{"Mastering едц","Average Swede","1,000,000.00"}), 0);
		assert(bookStore.list("едц").length == 1);
	}
}
