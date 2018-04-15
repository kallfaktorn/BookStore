package se.contribe.mattiaspettersson.bookstore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import se.contribe.mattiaspettersson.bookstore.cmd.BookStoreHandler;
import se.contribe.mattiaspettersson.bookstore.load.BookStoreParser;
import se.contribe.mattiaspettersson.bookstore.model.BookCart;
import se.contribe.mattiaspettersson.bookstore.model.BookStore;
import se.contribe.mattiaspettersson.bookstore.util.AjaxStringRetriever;



public class BookStoreApplication {

	public static void main(String[] args) throws IOException {		
		
		// Initialization
		BookStore bookStore = new BookStoreParser().parse(new AjaxStringRetriever(
			"https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt"
		).retrieve());
		BookCart bookCart = new BookCart();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		BookStoreHandler bookStoreHandler = new BookStoreHandler();
		int userType = -1;
		
		do {
			if (userType == 1) {
				bookStoreHandler.printUserMenu();
		        line = in.readLine();
		        
		        switch(line) {
		        case "1":
		        	bookStoreHandler.listAllBooks(bookStore);
		        	break;
		        case "2":
		        	bookStoreHandler.searchBooks(in, bookStore);
		        	break;
		        case "3":
		        	bookStoreHandler.listBookCart(bookCart);
		        	break;
		        case "4":
		        	bookStoreHandler.addBookToCart(in, bookStore, bookCart);
		        	break;
		        case "5":
		        	bookStoreHandler.removeBookFromCart(in, bookStore, bookCart);
		        	break;
		        case "6":
		        	bookStoreHandler.buyBooks(bookStore, bookCart);
		        	break;
		        case "7":
		        	userType = -1;
		        	break;
		        default:
		        	break;
		        } 
			} else if (userType == 2) {
				bookStoreHandler.printAdminMenu();
				line = in.readLine();
				
				switch(line) {
				case "1":
					bookStoreHandler.addBookToStore(in, bookStore);
					break;
				case "2":
					userType = -1;
				default:
					break;
				} 
			} else {
				bookStoreHandler.printRootMenu();
				line = in.readLine();
				switch(line) {
				case "1":
		        	userType = 1;
		        	break;
		        case "2":
		        	userType = 2;
		        	break;
		        default:
		        	userType = -1;
		        	break;
				}
			}
		} while (line.equalsIgnoreCase("quit") == false);
		
	    in.close();
	}
}