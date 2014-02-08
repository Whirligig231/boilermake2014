/**
 * 
 */
//package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author Steven. Created Feb 8, 2014.
 */
public class GameComponent extends JComponent {
	ArrayList<SimObject> visual = new ArrayList<SimObject>();
	final private int frameWidth;
	final private int frameHeight;
	private int time;
	private int fanCount;
	private int bounceCount;
	

	public GameComponent(int width, int height) {
		// Fan Button
		this.frameHeight=height;
		this.frameWidth=width;
		JButton FanAdder = new JButton("Fan (" + this.fanCount + ")");
		class FanButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.fanCount > 0) {
					GameComponent.this.fanCount--;
					SimObject temp = new Fan(5, 5);
					temp.makeMovable(true);
					GameComponent.this.visual.add(temp);
				}
			}		
		}
		FanButtonListner FanListn= new FanButtonListner();
		FanAdder.addActionListener(FanListn);
		//Bouncer Button
		JButton BouncerAdder = new JButton("Bouncer (" + this.bounceCount + ")");
		class BouncerButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.bounceCount > 0) {
					GameComponent.this.bounceCount--;
					SimObject temp = new Bounce(5, 5);
					temp.makeMovable(true);
					GameComponent.this.visual.add(temp);
				}
			}		
		}
		FanButtonListner BounceListn= new FanButtonListner();
		BouncerAdder.addActionListener(BounceListn);
	}
	public void setTime(int time){
		this.time=time; 
	}

	public void addFan(int x, int y) {
		SimObject temp = new Fan(x, y);
		this.visual.add(temp);
	}
	public void addBall(int x,int y){
		SimObject temp = new RedBall(x, y);
		this.visual.add(temp);
	}
	public void addBounce(int x, int y) {
		SimObject temp = new Bounce(x, y);
		this.visual.add(temp);
	}

	public void addToFanCount(int fanIncrease) {
		this.fanCount += fanIncrease;
		// SimObject temp=new Fan(5,5);
		// temp.makeMovable(true);
		// this.visual.add(temp);
	}

	public void addToBounceCount(int bounceCount) {
		this.bounceCount += bounceCount;
	}

	
}
