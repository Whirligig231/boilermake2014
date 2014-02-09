import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author doolansr.
 *         Created Feb 8, 2014.
 */
public class LevelMenu extends JFrame {

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param text
	 */
	public LevelMenu(String text,Options opts) {
		File[] files  = (new File("./Levels")).listFiles();
		GridLayout grid = new GridLayout(files.length+1,1, 50, 50);
		JPanel pane =  new JPanel();
		pane.setLayout(grid);
		JLabel label = new JLabel("Levels");
		label.setFont(new Font("Serif", Font.PLAIN, 40));
		pane.add(label);
		for(int i = 1; i<files.length+1; i++){
			System.out.println(files[i-1].getName());
			if(files[i-1].getName().contains("."))
				pane.add(new lvlButton((files[i-1].getName().substring(0, files[i-1].getName().indexOf(".")))));
			
			else
				pane.add(new lvlButton(files[i-1].getName()));
		}
		JScrollPane pain = new JScrollPane(pane);
		
		this.add(pain);
		this.setSize(300, 600);
		this.setVisible(true);
	}
	private class lvlButton extends JButton{
		public lvlButton(final String file){
			this.setText(file);
			ActionListener start = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LevelReader lvl = new LevelReader(new File("./Levels/" + file));
					// TODO add level thing
				}
			};
			this.addActionListener(start);
			
		}
	}
}
