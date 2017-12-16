package twitter;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class writes the query values into a new js file
 * @author lisa
 *
 */
public class JSWriter {
	
	String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
			"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
			"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
			"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
			"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
			"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
			"West Virginia", "Wisconsin", "Wyoming" };
	private StateTweetCounter stt;
	String firstTerm;
	String secondTerm;
	
	/**
	 * The constructor for the JSWriter class
	 * @param stt input StateTweetTracker object
	 * @param firstTerm the first search term
	 * @param secondTerm the second search term
	 */
	public JSWriter(StateTweetCounter stt, String firstTerm){
//		The class takes in inputs from both the parser class and user input in order to edit the .js file
		this.stt =stt;
		this.firstTerm = firstTerm;
	}
	/**
	 * This method finds the state name in a js file and edits its tweet data based on values
	 * from that particular state
	 * @param line the line of text scanned by outJS
	 * @return line the line of text with "ratio", "q1", "q2", "qc1", and "qc2" edited by corresponding value
	 */
	public String writeJS(String line){
//		Most lines in the .js file contains data for a state. Therefore, the state name is the first string to check for
		for(int i = 0; i < states.length; i++ ){			
			if (line.contains("density")){
				line = line.replaceAll("\"density\":\\d+\\.?(\\d+)?","\"tweets\""+":" + 0 );
		    } 
			//When a state is identified, query data from StateTweetTracker is pulled
			if(line.contains(states[i])){
//				System.out.println(states[i]);
				int qc = stt.getQuery1Count(states[i]);
//				The string "ratio" is replaced with the query value and "q1" and "q2" are replaced with the search terms
				if (line.contains("tweets")){
					line = line.replaceAll("\"tweets\":\\d+\\.?(\\d+)?","\"tweets\""+":" + qc );
			    return line;
			    } 
			}
		}
	    return line;
	}
	
	/**
	 * This method takes an input file called "us-states.js" and calls writeJS to overwrite the instances of 
	 * "ratio", "q1", "q2", "qc1", and "qc2". The result is a text-output file called "us-states-v2.js"
	 */
	public void outJS(){
		try {
//			The .js file is defined since the class will only be handling this particular file
			File inputFile = new File("us-states.js");
			Scanner in = new Scanner(inputFile);
//			The output is saved in a different file to avoid overwriting
			PrintWriter out = new PrintWriter("us-states-v2.js");
//			Each line in the .js file is iterated through to find the keywords and edited accordingly
			while (in.hasNextLine()) {
				String line = in.nextLine();	
				line = writeJS(line);
				out.println(line);	
			}		
			in.close();
			out.close();
			
		} catch (Exception e) {
//			If an exception is encountered, a message is displayed to the user and the program shuts down
			System.out.println("Error! Make sure us-states.js is in directory!");
			System.exit(0);
		}
	}
}
