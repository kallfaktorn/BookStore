package se.contribe.mattiaspettersson.bookstore.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AjaxStringRetriever {
	private URL url;
	private String unicodeIn;
	private String unicodeOut;
	
	
	public AjaxStringRetriever() {
		throw new IllegalArgumentException("url needed to retrieve string data.");
	}
	
	public AjaxStringRetriever(String url) {
		init(url, "ISO-8859-1", "UTF-8");
	}
	
	public AjaxStringRetriever(String url, String unicodeIn, String unicodeOut) {
		init(url, unicodeIn, unicodeOut);
	}
	
	private void init(String url, String unicodeIn, String unicodeOut) {
		this.unicodeIn = unicodeIn;
		this.unicodeOut = unicodeOut;
		
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url used to retrieve data was incorrect.");
			System.out.println("Url: " + url);
			e.printStackTrace();
		}
	}
	
	public String retrieve() throws IOException {
		URLConnection conn;
		String out = null;
		try {
			conn = url.openConnection();
			BufferedReader rd = null;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			
			while ((line = rd.readLine()) != null) {
			    sb.append(line + "\n");
			}
			
			String s = sb.toString();
			out = new UnicodeStringConverter(unicodeIn, unicodeOut).convert(s);

			if (rd != null) {
				rd.close();
			}
		} catch (IOException e) {
			System.out.println("AjaxStringRetriever could not establish connection to: " + url.toString());
			e.printStackTrace();
		}

		return out;
	}
}
