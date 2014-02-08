import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Level {

	private Dimension dimensions;
	private int time;
	private Point2D ballPosition;
	
	public Level(){
		this.dimensions = new Dimension(0,0);
		this.time = 0;
	}
	
//	private ArrayList<Goal> goals;
//	private ArrayList<GameObject> onScreenObjects;
//	private ArrayList<GameObject> offScreenObjects;
	
	public void setDimensions(Dimension dimension){
		this.dimensions = dimension;
	}
	
	public Dimension getDimensions(){
		return this.dimensions;
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
	
	public Point2D getBallPosition(){
		return this.ballPosition;
	}
	
//	public void addGoal(Goal nextGoal){
//		this.goals.add(nextGoal);
//	}
//	
//	public void addOnScreenObject(GameObject nextOnScreenObject){
//		this.onScreenObjects.add(nextOnScreenObject);
//	}
//	
//	public void addOffScreenObject(GameObject nextOffScreenObject){
//		this.offScreenObjects.add(nextOffScreenObject);
//	}
	
	
	
}
