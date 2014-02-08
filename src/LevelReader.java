import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LevelReader {

	private Level level;
	private File text;
	
	private final int SIZE_STRING_POSITION = 0;
	private final int GOALS_STRING_POSITION = 1;
	private final int TIME_STRING_POSITION = 2;
	private final int START_STRING_POSITION = 3;
		
	public LevelReader(File levelFile){
		this.text = levelFile;
		this.level = new Level();
		
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
	
	private void setup(String[] fileText){
		
		String sizeString = fileText[SIZE_STRING_POSITION];
		String goalsString = fileText[GOALS_STRING_POSITION];
		String timeString = fileText[TIME_STRING_POSITION];
		String startString = fileText[START_STRING_POSITION];
		
		processSize(sizeString);
		processGoals(goalsString);
		processTime(timeString);
		processStart(startString);
	}
	
	private void processSize(String sizeString){
		
		int startIndex = sizeString.indexOf(" ") + 1;
		int endIndex = sizeString.indexOf(">") - 1;
		
		//separates into x and y
		String[] pos = sizeString.substring(startIndex,endIndex).split(",");
		
		int sizeX = Integer.parseInt(pos[0]);
		int sizeY = Integer.parseInt(pos[1]);
		
		this.level.setDimensions(new Dimension(sizeX,sizeY));
	}
	
	private void processGoals(String goalsString){
		//
	}
	
	private void processTime(String timeString){
		
		int startIndex = timeString.indexOf(" ") + 1;
		int endIndex = timeString.indexOf(">");
		
		int time = Integer.parseInt(timeString.substring(startIndex,endIndex));
		
		this.level.setTime(time);
	}
	
	private void processStart(String startString){
		
		int startIndex = startString.indexOf("[") + 1;
		int endIndex = startString.indexOf("]");
		
		//separates into x and y
		String[] pos = startString.substring(startIndex,endIndex).split(",");
		
		int startX = Integer.parseInt(pos[0]);
		int startY = Integer.parseInt(pos[1]);
		
		this.level.setBallPosition(new Point2D.Double(startX,startY));
	}
	
	
	
}
