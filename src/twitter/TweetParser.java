package twitter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.*;


/**
 * The class will parse the input tweets list from TwitterCaller by state.
 * @author lisa
 *
 */
public class TweetParser {
	private StateTweetCounter stateTweets;
	private List<Status> tweets;
	private Map<String, String> stateNameAbbre;

	/**
	 * The constructor for this class.
	 * @param tweets The list of Twitter4J Status objects to parse
	 * @param queryIndex Either the first or second search term
	 */
	public TweetParser(List<Status> tweets) {
		this.tweets = tweets;
		stateTweets = new StateTweetCounter();
		stateNameAbbre = new HashMap<String, String>();	
	}

	/**
	 * Runs the parsing program to divide tweets by state
	 * @return A StateTweetTracker object with tweets parsed by state
	 */
	public StateTweetCounter getStatesList() {
		initializeStateMap();
		parseTweets();
		return stateTweets;
	}

	/**
	 * As the place will return abbreviation of the states,
	 * the hashmap will help easy lookup.
	 */
	private void initializeStateMap() {
		String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
				"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
				"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
				"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming" };
		String[] stateAbbreviations = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY" };

		for (int i = 0; i < 50; i += 1) {
			stateNameAbbre.put(stateAbbreviations[i], states[i]);
		}
	}
	
	/**
	 * Parses the list of tweets. Ignores non-US as well as Washington D.C. tweets, 
	 * as they do not belong to a US state.
	 */
	private void parseTweets() {
		//List of state abbreviation patterns (i.e. "\bFL\b", "\bPA\b")
		List<Pattern> stateAbbrMatches = new LinkedList<Pattern>();
		for (String stateAbbr : stateNameAbbre.keySet()) {
			stateAbbrMatches.add(Pattern.compile("\\b" + stateAbbr + "\\b"));
		}
		
		//List of state full name patterns (i.e. "\bFlorida\b", "\bPennsylvania\b")
		List<Pattern> stateMatches = new LinkedList<Pattern>();
		for (String state : stateNameAbbre.keySet()) {
			stateMatches.add(Pattern.compile("\\b" + stateNameAbbre.get(state) + "\\b"));
		}

		Matcher match;
		Pattern capital = Pattern.compile("D\\.C\\.");

		for (Status tweet : tweets) {
			boolean foundMatch = false; //used to jump to next tweet if match is found
			Place place = tweet.getPlace();
			User user = tweet.getUser();
			String userLocation = user.getLocation();
			if (place != null) {
				String tweetLocation = place.getFullName();
				if (!tweetLocation.isEmpty()) {
					//Checks tweetLocation against every state abbreviation and full name pattern
					for (Pattern stateAbbrPattern : stateAbbrMatches) {
						match = stateAbbrPattern.matcher(tweetLocation);
						if (match.find()) {
							String state = stateAbbrPattern.toString().substring(2, 4);

							stateTweets.getState(stateNameAbbre.get(state)).increaseTweetsCount();
							stateTweets.getState(stateNameAbbre.get(state)).addTweet(tweet);
							foundMatch = true;

						}

					}
					//Weeds out state capital from fullName searches. 
					match = capital.matcher(tweetLocation);
					if (match.find()) {
						foundMatch = true;
					}

					if (!foundMatch) {
						for (Pattern statePattern : stateMatches) {
							match = statePattern.matcher(tweetLocation);
							if (match.find()) {
								String state = statePattern.toString();
								int len = state.length();
								state = state.substring(2, len - 2);


								stateTweets.getState(state).increaseTweetsCount();
								stateTweets.getState(state).addTweet(tweet);
								foundMatch = true;

							}
						}
					}

				}
			}
			//Weeds out state capital from fullName searches. 
			match = capital.matcher(userLocation);
			if (match.find()) {
				foundMatch = true;
			}
			
			//Checks userLocation against every state abbreviation and full name pattern
			if (!userLocation.isEmpty() && !foundMatch) {
				for (Pattern stateAbbrPattern : stateAbbrMatches) {
					match = stateAbbrPattern.matcher(userLocation);
					if (match.find()) {
						String state = stateAbbrPattern.toString().substring(2, 4);

						stateTweets.getState(stateNameAbbre.get(state)).increaseTweetsCount();
						stateTweets.getState(stateNameAbbre.get(state)).addTweet(tweet);
						foundMatch = true;

					}

				}

				if (!foundMatch) {
					for (Pattern statePattern : stateMatches) {
						match = statePattern.matcher(userLocation);
						if (match.find()) {
							String state = statePattern.toString();
							int len = state.length();
							state = state.substring(2, len - 2);

						
							stateTweets.getState(state).increaseTweetsCount();
							stateTweets.getState(state).addTweet(tweet);
							foundMatch = true;

						}
					}
				}

			}

		}

	}

}
