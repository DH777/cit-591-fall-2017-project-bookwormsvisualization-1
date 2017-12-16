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
		
		//create a new TwitterCaller object
		int numberOfPages = 50;
		TwitterCaller bookSearch = new TwitterCaller(searchTerm, numberOfPages);
		
		//run search and saves the tweets to a linked list
		List<Status> queryTweets = new LinkedList<Status>();
		queryTweets.addAll(bookSearch.query()); 
		
		//Parses tweets and saves counts/tweets to the StateTweetCounter
		TweetParser tweetsParser = new TweetParser(queryTweets);
		StateTweetCounter parsedTweets = tweetsParser.getStatesList();

		//This should take in the parsed tweets and make updates to JS file as needed
		JSWriter js = new JSWriter(parsedTweets, searchTerm);
		js.updateJS();

		File htmlFile = new File("Book-worm-visualization.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch(FileNotFoundException fnfe) {
			System.out.println("The HTML file is missing.");
		} catch(Exception e) {
			System.out.println("Other problem occured.");
		}
	}
}