package twitter;

import java.util.Scanner;

/**
 * This is the Visualizer.
 * It has a default search term of "Book".
 * If user prefer to enter a search term of the book he/she choose,
 * he/she will get a tweet-map of that book.
 * @author lisa
 *
 */
public class BookwormVisualizer {

	public static void main(String[] args) {
		
		System.out.println("You'll be able to see where your fellow book worms tweets! Hooray!");
		System.out.println("Please wait, system processing...");
		System.out.println("Please be patient. We are almost there!\n");

		//get user input of book name
		Scanner in = new Scanner(System.in);
		System.out.println("Would you like to search a book that you're interested in? Y/N");
		String selection = in.nextLine();
		
		String term = null;
		
		if(selection.equalsIgnoreCase("Y")) {
			System.out.println("Please enter the book name");
			term = in.nextLine();
		} else {
			//default
			term = "book";
		}
		
		BookwormLauncher bl = new BookwormLauncher(term);
		bl.runSearch();
		in.close();
	}
}
