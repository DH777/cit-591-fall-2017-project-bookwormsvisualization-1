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
	
	String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
			"Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
			"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
			"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
			"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
			"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
			"West Virginia", "Wisconsin", "Wyoming", "Puerto Rico" };
	private StateTweetCounter stc;
	String searchTerm;
	
	/**
	 * The constructor
	 * @param stt 
	 * @param searchTerm
	 */
	public JSWriter(StateTweetCounter stc, String searchTerm){
		this.stc =stc;
		this.searchTerm = searchTerm;
	}

	/**
	 * This method update the JS file with total number of tweets.
	 * @param line sample js line
	 * @return updated js line with number of tweets
	 */
	private String writeJS(String line){
		// the sample data use population density in the js file
		for(int i = 0; i < states.length; i++ ){			
			if (line.contains("density")){
				line = line.replaceAll("\"density\":\\d+\\.?(\\d+)?","\"tweets\""+":" + 0 );
		    } 
			
			if(line.contains(states[i])){
				int qc = stc.getQueryCount(states[i]);
				//update tweets number
				if (line.contains("tweets")){
					line = line.replaceAll("\"tweets\":\\d+\\.?(\\d+)?","\"tweets\""+":" + qc );
					return line;
				} 
			}
		}
	    return line;
	}
	
	/**
	 * This method takes an input file of "us-states.js" and calls writeJS to overwrite. 
	 * The result is a file called "us-states-v2.js".
	 */
	public void updateJS(){
		try {
			File inputFile = new File("us-states.js");
			Scanner in = new Scanner(inputFile);
			//The output is saved in a different file to avoid overwriting
			PrintWriter out = new PrintWriter("us-states-v2.js");

			while (in.hasNextLine()) {
				String line = in.nextLine();	
				line = writeJS(line);
				out.println(line);	
			}		
			in.close();
			out.close();
			
		} catch (Exception e) {
			System.out.println("us-states.js is not in the directory.");
			System.exit(0);
		}
	}
}
