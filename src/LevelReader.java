import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LevelReader {

	private Level level;
	private File text;
	
	private final int SIZE_STRING_POSITION = 1;
	private final int GOALS_STRING_POSITION = 2;
	private final int TIME_STRING_POSITION = 3;
	private final int START_STRING_POSITION = 4;

	public static void main(String[] args){
		//to test
		LevelReader reader = new LevelReader(new File("Levels/level1.txt"));
	}
	
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
		
		readOffValues();
	}
	
	private void processSize(String sizeString){
		
		int startIndex = sizeString.indexOf("[") + 1;
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
		
		StringBuilder numbersInTime = new StringBuilder();
		
		for(int i = 0; i < timeString.length(); i++){
			if(!Character.isDigit(timeString.charAt(i))){
				continue;
			}
			numbersInTime.append(timeString.charAt(i));
		}
		
		int time = Integer.parseInt(numbersInTime.toString());
		
		this.level.setTime(time);
	}
	
	private void processStart(String startString){
		
		int startIndex = startString.indexOf("[") + 1;
		int endIndex = startString.indexOf("]");
		
		//separates into x and y
		String[] pos = startString.substring(startIndex,endIndex).split(",");
		
		int startX = Integer.parseInt(pos[0]);
		int startY = Integer.parseInt(pos[1]);
		
		System.out.println(startX + " " + startY);
		
		this.level.setBallPosition(new Point2D.Double(startX,startY));
	}	
	
	private void readOffValues(){
		
		int heightWindow = this.level.getDimensions().height;
		int widthWindow = this.level.getDimensions().width;
		int time = this.level.getTime();
		double ballX = this.level.getBallPosition().getX();
		double ballY = this.level.getBallPosition().getY();
		
		System.out.printf("Window Width: %d\nWindow Height: %d\nTime: %d\nBallX: %f\nBallY: %f", widthWindow,heightWindow,time,ballX,ballY);
	}
	
}
