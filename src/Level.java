import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Level {

	private int size;
	private int time;
	private Point2D ballPosition;
	
	private ArrayList<Goal> goals;
	private ArrayList<GameObject> onScreenObjects;
	private ArrayList<GameObject> offScreenObjects;
	
	public Level(){
		this.size = 0;
		this.time = 0;
	}
	
	
	
}
