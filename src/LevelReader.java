import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;


public class LevelReader {

	private Level level;
	private File text;
	
	private final int SIZE_STRING_POSITION = 0;
	private final int GOALS_STRING_POSITION = 1;
	private final int TIME_STRING_POSITION = 2;
	private final int START_STRING_POSITION = 3;
		
	public LevelReader(File levelFile){
		this.text = levelFile;
//		this.level = new Level();
		try {
			Scanner scanner = new Scanner(text);
			StringBuilder fileText = new StringBuilder();
			while(scanner.hasNext()){
				fileText.append(scanner.next());
			}
			setup(fileText.toString().split("<"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructs the Level object and initializes all its variables
	 * @param fileText the text file, broken up by <
	 */
	private void setup(String[] fileText){
		
		String sizeString = fileText[SIZE_STRING_POSITION];
		String goalsString = fileText[GOALS_STRING_POSITION];
		String timeString = fileText[TIME_STRING_POSITION];
		String startString = fileText[START_STRING_POSITION];
		
		
	}
	
	
	
	
	
}
