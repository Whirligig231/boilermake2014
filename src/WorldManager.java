

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.TreeSet;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
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
	// Physics FPS
	private static final float PHYSICS_FPS = 50.0f;

	private World physicsWorld;
	/**
	 * Returns the value of the field called 'physicsWorld'.
	 * @return Returns the physicsWorld.
	 */
	public World getPhysicsWorld() {
		return this.physicsWorld;
	}

	private TreeSet<SimObject> allObjects;
	
	/**
	 * Returns the value of the field called 'allObjects'.
	 * @return Returns the allObjects.
	 */
	public TreeSet<SimObject> getAllObjects() {
		return this.allObjects;
	}

	private boolean running = true;
	private float accumulatedPhysTime = 0.0f;
	
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
	
	private void performStep() {
		this.physicsWorld.step((float)(1.0/PHYSICS_FPS),6,2);
		for (SimObject object : this.allObjects) object.step();
	}
	
	public void performDraw(Graphics g) {
		for (SimObject obj : this.allObjects) {
			obj.draw(g);
		}
	}
	
	public void stepLoop() {
		while (this.running) {
			float startTimeMs = System.nanoTime()/1000000.0f;
			float endTimeMs = startTimeMs + 1000.0f/PHYSICS_FPS;
			this.performStep();
			float currentTimeMs = System.nanoTime()/1000000.0f;
			while (currentTimeMs < endTimeMs) {
				currentTimeMs = System.nanoTime()/1000000.0f;
				// Nothing else here ...
			}
		}
	}
	
	public SimObject getObject(Vec2 point) {
		for (SimObject obj : this.allObjects.descendingSet()) {
			Fixture fix = obj.getBody().getFixtureList();
			if (fix.testPoint(point)) return obj;
		}
		return null;
	}

	/**
	 * Returns the value of the field called 'running'.
	 * @return Returns the running.
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets the field called 'running' to the given value.
	 * @param running The running to set.
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void start() {
		this.setRunning(true);
	}
	
	public void stop() {
		this.setRunning(false);
		this.resetObjectPositions();
	}

	private void resetObjectPositions() {
		for (SimObject obj : this.allObjects) {
			obj.reset();
		}
	}
	
}
