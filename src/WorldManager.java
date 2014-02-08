

import java.util.TreeSet;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 7, 2014.
 */
public class WorldManager {
	
	// World's gravity
	private static final Vec2 GRAVITY = new Vec2(0.0f,-10.0f);

	private World physicsWorld;
	private TreeSet<SimObject> allObjects;
	
	public WorldManager() {
		// Will work this out later
	}
	
	public void addObject(SimObject object) {
		object.setWorld(this);
		this.allObjects.add(object);
		object.create();
	}
	
	public void removeObject(SimObject object) {
		this.allObjects.remove(object);
	}
	
}
