import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;

public class CodeMonkey {
	public static void main(String[] args) {
		// API
		try {
			// Get a query from the user
			String query = "java iterate through all characters in a string";
			
			// Get URLs from Google
			List<String> urls = searchGoogle( query );
			
			// Extract IDs from URLs
			List<String> resultIds = new ArrayList<String>();
			for (String s : urls) {
				resultIds.add( s.split("/")[4] );
			}
			
			// Get code samples from StackOverflow
			List<String> code = searchStackOverflowAPI( resultIds );
			
			// Print the results
			for (String c : code) {
				System.out.println( c );
				System.out.println( "--------------------" );
			}
			
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		}
	}

	/**
	 * Get search results from Google API
	 * 
	 * This code was modified from http://stackoverflow.com/a/3727777
	 * 
	 * @param query			raw string query to search for
	 * @return				list of the URLs of the top 4 results
	 * @throws Exception
	 */
	public static List<String> searchGoogle( String query ) throws Exception {
		// Use Google Search API to get the top 4 results
		// TODO: change number of results
	    String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String site = "stackoverflow.com";
	    String charset = "UTF-8";

	    URL url = new URL(google + URLEncoder.encode(query + " site:" + site, charset));
	    Reader reader = new InputStreamReader(url.openStream(), charset);
	    
	    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
	    
	    // Strip the StackOverflow IDs and return them
	    // TODO: generalize
	    List<String> resultIds = new ArrayList<String>();
	    for (int i = 0; i < results.getResponseData().getResults().size(); i++) {
	    	// TODO: sanity check: ensure URL is from StackOverflow.com
	    	resultIds.add( results.getResponseData().getResults().get(i).getUrl() );
	    }

	    return resultIds;
	}
	
	/**
	 * Get code samples from StackOverflow API
	 * 
	 * @param resultIds		list of StackOverflow question IDs
	 * @return				list of code sample answers on pages
	 * @throws Exception
	 */
	public static List<String> searchStackOverflowAPI( List<String> resultIds ) throws Exception {
	    String stackoverflow = "https://api.stackexchange.com/2.2/questions/";
	    String id = "196830";
	    String filters = "/answers?filter=withbody&order=desc&sort=votes&site=stackoverflow";
	    String charset = "UTF-8";

	    URL url = new URL(stackoverflow + id + filters);
	    Reader reader = new InputStreamReader(new GZIPInputStream(url.openStream()), charset);

	    StackOverflowResults results = new Gson().fromJson(reader, StackOverflowResults.class);
	    
	    List<String> code = new ArrayList<String>();
	    for ( String s : results.getBodies() ) {
		    // Cut out the code samples
		    String page = s.toString();
		    String startString = "<pre><code>";
		    String endString = "</code></pre>";
		    
		    while(page.contains(startString)){
		    	// Move to the next occurrence of the tags
		    	int start = page.indexOf(startString) + startString.length();
		    	int end = page.indexOf(endString);
		    	String codeSample = page.substring(start, end);
		    	
		    	// Grab the code between the tags
		    	codeSample = StringEscapeUtils.unescapeHtml4(codeSample);
		    	code.add(codeSample);
		    	// TODO: add automatic citation in comments
		    	
		    	// Throw away the portion we just used
		    	page = page.substring(page.indexOf(endString) + endString.length());
		    }
	    }

	    return code;
	}

	/**
	 * Scrape code samples from StackOverflow URL
	 * 
	 * This code was modified from http://stackoverflow.com/a/13671432
	 * 
	 * @param urls			list of StackOverflow URLs
	 * @return				list of code sample answers on pages
	 * @throws Exception
	 */
	public static List<String> searchStackOverflowHTML( List<String> urls ) throws Exception {
		List<String> code = new ArrayList<String>();
		
		// Collect code samples from all URLs
		for (String query : urls) {
			// Get the HTML
		    URLConnection connection = new URL(query).openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		    connection.connect();
	
		    BufferedReader reader  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
	
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line + "\n");
		    }
		    
		    // Cut out the code samples
		    String page = sb.toString();
		    String startString = "<pre><code>";
		    String endString = "</code></pre>";
		    
		    while(page.contains(startString)){
		    	// Move to the next occurrence of the tags
		    	int start = page.indexOf(startString) + startString.length();
		    	int end = page.indexOf(endString);
		    	String codeSample = page.substring(start, end);
		    	
		    	// Grab the code between the tags
		    	codeSample = StringEscapeUtils.unescapeHtml4(codeSample);
		    	code.add(codeSample);
		    	// TODO: add automatic citation in comments
		    	
		    	// Throw away the portion we just used
		    	page = page.substring(page.indexOf(endString) + endString.length());
		    }
		}
	    
		// Return all the code samples
	    return code;
	}
}
