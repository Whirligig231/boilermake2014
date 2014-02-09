import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class LevelBuilder extends JFrame{

	private ArrayList<SimObject> gameParts;
	private GameComponent gameComponent;
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	private int time = 60;
	final private int MAXAMOUNTOBJECTS=25; 
	
	public LevelBuilder(GameComponent gameComponent){
		this.gameComponent = gameComponent;
		this.gameComponent.createWorld();
		this.gameParts = new ArrayList<SimObject>();
		this.setUpButtons();
		this.gameComponent.setLevelBuilder(this);
	}
	public void setUpButtons(){
		
		this.gameComponent.addToFanCount(MAXAMOUNTOBJECTS);
		this.gameComponent.addToBounceCount(MAXAMOUNTOBJECTS);
		this.gameComponent.addToTorchCount(MAXAMOUNTOBJECTS);
		this.gameComponent.addToRockCount(MAXAMOUNTOBJECTS);
		this.gameComponent.addToGearCount(MAXAMOUNTOBJECTS);
		this.gameComponent.addToWallCount(MAXAMOUNTOBJECTS);
		this.gameComponent.generateButtons();
		this.gameComponent.makeLevelButton();
		
	}
	public void setGameParts(TreeSet<SimObject> objectsDrawn){
		for (SimObject s:objectsDrawn){
			gameParts.add(s);
		}
	}
	
	public void writeToFile(){
		
		JOptionPane namePrompt = new JOptionPane();
		
		String fileName = namePrompt.showInputDialog("What would you like to name your level?");
		
		//actually create a new file under the level builder's files
		try{
			FileOutputStream stream = new FileOutputStream("./LevelBuilderFiles/" + fileName + ".txt");
			stream.close();
			}
			catch(FileNotFoundException e){
				System.out.println("file not found");
			}
			catch(IOException e){
				System.out.println("io exception");
			}
		
		StringBuilder insides = new StringBuilder();
		insides.append("< on_screen ");
		
		ArrayList<SimObject> specialParts = new ArrayList<SimObject>();
		
		for (int i=0;i<this.gameParts.size()-1;i++){
			String name = "NAME"; // this.gameParts.get(i).getName();
			if(this.gameParts.get(i).isSpecial()){
				specialParts.add(this.gameParts.get(i));
			}else{
				int xPos =(int) this.gameParts.get(i).getBody().getPosition().x;
				int yPos =(int) this.gameParts.get(i).getBody().getPosition().y;
				double tilt = this.gameParts.get(i).getBody().getAngle();
				DecimalFormat df = new DecimalFormat("0.000");
				insides.append(getInsideObjectString(name,String.valueOf(xPos),String.valueOf(yPos),df.format(tilt)));
			}
		}
	
		//after this point the stringbuilder has all of the file's inside objects code
		insides.append(">");

		String sizeString = "<size " + "[" + WIDTH + "," + HEIGHT + "]>";
		String timeString = "<time " + this.time + ">";
		String outsides = "<off_screen #fan ["+MAXAMOUNTOBJECTS+"] #bounce [" + MAXAMOUNTOBJECTS+"] #wood[" + MAXAMOUNTOBJECTS+ "] #torch[" + MAXAMOUNTOBJECTS+ "] #rock[" + MAXAMOUNTOBJECTS+ "] #gear[" + MAXAMOUNTOBJECTS+ "] #wall[" + MAXAMOUNTOBJECTS+ "] >";
		
		System.out.println(sizeString);
		System.out.println(timeString);
		System.out.println(insides.toString());
		System.out.println(outsides);
	}
	
	
	
	
	private String getInsideObjectString(String name, String xPos, String yPos, String tilt){
		return "#" + name + " [" + xPos + "," + yPos + "," + tilt + "]";
	}

	private String getOutsideObjectString(String name, String number){
		return "#" + name + " [" + number + "]"; 
	}
	
}


