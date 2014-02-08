import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class LevelReader {

	private Level level;
	private File text;
	private HashMap<String,Integer> types;
	private GameComponent gameComponent;
	
	private final int SIZE_STRING_POSITION = 1;
	private final int TIME_STRING_POSITION = 2;
	private final int START_STRING_POSITION = 3;
	private final int ON_SCREEN_STRING_POSITION = 4;
	private final int OFF_SCREEN_STRING_POSITION = 5;
	
	public static void main(String[] args){
		//to test level reader
		LevelReader reader = new LevelReader(new File("Levels/level1.txt"));
	}
	
	public LevelReader(File levelFile){
		this.text = levelFile;
		this.level = new Level();
		this.types = new HashMap<String,Integer>();
		
		
		//read the file to make a level
		try {
			Scanner scanner = new Scanner(text);
			StringBuilder fileText = new StringBuilder();
			while(scanner.hasNext()){
				fileText.append(scanner.next());
			}
			scanner.close();
			setup(fileText.toString().split("<"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//set up the types set
		Scanner scanner;
		try {
			scanner = new Scanner(new File("types.txt"));
			
			while(scanner.hasNext()){
				String next = scanner.next();
				this.types.put(next,0);
				System.out.println(next);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		readOffValues();
	}
	
	private void setup(String[] fileText){
		
		//size must be processed first for making GameComponent
		String sizeString = fileText[SIZE_STRING_POSITION];
		String timeString = fileText[TIME_STRING_POSITION];
		String startString = fileText[START_STRING_POSITION];
		String onScreen = fileText[ON_SCREEN_STRING_POSITION];
		String offScreen = fileText[OFF_SCREEN_STRING_POSITION];
		
		processSize(sizeString);
		processTime(timeString);
		processStart(startString);
		processOnScreen(onScreen);
		processOffScreen(offScreen);
		this.gameComponent.createWorld();
	}

	private void processSize(String sizeString){
		
		int startIndex = sizeString.indexOf("[") + 1;
		int endIndex = sizeString.indexOf(">") - 1;
		
		//separates into x and y
		String[] pos = sizeString.substring(startIndex,endIndex).split(",");
		
		int sizeX = Integer.parseInt(pos[0]);
		int sizeY = Integer.parseInt(pos[1]);
		
		this.gameComponent = new GameComponent(sizeX,sizeY);
		
		//TODO remove level stuff
		this.level.setDimensions(new Dimension(sizeX,sizeY));
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
		
		this.gameComponent.setTime(time);
		
		//TODO remove level stuff
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
		
		this.gameComponent.addBall(startX, startY);
		
		//TODO remove level stuff
		this.level.setBallPosition(new Point2D.Double(startX,startY));
	}
	
	private void processOnScreen(String onScreenString){
		
		String[] gameObjects = onScreenString.split("#");		
		
		//Object data contains four fields: type, xPosition, yPosition, tilt
		for(int i = 1; i < gameObjects.length; i++){
			ArrayList<String> objectData = processOnScreenObject(gameObjects[i]);
			
			String type = objectData.get(0);
			int xPosition = Integer.parseInt(objectData.get(1));
			int yPosition = Integer.parseInt(objectData.get(2));
			double tilt = Double.parseDouble(objectData.get(3));
			
			if(type.equals("ball")){
				this.gameComponent.addBall(xPosition, yPosition);
			}
			if(type.equals("fan")){
				this.gameComponent.addFan(xPosition, yPosition,tilt);
			}
			if(type.equals("torch")){
				this.gameComponent.addTorch(xPosition, yPosition, tilt);
			}
			if(type.equals("wood")){
				this.gameComponent.addWood(xPosition, yPosition, tilt);
			}
			if(type.equals("rock")){
				this.gameComponent.addRock(xPosition, yPosition, tilt);
			}
			if(type.equals("gear")){
				this.gameComponent.addGear(xPosition, yPosition, tilt);
			}
			if(type.equals("bounce")){
				this.gameComponent.addBounce(xPosition, yPosition,tilt);
			}
			if(type.equals("gate")){
				this.gameComponent.addGate(xPosition,yPosition,tilt);
			}
			
		}
	}
	
	private ArrayList<String> processOnScreenObject(String objectString){
		
		ArrayList<String> objectData = new ArrayList<String>();
		
		objectData.add(objectString.substring(0,objectString.indexOf("[")));
		
		int start = objectString.indexOf("[") + 1;
		int end = objectString.indexOf("]");
		
		
		//should have three fields: x,y,tilt
		String[] objectFields = objectString.substring(start,end).split(",");
		
		String x = String.valueOf(Integer.parseInt(objectFields[0]));
		String y = String.valueOf(Integer.parseInt(objectFields[1]));
		String tilt = String.valueOf(Double.parseDouble(objectFields[2]));
		
		//add all the fields to the object data
		objectData.add(x);
		objectData.add(y);
		objectData.add(tilt);
		
		return objectData;
	}
	
	private void processOffScreen(String offScreenString){
		
		String[] gameObjects = offScreenString.split("#");
		
		//Object data contains two fields: type, number of the object
		for(int i = 1; i < gameObjects.length; i++){
			ArrayList<String> objectData = processOffScreenObject(gameObjects[i]);
			String type = objectData.get(0);
			
			if(type.equals("fan")){
				this.gameComponent.addToFanCount(Integer.valueOf(objectData.get(1)));
			}
			if(type.equals("bounce")){
				this.gameComponent.addToBounceCount(Integer.valueOf(objectData.get(1)));
			}
		}
		
		this.gameComponent.generateButtons();
	}
	
	private ArrayList<String> processOffScreenObject(String objectString){
		
		ArrayList<String> objectData = new ArrayList<String>();
		
		objectData.add(objectString.substring(0,objectString.indexOf("[")));
		
		int start = objectString.indexOf("[") + 1;
		int end = objectString.indexOf("]");
		
		objectData.add(objectString.substring(start,end));
		
		return objectData;
	}

	
	private void readOffValues(){
		
		int heightWindow = this.level.getDimensions().height;
		int widthWindow = this.level.getDimensions().width;
		int time = this.level.getTime();
		double ballX = this.level.getBallPosition().getX();
		double ballY = this.level.getBallPosition().getY();
		
		System.out.printf("Window Width: %d\nWindow Height: %d\nTime: %d\nBallX: %f\nBallY: %f", widthWindow,heightWindow,time,ballX,ballY);
		System.out.println("\n" + this.types);
		
	}
	
}
