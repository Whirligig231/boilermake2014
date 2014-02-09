import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoFrame extends JFrame {

public InfoFrame(){
	super();
	JPanel instrPanel = new JPanel();
	ImageIcon icon = new ImageIcon(" "); 
	JLabel label = new JLabel(); 
	label.setIcon(icon); 
	instrPanel.add(label); 
	this.add(instrPanel);
	
}
}
