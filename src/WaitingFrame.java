import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WaitingFrame extends JFrame {

	public WaitingFrame(){
		super();
		JLabel mess = new JLabel("Waiting for other player");
		JButton butt = new JButton("Continue");
		butt.setEnabled(false);
		butt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		JPanel pane = new JPanel();
		pane.add(mess,BorderLayout.CENTER);
		pane.add(butt,BorderLayout.SOUTH);
		this.add(pane);
		this.setVisible(true);
	}
	
}
