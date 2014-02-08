/**
 * 
 */
//package src;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * TODO Put here a description of what this class does.
 * 
 * @author Steven. Created Feb 8, 2014.
 */
public class GameComponent extends JComponent {
	ArrayList<SimObject> visual = new ArrayList<SimObject>();
	private JFrame gameFrame;
	private int time;
	//off screen object counters
	private int fanCount;
	private int bounceCount;
	private int torchCount;
	private int woodCount;
	private int rockCount;
	private int gearCount;
//	private int
//	private int
//	private int
	

	public GameComponent(int width, int height) {
		this.gameFrame=new JFrame("Game");
		this.gameFrame.setSize(width,height);
		this.gameFrame.setVisible(true);
		JPanel buttonPanel=new JPanel();
		//Objects Buttons
		//Fan Button
		JButton FanAdder = new JButton("Fan (" + this.fanCount + ")");
		class FanButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.fanCount > 0) {
					GameComponent.this.fanCount--;
					SimObject temp = new Fan(5, 5,0);
					temp.makeMovable(true);
					GameComponent.this.visual.add(temp);
				}
			}		
		}
		FanButtonListner FanListn= new FanButtonListner();
		FanAdder.addActionListener(FanListn);
		buttonPanel.add(FanAdder);
		//Bouncer Button
		JButton BouncerAdder = new JButton("Bouncer (" + this.bounceCount + ")");
		class BouncerButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.bounceCount > 0) {
					GameComponent.this.bounceCount--;
					SimObject temp = new Bounce(5, 5,0);
					temp.makeMovable(true);
					GameComponent.this.visual.add(temp);
				}
			}		
		}
		BouncerButtonListner bounceListn= new BouncerButtonListner();
		BouncerAdder.addActionListener(bounceListn);
		buttonPanel.add(BouncerAdder);
		//Torch Button
				JButton torchAdder = new JButton("Torch (" + this.torchCount + ")");
				class TorchButtonListner implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (GameComponent.this.torchCount > 0) {
							GameComponent.this.torchCount--;
							SimObject temp = new Torch(5, 5,0);
							temp.makeMovable(true);
							GameComponent.this.visual.add(temp);
						}
					}		
				}
				TorchButtonListner torchListn= new TorchButtonListner();
				torchAdder.addActionListener(torchListn);
				buttonPanel.add(torchAdder);
				//Wood Button
				JButton woodAdder = new JButton("Wood (" + this.woodCount + ")");
				class WoodButtonListner implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (GameComponent.this.woodCount > 0) {
							GameComponent.this.woodCount--;
							SimObject temp = new Wood(5, 5,0);
							temp.makeMovable(true);
							GameComponent.this.visual.add(temp);
						}
					}		
				}
				WoodButtonListner woodListn= new WoodButtonListner();
				woodAdder.addActionListener(woodListn);
				buttonPanel.add(woodAdder);
				//Rock Button
				JButton rockAdder = new JButton("Rock (" + this.rockCount + ")");
				class RockButtonListner implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (GameComponent.this.rockCount > 0) {
							GameComponent.this.rockCount--;
							SimObject temp = new Rock(5, 5,0);
							temp.makeMovable(true);
							GameComponent.this.visual.add(temp);
						}
					}		
				}
				RockButtonListner rockListn= new RockButtonListner();
				rockAdder.addActionListener(rockListn);
				buttonPanel.add(rockAdder);
				//Gear Button
				JButton gearAdder = new JButton("Gear (" + this.gearCount + ")");
				class GearButtonListner implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (GameComponent.this.gearCount > 0) {
							GameComponent.this.gearCount--;
							SimObject temp = new Gear(5, 5,0);
							temp.makeMovable(true);
							GameComponent.this.visual.add(temp);
						}
					}		
				}
				GearButtonListner gearListn= new GearButtonListner();
				gearAdder.addActionListener(gearListn);
				buttonPanel.add(gearAdder);
		JScrollPane buttonScroll =new JScrollPane(buttonPanel);
		this.gameFrame.add(buttonScroll,BorderLayout.EAST);
		JPanel gamePanel = new JPanel(){
			@Override
				public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(10,10,10,10);
			}
		
			
		};
		this.gameFrame.add(gamePanel,BorderLayout.CENTER);
			}
	public void setTime(int time){
		this.time=time; 
	}
	public void addBall(int x,int y){
		SimObject temp = new RedBall(x, y);
		this.visual.add(temp);
	}
	//objects starting on screen
	public void addFan(int x, int y,double angle) {
		SimObject temp = new Fan(x, y,angle);
		this.visual.add(temp);
	}

	public void addBounce(int x, int y,double angle) {
		SimObject temp = new Bounce(x, y,angle);
		this.visual.add(temp);
	}
	public void addGear(int x, int y,double angle) {
		SimObject temp = new Fan(x, y,angle);
		this.visual.add(temp);
	}

	public void addTorch(int x, int y,double angle) {
		SimObject temp = new Torch(x, y,angle);
		this.visual.add(temp);
	}
	public void addWood(int x, int y,double angle) {
		SimObject temp = new Wood(x, y,angle);
		this.visual.add(temp);
	}

	public void addRock(int x, int y,double angle) {
		SimObject temp = new Rock(x, y,angle);
		this.visual.add(temp);
	}
	
	
	
	//Object counters (off Screen)
	public void addToFanCount(int fanIncrease) {
		this.fanCount += fanIncrease;
	}
	public void addToBounceCount(int bounceCount) {
		this.bounceCount += bounceCount;
	}
	public void addToTorchCount(int bounceCount) {
		this.torchCount += bounceCount;
	}
	public void addToWoodCount(int bounceCount) {
		this.woodCount += bounceCount;
	}
	public void addToRockCount(int bounceCount) {
		this.rockCount += bounceCount;
	}
	public void addToGearCount(int bounceCount) {
		this.gearCount += bounceCount;
	}
	
}
