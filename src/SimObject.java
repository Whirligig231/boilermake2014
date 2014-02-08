import java.awt.Graphics;

import org.jbox2d.dynamics.Body;



/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public abstract class SimObject implements Comparable<SimObject> {

	private int depth;
	private WorldManager world;
	private boolean moveable =false;
	
	public abstract void create();
	
	public void destroy() {
		this.getWorld().removeObject(this);
	}
	public void makeMovable(boolean a){
		this.moveable=a;
	}
	
	@Override
	public int compareTo(SimObject other) {
		return other.depth-this.depth;
	}

	/**
	 * Returns the value of the field called 'depth'.
	 * @return Returns the depth.
	 */
	public int getDepth() {
		return this.depth;
	}

	/**
	 * Sets the field called 'depth' to the given value.
	 * @param depth The depth to set.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Returns the value of the field called 'world'.
	 * @return Returns the world.
	 */
	public WorldManager getWorld() {
		return world;
	}

	/**
	 * Sets the field called 'world' to the given value.
	 * @param world The world to set.
	 */
	public void setWorld(WorldManager world) {
		this.world = world;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public abstract void step();

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param g
	 */
	public abstract void draw(Graphics g);

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public abstract Body getBody();

	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public abstract void reset();

}