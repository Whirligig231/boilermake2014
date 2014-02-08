import java.awt.Graphics;

import org.jbox2d.dynamics.Body;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author Steven.
 *         Created Feb 8, 2014.
 */
public class Bounce extends SimObject {
	private int xcorrd;
	private int ycorrd;
	
	public Bounce(int x,int y){
		this.xcorrd=x;
		this.ycorrd=y;
	}
	
	/* (non-Javadoc)
	 * @see SimObject#create()
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see SimObject#step()
	 */
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see SimObject#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see SimObject#getBody()
	 */
	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see SimObject#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
