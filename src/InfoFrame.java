import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class InfoFrame extends JFrame {

public InfoFrame(){
	super();
	this.setSize(850,1000);
	JPanel instrPanel = new JPanel();
	ImageIcon icon = new ImageIcon("./Graphics/instructions.png/"); 
	JLabel label = new JLabel(); 
	label.setIcon(icon); 
	instrPanel.add(label); 
	JScrollPane spane = new JScrollPane(instrPanel);
	this.add(spane);
	this.setVisible(true);
	
}
}
