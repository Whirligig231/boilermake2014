import java.util.ArrayList;

import javax.swing.JFrame;


public class LevelBuilder extends JFrame{

	private ArrayList<SimObject> gameParts;
	private GameComponent gameComponent;
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	private int time = 60;
	
	public LevelBuilder(GameComponent gameComponent){
		this.gameComponent = gameComponent;
		this.gameParts = new ArrayList<SimObject>();
		displayObjects();
	}
	
	public void displayObjects(){
		while(true) {
//			if(gameComponent.)
		}
	}

	
	
	
}


