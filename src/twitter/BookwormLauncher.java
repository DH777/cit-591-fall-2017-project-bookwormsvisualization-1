package twitter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import twitter4j.Status;

/**
 * Creates the necessary configuration options to launch the map
 * @author lisa
 *
 */
public class BookwormLauncher {
	
	private String searchTerm;
	
	/**
	 * The constructor
	 * @param searchTerm User input book name, default = "Book".
	 */
	public BookwormLauncher(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * This method will run the search, write to JS file and HTML file, then open the map.
	 */
	public void runSearch() {
		
		//Performs search and adds the lists of tweets to the Linked List
		int numberOfPages = 50;
		TwitterCaller bookSearch = new TwitterCaller(searchTerm, numberOfPages);
		
		//Saves the tweets to a linked list
		List<Status> queryTweets = new LinkedList<Status>();
		queryTweets.addAll(bookSearch.query()); 
		//Parses tweets and saves counts/tweets to the StateTweetTracker
		TweetParser tweetsParser = new TweetParser(queryTweets);
		StateTweetCounter parsedTweets = tweetsParser.getStatesList();

		//This should take in the parsed tweets and make updates to JavaScript and HTML files as needed
		JSWriter js = new JSWriter(parsedTweets, searchTerm);

		js.outJS();


		HTMLWriter html = new HTMLWriter(searchTerm);

		html.outHTML();

		File htmlFile = new File("Book-worm-vitialization.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch(FileNotFoundException fnfe) {
			System.out.println("The HTML file is missing. Please find on your desktop and open.");
		} catch(Exception e) {
			System.out.println("Other problem occured");
		}
	}
}