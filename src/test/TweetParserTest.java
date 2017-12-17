package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import twitter.TweetParser;
import twitter.TwitterCaller;

public class TweetParserTest {
	private TweetParser tp;
	
	@Before
	public void setUp() throws Exception {
		TwitterCaller tc = new TwitterCaller("book", 5);
		tp = new TweetParser(tc.query());
		
	}

	@Test
	public void testGetStatesList() {
		assertNotNull(tp.getStatesList());
	}
}
