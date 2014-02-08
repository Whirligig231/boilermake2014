package boilermake.physgame.physics;

/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public abstract class SimObject implements Comparable<SimObject> {

	private int depth;
	private WorldManager world;

	public abstract void create();
	
	public void destroy() {
		this.getWorld().removeObject(this);
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

}
