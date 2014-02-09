import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class MultiFrame extends JFrame {
	
	public MultiFrame(){
		super();
		JPanel pane = new JPanel();
		String ipstr  = "";
		InetAddress ip;
		  try {
	 
			ip = InetAddress.getLocalHost();
			ipstr = "Current IP address : " + ip.getHostAddress();
	 
		  } catch (UnknownHostException e) {
	 
			e.printStackTrace();
			ipstr = "IP unknown";
		  }
		  
		  JLabel label = new JLabel(ipstr);
		  label.setFont(new Font("Sans", Font.PLAIN, 20));
		  pane.setLayout(new BorderLayout());
		  pane.add(label,BorderLayout.NORTH);
		  JPanel buttpane = new JPanel();
		  GridLayout grid = new GridLayout(1,2,20,20);
		  
		  buttpane.setLayout(grid);
		  buttpane.add(new HostButt());
		  buttpane.add(new JoinButt());
		  pane.add(buttpane, BorderLayout.CENTER);
		  this.add(pane);
		  this.setSize(400,200);
		  this.setVisible(true);
		  
		 
	}
	
	private class HostButt extends JButton{
		public HostButt(){
		super();
		this.setText("Host");
		ActionListener host = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		};
		this.addActionListener(host);
		}
		
		
	}
	
	private class JoinButt extends JButton{
		public JoinButt(){
		super();
		this.setText("Join");
		ActionListener join = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		};
		this.addActionListener(join);
		}
		
		
	}

}
