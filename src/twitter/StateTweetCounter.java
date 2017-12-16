package twitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import twitter4j.Status;

/**
 * This class will initialize 52 states 
 * and place their tweet counts and tweets in their property.
 * @author lisa
 *
 */
public class StateTweetCounter {
	private Map<String, StateTwitterProperty> stateTweets;

	/**
	 * The constructor.
	 */
	public StateTweetCounter() {
		stateTweets = new HashMap<String, StateTwitterProperty>();
		createStatesList();
	}

	/**
	 * Initialize list of 52 state names;
	 */
	private void createStatesList() {
		String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
				"Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
				"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
				"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming", "Puerto Rico" };

		for (String state : states) {
			if (!stateTweets.containsKey(state)) {
				stateTweets.put(state, new StateTwitterProperty(state));
			}
		}
	}

	/**
	 * Returns a USAState object for the specified state name
	 * @param state the state name to return
	 * @return StateTwitterProperty object
	 */
	public StateTwitterProperty getState(String state) {
		return stateTweets.get(state);
	}

	/**
	 * Gets the number of the first search term results associated with the
	 * specified state name
	 * @param state the state name to lookup
	 * @return the number of search term hits
	 */
	public int getQueryCount(String state) {
		return getState(state).getTweetsCount();
	}

	/**
	 * Gets the list of TaggedStatus objects associated with the specified state
	 * @param state the state name to lookup
	 * @return all Status objects (Tweets) in that state
	 */
	public List<Status> getTweets(String state) {
		return getState(state).getTweets();
	}

	/**
	 * Gets the list of state names
	 * @return the list of states
	 */
	public Set<String> getStates() {
		return stateTweets.keySet();
	}

	/**
	 * Adds another StateTweetTracker to this object. Joins multiple searches
	 * into one.
	 * @param parsedTweets
	 */
	public void addParsedTweets(StateTweetCounter parsedTweets) {
		for (String state : parsedTweets.getStates()) {
			int count = 0;
			count = parsedTweets.getQueryCount(state);
			
			for (int i = 0; i < count; i++) {
				this.getState(state).increaseTweetsCount();
			}
			this.getState(state).addTweetsList(parsedTweets.getTweets(state));
		}
	}
}
