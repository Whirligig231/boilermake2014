/**
 * 
 */
//package src;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Steven.
 *         Created Feb 8, 2014.
 */
public class GameComponent extends JComponent {
	ArrayList<SimObject> visual=new ArrayList<SimObject>();
	private int fanCount;
	private int bounceCount;
	
	
	public GameComponent(){
		}
	
	public void addFan(int x,int y){
		SimObject temp=new Fan(x,y);
		this.visual.add(temp);
	}
	public void addBounce(int x,int y){
		SimObject temp=new Bounce(x,y);
		this.visual.add(temp);
		}
	public void addToFanCount(int fanIncrease){
		this.fanCount+=fanIncrease;
//		SimObject temp=new Fan(5,5);
//		temp.makeMovable(true);
//		this.visual.add(temp);
	}
	public void addToBounceCount(int bounceCount){
		this.bounceCount+=bounceCount;
	}
	//buttons
	JButton fanAdder;
	
	
}
