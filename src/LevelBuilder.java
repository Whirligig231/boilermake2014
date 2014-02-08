import java.util.ArrayList;

import javax.swing.JFrame;


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

	
	
	
}


