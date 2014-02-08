/**
 * 
 */
//package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/* * TODO Put here a description of what this class does.
 * 
 * @author Steven. Created Feb 8, 2014.
 */
public class GameComponent extends JComponent {
	ArrayList<SimObject> visual = new ArrayList<SimObject>();
	private WorldManager theWorld;
	private JFrame gameFrame;
	private int time;
	// off screen object counters
	private int fanCount;
	private int bounceCount;
	private int torchCount;
	private int woodCount;
	private int rockCount;
	private int gearCount;
	private int wallCount;
	// private int
	// private int
	// private int
	private JPanel gamePanel;

	public GameComponent(int width, int height) {
		this.gameFrame = new JFrame("Game");
		this.gameFrame.setSize(width, height);
		this.gameFrame.setVisible(true);

		this.gamePanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(new Color(150, 200, 250));
				g.fillRect(0, 0, 1200, 800);
				if (GameComponent.this.theWorld != null)
					GameComponent.this.theWorld.performDraw(g);
			}

		};
		this.gamePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Nothing!
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// Nothing!
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// Nothing!
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				GameComponent.this.theWorld.handlePress(arg0.getX(),arg0.getY(),
						arg0.getButton());
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				GameComponent.this.theWorld.handleRelease(arg0.getX(),arg0.getY(),
						arg0.getButton());
			}
			
		});
		this.gamePanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				GameComponent.this.theWorld.handleDrag(arg0.getX(),arg0.getY(),
						arg0.getButton());
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// Nothing!
			}

			
		});
		this.gamePanel.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				GameComponent.this.theWorld.handleWheel(arg0.getX(),arg0.getY(),
						arg0.getWheelRotation());
			}

			
		});
		this.gameFrame.add(this.gamePanel, BorderLayout.CENTER);
	}

	public void createWorld() {
		// World creation
		this.theWorld = new WorldManager(this.gamePanel, this.visual);
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void addBall(int x, int y) {
		SimObject temp = new RedBall(x, y);
		this.visual.add(temp);
	}

	// objects starting on screen
	public void addFan(int x, int y, double angle) {
		SimObject temp = new Fan(x, y, angle);
		this.visual.add(temp);
	}

	public void addBounce(int x, int y, double angle) {
		SimObject temp = new Bounce(x, y, angle);
		this.visual.add(temp);
	}

	public void addGear(int x, int y, double angle) {
		SimObject temp = new Gear(x, y, angle);
		this.visual.add(temp);
	}

	public void addTorch(int x, int y, double angle) {
		SimObject temp = new Torch(x, y, angle);
		this.visual.add(temp);
	}

	public void addWood(int x, int y, double angle) {
		SimObject temp = new Wood(x, y, angle);
		this.visual.add(temp);
	}

	public void addWall(int x, int y, double angle) {
		SimObject temp = new Wall(x, y, angle);
		this.visual.add(temp);
	}

	public void addRock(int x, int y, double angle) {
		SimObject temp = new Rock(x, y, angle);
		this.visual.add(temp);
	}

	public void addGate(int x, int y, double angle) {
		SimObject temp = new Gate(x, y, angle);
		this.visual.add(temp);
	}

	// Object counters (off Screen)
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

	public void addToWallCount(int wallCount){
		this.wallCount += bounceCount;
	}
	
	public void generateButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		JButton start = new JButton("Start");
		class StartButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JButton b = (JButton)arg0.getSource();
				WorldManager world = GameComponent.this.theWorld;
				if (world.isRunning()) {
					world.stop();
					b.setText("Start");
				}
				else {
					world.start();
					b.setText("Stop");
				}
			}
		}
		StartButtonListner startListn = new StartButtonListner();
		start.addActionListener(startListn);
		buttonPanel.add(start);
		// Objects Buttons
		// Fan Button
		JButton fanAdder = new JButton("Fan (" + this.fanCount + ")");
		class FanButtonListner implements ActionListener {
			int fans=GameComponent.this.fanCount;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.theWorld.isRunning()) return;
				if (GameComponent.this.fanCount > 0) {
					GameComponent.this.fanCount--;
					SimObject temp = new Fan(5, 5, 0);
					temp.makeMovable(true);
					GameComponent.this.theWorld.addObject(temp);
					((JButton)(arg0.getSource())).setText("Fan ("+GameComponent.this.fanCount
							+")");
				}
			}
		}
		FanButtonListner fanListn = new FanButtonListner();
		fanAdder.addActionListener(fanListn);
		buttonPanel.add(fanAdder);
		// Bouncer Button
		JButton BouncerAdder = new JButton("Bouncer (" + this.bounceCount + ")");
		class BouncerButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.theWorld.isRunning()) return;
				if (GameComponent.this.bounceCount > 0) {
					GameComponent.this.bounceCount--;
					SimObject temp = new Bounce(5, 5, 0);
					temp.makeMovable(true);
					GameComponent.this.theWorld.addObject(temp);
					((JButton)(arg0.getSource())).setText("Bouncer ("+
					GameComponent.this.bounceCount+")");
				}
			}
		}
		BouncerButtonListner bounceListn = new BouncerButtonListner();
		BouncerAdder.addActionListener(bounceListn);
		buttonPanel.add(BouncerAdder);
		// Torch Button
		JButton torchAdder = new JButton("Torch (" + this.torchCount + ")");
		class TorchButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.theWorld.isRunning()) return;
				if (GameComponent.this.torchCount > 0) {
					GameComponent.this.torchCount--;
					SimObject temp = new Torch(5, 5, 0);
					temp.makeMovable(true);
					GameComponent.this.theWorld.addObject(temp);
					((JButton)(arg0.getSource())).setText("Fan ("+GameComponent.this.torchCount
							+")");
				}
			}
		}
		TorchButtonListner torchListn = new TorchButtonListner();
		torchAdder.addActionListener(torchListn);
		buttonPanel.add(torchAdder);
		// Wood Button
		JButton woodAdder = new JButton("Wood (" + this.woodCount + ")");
		class WoodButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.theWorld.isRunning()) return;
				if (GameComponent.this.woodCount > 0) {
					GameComponent.this.woodCount--;
					SimObject temp = new Wood(5, 5, 0);
					temp.makeMovable(true);
					GameComponent.this.theWorld.addObject(temp);
					((JButton)(arg0.getSource())).setText("Wood ("+GameComponent.this.woodCount
							+")");
				}
			}
		}
		WoodButtonListner woodListn = new WoodButtonListner();
		woodAdder.addActionListener(woodListn);
		buttonPanel.add(woodAdder);
		// Rock Button
		JButton rockAdder = new JButton("Rock (" + this.rockCount + ")");
		class RockButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.theWorld.isRunning()) return;
				if (GameComponent.this.rockCount > 0) {
					GameComponent.this.rockCount--;
					SimObject temp = new Rock(5, 5, 0);
					temp.makeMovable(true);
					GameComponent.this.theWorld.addObject(temp);
					((JButton)(arg0.getSource())).setText("Rock ("+GameComponent.this.rockCount
							+")");
				}
			}
		}
		RockButtonListner rockListn = new RockButtonListner();
		rockAdder.addActionListener(rockListn);
		buttonPanel.add(rockAdder);
		// Gear Button
		JButton gearAdder = new JButton("Gear (" + this.gearCount + ")");
		class GearButtonListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (GameComponent.this.theWorld.isRunning()) return;
				if (GameComponent.this.gearCount > 0) {
					GameComponent.this.gearCount--;
					SimObject temp = new Gear(5, 5, 0);
					temp.makeMovable(true);
					GameComponent.this.theWorld.addObject(temp);
					((JButton)(arg0.getSource())).setText("Gear ("+GameComponent.this.gearCount
							+")");
				}
			}
		}
		GearButtonListner gearListn = new GearButtonListner();
		gearAdder.addActionListener(gearListn);
		buttonPanel.add(gearAdder);
		JScrollPane buttonScroll = new JScrollPane(buttonPanel);
		// Wall Button
				JButton wallAdder = new JButton("Wall (" + this.wallCount + ")");
				class WallButtonListner implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (GameComponent.this.theWorld.isRunning()) return;
						if (GameComponent.this.wallCount > 0) {
							GameComponent.this.wallCount--;
							SimObject temp = new Wall(5, 5, 0);
							temp.makeMovable(true);
							GameComponent.this.theWorld.addObject(temp);
							((JButton)(arg0.getSource())).setText("Wall ("+GameComponent.this.wallCount
									+")");
						}
					}
				}
				WallButtonListner wallListn = new WallButtonListner();
				wallAdder.addActionListener(wallListn);
				buttonPanel.add(wallAdder);
		this.gameFrame.add(buttonScroll, BorderLayout.WEST);
	}
}
