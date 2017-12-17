package test;

import static org.junit.Assert.*;
import twitter4j.*;
import twitter.*;

import org.junit.Before;
import org.junit.Test;

public class TwitterCallerQueryTest {

	@Test
	public void testBeyondSearchLimit() {
		TwitterCaller tc = new TwitterCaller("book", 5001);
		assertNull(tc.query());
	}
	
	@Test
	public void testOnePageResult() {
		TwitterCaller tc = new TwitterCaller("book", 1);
		int i = tc.query().size();
		assertEquals(i, 100);
	}
	
	@Test
	public void testMoreThanOnePageResult() {
		TwitterCaller tc = new TwitterCaller("book", 3);
		int i = tc.query().size();
		assertEquals(i, 300);
	}
	
	@Test
	public void testTwitterException() {
		TwitterCaller tc = new TwitterCaller("asdfaswerbxcyvx", 3);
		int i = tc.query().size();
		System.out.println(i);
	}
	

}
