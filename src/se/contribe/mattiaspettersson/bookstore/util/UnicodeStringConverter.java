package se.contribe.mattiaspettersson.bookstore.util;

import java.io.UnsupportedEncodingException;

public class UnicodeStringConverter {
	String unicodeIn;
	String unicodeOut;
	
	UnicodeStringConverter() {
		throw new IllegalArgumentException("unicodeIn and unicodeOut needed");
	}
	
	UnicodeStringConverter(String unicodeIn, String unicodeOut) {
		this.unicodeIn = unicodeIn;
		this.unicodeOut = unicodeOut;
	}
	
	String convert(String in) {
		String out = null;
		try {
			out = new String(in.getBytes(unicodeIn), unicodeOut);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Unsupported unicodes. Check unicodeIn and unicodeOut.");
			e.printStackTrace();
		}
		return out;
	}
}
