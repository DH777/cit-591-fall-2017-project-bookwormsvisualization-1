package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import twitter.BookwormLauncher;

public class BookwormLauncherTest {

	@Test
	public void test() {
		try {
			BookwormLauncher bl = new BookwormLauncher("george");
			bl.runSearch();
		}catch(Throwable t) {
			fail();
		}

	}

}
