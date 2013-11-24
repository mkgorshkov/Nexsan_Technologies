

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads the information from the file in order to process it to figure out the
 * occurrence of required phrases.
 * 
 * @author Maxim
 * 
 */
public class ReadFile {

	// File to process
	private File inFile;
	// Scanner to work with the file
	private Scanner fStream;
	// Holds the full contents of the file
	private String contents;
	// Holds contents of the file after breaking apart with delimiters
	private ArrayList<String> brokenContents;

	// Case insensitive
	private int nexsanAll = 0;
	// Case sensitive
	private int nexsanSensitive = 0;
	// Case insensitive
	private int technologiesAll = 0;
	// Case sensitive
	private int technologiesSensitive = 0;

	/**
	 * Constructor
	 * 
	 * @param f - file passed from driver
	 */
	public ReadFile(File f) {
		// Initialize the file
		inFile = f;
		// Initialize the scanner
		try {
			fStream = new Scanner(f);
		} catch (FileNotFoundException e) {
			// If we cannot find the file, print an error, and quit.
			System.err.println("Cannot initiate input stream. File not found.");
			System.exit(0);
		}
		// Initialize the contents
		contents = "";
		// Initialize where the contents broken up by delimieters will go.
		brokenContents = new ArrayList<String>();
		// Call to get the input
		getInput();
	}

	/**
	 * Takes in data from the file and puts everything into a long String.
	 * Breaks up the input into separate strings based on the delimeters.
	 */
	private void getInput() {
		// Put everything into a large string.
		while (fStream.hasNext()) {
			contents += fStream.nextLine();
		}
		// Break the string into the individual characters
		char[] temp = contents.toCharArray();
		// Assign temporary string.
		String tString = "";
		// Check to see what the next character is
		for (int i = 0; i < temp.length; i++) {
			// If it's valid, keep going
			if (temp[i] < 33 || (temp[i] > 47 && temp[i] < 58)
					|| (temp[i] > 64 && temp[i] < 91)
					|| (temp[i] > 96 && temp[i] < 123)) {
				// If we have a space that could mean that we either have a
				// space between
				// Nexsan and Technologies or we have a delimiter
				if (temp[i] == 32) {
					// Space between technologies
					if (temp[i + 1] == 't' || temp[i + 1] == 'T') {
						// Keep going
						tString += temp[i];
						// Just a delimiter
					} else {

						// Otherwise, we reached a break condition and we can
						// add the
						// temporary string to the list
						brokenContents.add(tString);
						tString = "";
					}
					// We didn't have the space at all, so we keep going
				} else {
					tString += temp[i];
				}

				// Otherwise, we reached a break condition and we can add the
				// temporary string to the list
			} else {
				brokenContents.add(tString);
				tString = "";

			}

		}
		// Close the file, no memory leaks
		fStream.close();
		// Match up the broken up strings with what we want
		matchOccurences();
	}

	/**
	 * We match the occurrences by going through the ArrayList and trying to see
	 * which ones fit the data The case sensitive are nested in the case
	 * insensitive as we don't need to look for the case sensitive, if there is
	 * nothing that we want to match there.
	 */
	private void matchOccurences() {
		for (int i = 0; i < brokenContents.size(); i++) {
			String temp = brokenContents.get(i);

			if (temp.equalsIgnoreCase("Nexsan")) {
				nexsanAll++;
				if (temp.equals("Nexsan")) {
					nexsanSensitive++;
				}
			}

			if (temp.equalsIgnoreCase("Nexsan Technologies")) {
				technologiesAll++;
				if (temp.equals("Nexsan Technologies")) {
					technologiesSensitive++;
				}
			}
		}

		// Print our findings to the console
		System.out
				.println("Number of case sensitive direct matches of \"Nexsan\": "
						+ nexsanSensitive);
		System.out
				.println("Number of case sensitive direct matches of \"Nexsan Technologies\": "
						+ technologiesSensitive);

		System.out
				.println("Number of case insensitive direct matches of \"Nexsan\": "
						+ nexsanAll);
		System.out
				.println("Number of case insensitive direct matches of \"Nexsan Technologies\": "
						+ technologiesAll);

	}

}
