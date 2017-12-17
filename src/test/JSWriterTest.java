package test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import twitter.JSWriter;
import twitter.StateTweetCounter;
import twitter.TweetParser;
import twitter.TwitterCaller;
import twitter4j.Status;

public class JSWriterTest {


	@Test
	public void JSWriterNotNull() {
		List<Status> tweets = new LinkedList<Status>();
		TweetParser parser = new TweetParser(tweets);
		StateTweetCounter parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets, "test1");
		assertNotNull(write);
		
	}
	
	@Test
	public void writeJSNotNull() {
		TwitterCaller tc = new TwitterCaller("book", 3);
		List<Status> tweets = tc.query();
		TweetParser parser = new TweetParser(tweets);
		StateTweetCounter parsedTweets = parser.getStatesList();
		JSWriter write = new JSWriter(parsedTweets, "test2");
		String line = "density California tweets";
		write.writeJS(line);
		assertNotNull(write.writeJS(line));
		
	}
	
	@Test
	public void updateJSNotNull() {
		try {
			TwitterCaller tc = new TwitterCaller("book", 3);
			List<Status> tweets = tc.query();
			TweetParser parser = new TweetParser(tweets);
			StateTweetCounter parsedTweets = parser.getStatesList();
			JSWriter write = new JSWriter(parsedTweets, "test3");
			write.updateJS();
		}catch (Throwable t) {
			fail();
		}
	}

}
