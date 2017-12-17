package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import twitter.StateTweetCounter;
import twitter.TweetParser;
import twitter.TwitterCaller;
import twitter4j.Status;

public class StateTweetCounterTest {

	private StateTweetCounter  stc;
	private TweetParser tp;
	
	@Before
	public void setUp() throws Exception {
		TwitterCaller tc = new TwitterCaller("book", 3);
		tp = new TweetParser(tc.query());
		stc = new StateTweetCounter();
	}

	@Test
	public void testGetQueryCount() {
		stc = tp.getStatesList();
		int num = stc.getQueryCount("California");
		assertTrue(num > 0);
	}
	
	@Test
	public void testGetTweets() {
		stc = tp.getStatesList();
		List<Status> listOfStatus = stc.getTweets("California");
		assertNotNull(listOfStatus);
	}

}
