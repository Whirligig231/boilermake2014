import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class WaitingFrame extends JFrame {
	
	private JButton butt;

	public WaitingFrame(){
		super();
		JLabel mess = new JLabel("   Waiting for other player ...");
		this.butt = new JButton("Continue");
		this.butt.setEnabled(false);
		this.butt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (NetServer.isNet()) {
					new LevelMenu("",null);
				}
				else JOptionPane.showMessageDialog(null,"TO BE IMPLEMENTED");
			}
			
		});
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.setSize(300,100);
		pane.add(mess,BorderLayout.CENTER);
		JPanel pane2 = new JPanel();
		//pane2.add(this.butt);
		pane.add(pane2,BorderLayout.SOUTH);
		this.add(pane);
		this.setSize(300,100);
		this.setVisible(true);
	}
	
	public void enable() {
		//this.butt.setEnabled(true);
		this.dispose();
		if (NetServer.isNet()) {
			new LevelMenu("",null);
		}
		else JOptionPane.showMessageDialog(null,"TO BE IMPLEMENTED");
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param object
	 */
	public void play(MessageLevel object) {
		this.dispose();
		String file = object.levelName;
		File f = new File("./Levels/" + file);
		if (!f.exists()) f = new File("./Levels/" + file + ".txt");
		LevelReader lvl = new LevelReader(f);
	}
	
}
