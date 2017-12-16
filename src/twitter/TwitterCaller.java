package twitter;
import java.util.List;
import java.util.Map;
import twitter4j.*;

/**
 * This class will call the twitter API and get search result.
 * @author lisa
 *
 */
public class TwitterCaller {
	
	public static final Twitter twitter = ConfigFile.createTwitterObject();
	
	private String searchTerm;
	private double numberOfPages;
	
	/**
	 * The constructor
	 * @param searchTerm The term on which the search is based
	 * @param numberOfPages limit search pages
	 */
	public TwitterCaller(String searchTerm, int numberOfPages) {
		this.searchTerm = searchTerm;
		this.numberOfPages = numberOfPages;
	}
	
	/**
	 * This method returns a list of relevant Tweets matching a specific query.
	 * @return totalTweets | list of Status objects containing the searched Tweets
	 */
	public List<Status> query() {
		
		Query query = new Query(searchTerm); 
	
		query.setLang("en"); 
		query.setResultType(Query.RECENT);
		// sets query's count to 100 Tweets - maximum 100 per page
		query.setCount(100); 
		
		// checks if this search will hit Twitter API's search limit
		if (getSearchLimit(false) >= numberOfPages) {
			try {
				// calls Twitter's API to search based on the query
				QueryResult result = twitter.search(query);
				
				// extracts Tweets from the query result
				List<Status> totalTweets = result.getTweets();

				numberOfPages--;
				
				// iterates search for additional pages
				while (result.hasNext() && numberOfPages > 0) {
					query = result.nextQuery(); // creates formatted query for the next page
					result = twitter.search(query);
					totalTweets.addAll(result.getTweets());
				
					numberOfPages--;
				}
				
				return totalTweets;
				
			} catch (TwitterException e) {
				System.out.println("Error getting query result!");
			}
			
		} else {
			System.out.println("Please set lower number of Pages");
			getSearchLimit(true);
		}
		
		return null;
	}
	
	/**
	 * This method returns the number of remaining Twitter search requests and can print relevant information.
	 * @param print, true to print remaining Twitter search requests and time until search limit reset
	 * @return the number of remaining Twitter search requests
	 */
	private int getSearchLimit(boolean print) {
		try {
			// calls Twitter's API to get the rate limits
			Map<String, RateLimitStatus> rateLimits = twitter.getRateLimitStatus();
			// extracts the search rate limit
			RateLimitStatus searchLimit = rateLimits.get("/search/tweets");
			
			if (print) {
				System.out.println(searchLimit.getRemaining() + "/" + searchLimit.getLimit() + " requests remaining.");
				System.out.println(searchLimit.getSecondsUntilReset() + " seconds until search limit reset.");
			}
			
			return searchLimit.getRemaining();
			
		} catch (TwitterException e) {
			System.out.println("Error getting rate limit!");
		}
		
		return 0;
	}

}