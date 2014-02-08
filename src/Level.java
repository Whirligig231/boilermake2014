import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Level {

	private int size;
	private int time;
	private Point2D ballPosition;
	
	private ArrayList<Goal> goals;
	private ArrayList<SimObject> onScreenObjects;
	private ArrayList<SimObject> offScreenObjects;
	
	public Level(){
		this.size = 0;
		this.time = 0;
		this.ballPosition = null;
		this.goals = new ArrayList<Goal>();
		this.onScreenObjects = new ArrayList<SimObject>();
		this.offScreenObjects = new ArrayList<SimObject>();
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public void setBallPosition(Point2D ballPosition){
		this.ballPosition = ballPosition;
	}
	
	public void addGoal(Goal nextGoal){
		this.goals.add(nextGoal);
	}
	
	public void addOnScreenObject(SimObject nextOnScreenObject){
		this.onScreenObjects.add(nextOnScreenObject);
	}
	
	public void addOffScreenObject(SimObject nextOffScreenObject){
		this.offScreenObjects.add(nextOffScreenObject);
	}
	
	
	
}
