package test;

import static org.junit.Assert.*;

import java.util.List;

import twitter.*;
import twitter4j.Status;

import org.junit.Before;
import org.junit.Test;

public class StateTwitterPropertyTest {
	
	private StateTwitterProperty stp;
	
	@Before
	public void setUp() throws Exception {
		stp = new StateTwitterProperty("Pennsylvania"); 
	}

	@Test
	public void testIncreaseTweetsCount() {
		stp.increaseTweetsCount();
		assertEquals(1, stp.getTweetsCount());
	}

	@Test
	public void testGetName() {
		assertEquals("Pennsylvania", stp.getName());
	}
	
	@Test
	public void testAddTweetsList() {
		TwitterCaller tc = new TwitterCaller("book", 1);
		stp.addTweetsList(tc.query());
		assertNotNull(stp.getTweets());
	}
	
	@Test
	public void testAddTweet() {
		TwitterCaller tc = new TwitterCaller("book", 1);
		stp.addTweet(tc.query().get(0));
		assertEquals(1, stp.getTweets().size());
	}
	


}
