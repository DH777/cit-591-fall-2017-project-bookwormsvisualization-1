package twitter;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;

/**
 * This class stores state name and number of tweets found in that state
 * @author lisa
 *
 */
public class StateTwitterProperty {
	
	private String stateName; 
	private int tweetsCount;
	private List<Status> tweets;
	
	/**
	 * The constructor
	 * @param stateName
	 */
	public StateTwitterProperty(String stateName) {
		this.stateName = stateName;
		tweetsCount = 0;
		tweets = new ArrayList<Status>();
	}

	/**
	 * Increases the count for search query by 1
	 */
	public void increaseTweetsCount() {
		tweetsCount += 1;
	}

	/**
	 * Gets the name of the state
	 * @return the name of the state
	 */
	public String getName() {
		return stateName;
	}
	
	/**
	 * Getter for tweetsCount
	 * @return number of tweets found in the state
	 */
	public int getTweetsCount() {
		return tweetsCount;
	}

	/**
	 * Adds a list of Status objects to the state
	 * @param tweets list of Status objects
	 */
	public void addTweetsList(List<Status> tweets) {	 
		tweets.addAll(tweets);
	}
	
	/**
	 * Add one tweet into the list
	 * @param tweet the tweet to be added
	 */
	public void addTweet(Status tweet) { 
		tweets.add(tweet);
	}
	
	/**
	 * Returns the list of Status Tweets.
	 * @return the list of Status Tweets.
	 */
	public List<Status> getTweets() {
		return tweets;
	}

}
