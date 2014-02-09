import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		
		
		
		ArrayList<SimObject> specialParts = new ArrayList<SimObject>();
		int xPos = 100;
		int yPos = 100;
		double tilt = 100;
		
		
		
		for (int i=0;i<this.gameParts.size()-1;i++){
			if(this.gameParts.get(i).isSpecial()){
				specialParts.add(this.gameParts.get(i));
			}else{
				xPos =(int) this.gameParts.get(i).getBody().getPosition().x;
				yPos =(int) this.gameParts.get(i).getBody().getPosition().y;
				tilt = this.gameParts.get(i).getBody().getAngle();
			}
		}
	}

	private String getInsideObjectString(String name, String xPos, String yPos, String tilt){
		return "#" + name + " [" + xPos + "," + yPos + "," + tilt + "]";
	}

	private String getOutsideObjectString(String name, String number){
		return "#" + name + " [" + number + "]"; 
	}
	
}


