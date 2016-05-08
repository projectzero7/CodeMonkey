package codemonkey;

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

/*
import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;

public class ResultBuilder {
	public static String query;
	
	public ResultBuilder(String q){
		query = q;
		
	}
	
	public void buildResult(String query){
		
	}
	

	private static List<String> searchGoogle( String query ) throws Exception {
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
	

	private static List<String> searchStackOverflowAPI( String resultId ) throws Exception {
	    String stackoverflow = "https://api.stackexchange.com/2.2/questions/";
	    String filters = "/answers?filter=withbody&order=desc&sort=votes&site=stackoverflow";
	    String charset = "UTF-8";

	    URL url = new URL(stackoverflow + resultId + filters);
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


	private static List<String> searchStackOverflowHTML( List<String> urls ) throws Exception {
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
	

	private static boolean isCode(String s){
		double cutoff = .00666;	//.00666 chosen so that a line of code with 1 semicolon must exceed 150 characters to not be considered code.
		double numSemicolons = s.length() - s.replace(";", "").length();
		double numChars = s.length();
		return numSemicolons / numChars >= cutoff;
	}
}
*/
