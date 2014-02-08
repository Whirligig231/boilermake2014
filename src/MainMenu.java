import java.awt.BorderLayout;
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
		JLabel label = new JLabel("Gravity n Stuff");
		label.setFont(new Font("Serif", Font.PLAIN, 40));
		JPanel labelPane = new JPanel();
		labelPane.setSize(600,100);
		labelPane.add(label);
		this.nametxt = new JTextField();
		this.nametxt.setText("Player 1");
		name.add(this.nametxt);
		
		GridLayout grid  = new GridLayout(2,2,10,10);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(600,200);
		buttonPanel.setLayout(grid);
		menuPanel.setLayout(new BorderLayout());
		menuPanel.add(labelPane,BorderLayout.NORTH);
		buttonPanel.add("start",new startButton());
		buttonPanel.add("instr",new instrButton());
		buttonPanel.add("options",new JButton());
		buttonPanel.add("exit", new exitButton());
		menuPanel.add(name,BorderLayout.CENTER);
		
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
			this.setText("Start");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new LevelMenu(MainMenu.this.nametxt.getText(),MainMenu.this.opts);
					
				}
			};
			this.addActionListener(start);
		}
		
	}
	private class instrButton extends JButton{
		public instrButton(){
			super();
			this.setText("Instructions");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,"Burn in hell infidile");
					
				}
			};
			this.addActionListener(start);
		}
	}
	private class exitButton extends JButton{
		public exitButton(){
			super();
			this.setText("Exit");
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
			};
			this.addActionListener(start);
		}
	}	

}
