import javax.swing.JOptionPane;

import org.jbox2d.common.Vec2;

/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 7, 2014.
 */
public class DummyClass {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Vec2 a = new Vec2(4,5);
		Vec2 b = new Vec2(7,1);
		System.out.println("Dot product is "+Vec2.dot(a,b));
	}

}
