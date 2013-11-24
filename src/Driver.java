import java.io.File;

/**
 * Driver class works directly with input from the user, has the main method.
 * @author Maxim
 */
public class Driver {
	
	//File which contains the data to be parsed
	File inFile;
	//Parser that reads the file
	ReadFile reader;
	
	/**
	 * Constructor - initializes the parser, awaits for a file.
	 */
	public Driver(String fname){
		inFile = new File(fname);
		
		if(inFile.canRead()){
			reader = new ReadFile(inFile);
		}else{
			System.err.println("File cannot be read. Exiting");
			System.exit(0);
		}
	}
	
	/**
	 * If there is an error in starting the program, usage tells
	 * user what kind of format is expected
	 */
	private static void usage(){
		System.err.println("Usage: ");
		System.err.println("\tjava Driver [fileName]");
		System.err.println("\t- [filename] is any binary file.");
		System.exit(0);
	}
	
	
	/**
	 * Main method - takes the filename from the user, initiates the parser.
	 * Otherwise, if a filename cannot be found, displays proper usage.
	 * @param args - argument from console.
	 */
	public static void main(String[] args) {
		if(args.length == 0){
			usage();
		}else{
			Driver driver = new Driver(args[0]);
		}
	}
	
}
