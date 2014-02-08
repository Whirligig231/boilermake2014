import java.awt.Graphics;

import org.jbox2d.common.Vec2;
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
public class Wood extends SimObject {
	private Vec2 startPosition;
	
	public Wood(int x, int y){
		this.startPosition=new Vec2(x,y);
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
