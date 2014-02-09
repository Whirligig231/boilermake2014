import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author doolansr.
 *         Created Feb 7, 2014.
 */
public class MainMenu extends JFrame {
	private JTextField nametxt;
	private Options opts;
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @throws HeadlessException
	 */
	public MainMenu() throws HeadlessException {
		this.opts =  new Options();
		JPanel menuPanel = new JPanel();
		this.setSize(600, 300);
		JPanel name = new JPanel();
		JLabel label = new JLabel("RedBall");
		label.setFont(new Font("Sans", Font.PLAIN, 40));
		label.setForeground(new Color(128,0,0));
		JPanel labelPane = new JPanel();
		labelPane.setSize(600,100);
		labelPane.add(label);
		
		GridLayout grid  = new GridLayout(2,2,10,10);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(600,200);
		buttonPanel.setLayout(grid);
		menuPanel.setLayout(new BorderLayout());
		menuPanel.add(labelPane,BorderLayout.CENTER);
		buttonPanel.add("single",new startButton());
		buttonPanel.add("multi",new multiButton());
		buttonPanel.add("Level edit",new lvlButton());
		buttonPanel.add("inst", new instrButton());
		menuPanel.add(buttonPanel,BorderLayout.SOUTH);
		menuPanel.setVisible(true);
		buttonPanel.setVisible(true);
		this.add(menuPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param gc
	 */
	public MainMenu(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param title
	 * @throws HeadlessException
	 */
	public MainMenu(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param title
	 * @param gc
	 */
	public MainMenu(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	private class startButton extends JButton{
		public startButton(){
			super();
			this.setText("Single");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new LevelMenu("",MainMenu.this.opts);
					
				}
			};
			this.addActionListener(start);
		}
		
	}
	private class instrButton extends JButton{
		public instrButton(){
			super();
			this.setText("Info");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new InfoFrame();
					
				}
			};
			this.addActionListener(start);
		}
	}
	private class multiButton extends JButton{
		public multiButton(){
			super();
			this.setText("Multi");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new MultiFrame();
					
				}
			};
			this.addActionListener(start);
		}
	}	
	private class lvlButton extends JButton{
		public lvlButton(){
			super();
			this.setText("Level Editor");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GameComponent gameComponent = new GameComponent(1200,800);
					LevelBuilder builder = new LevelBuilder(gameComponent);
				}
			};
			this.addActionListener(start);
		}
		
	}
}
