package twitter;

import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

/**
 * This method will configure the Twitter account. 
 * @author recitation + lisa
 *
 */
public class ConfigFile {
	
	/**
	 * Creates new ConfigurationBuilder
	 * @return ConfigurationBuilder object
	 */
	private static ConfigurationBuilder configure() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
				
		// STORE THESE SECRETLY!!!! DO NOT EVER STORE THESE WHERE THEY CAN BE SEEN!!!
		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(Secret.cKey);
		cb.setOAuthConsumerSecret(Secret.cSecret);	

		return cb;
	}
	
	/**
	 * Get twitter application token to search more.
	 * @return TwitterFactory object
	 */
	private static OAuth2Token createFactory() {
		OAuth2Token token = null;
		ConfigurationBuilder cb = configure();
		
		try {
			token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
		} catch (TwitterException e) {
			System.out.println("Error getting OAuth2 token!");
			System.exit(0);
		}
		
		return token;
	}

	/**
	 * Passes in application token.
	 * @return Twitter object
	 */
	public static Twitter createTwitterObject() {
		OAuth2Token token = createFactory();
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(Secret.cKey);
		cb.setOAuthConsumerSecret(Secret.cSecret);
		cb.setOAuth2TokenType(token.getTokenType());
		cb.setOAuth2AccessToken(token.getAccessToken());

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		return twitter;
    }
}