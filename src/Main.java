import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;


/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author doolansr.
 *         Created Feb 8, 2014.
 */
public class Main {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
		}
		new MainMenu();

	}

}
