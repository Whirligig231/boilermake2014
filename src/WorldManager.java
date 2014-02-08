

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.util.Collection;
import java.util.TreeSet;

import javax.swing.JPanel;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 7, 2014.
 */
public class WorldManager implements Runnable {
	
	// World's gravity
	public static final Vec2 GRAVITY = new Vec2(0.0f,10.0f);
	// Physics scale
	public static final float PHYSICS_SCALE = 128.0f;
	// Physics FPS
	public static final float PHYSICS_FPS = 50.0f;
	
	private SimObject holding = null;

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

	private boolean running = false;
	private float accumulatedPhysTime = 0.0f;
	private JPanel myCanvas;
	
	public WorldManager(JPanel myCanvas, Collection<SimObject> objects) {
		this.myCanvas = myCanvas;
		this.physicsWorld = new World(GRAVITY);
		this.allObjects = new TreeSet<SimObject>();
		System.out.println("SIZE "+objects.size());
		for (SimObject obj : objects) this.addObject(obj);
		System.out.println("MY SIZE "+this.allObjects.size());
		this.physicsWorld.setContactListener(new ContactListener() {

			@Override
			public void beginContact(Contact arg0) {
				Body b1 = arg0.getFixtureA().getBody();
				Body b2 = arg0.getFixtureB().getBody();
				SimObject o1 = null, o2 = null;
				for (SimObject obj : WorldManager.this.allObjects) {
					if (obj.getBody() == null) continue;
					if (obj.getBody().equals(b1)) o1 = obj;
					if (obj.getBody().equals(b2)) o2 = obj;
				}
				o1.collideWith(o2);
				o2.collideWith(o1);
			}

			@Override
			public void endContact(Contact arg0) {
				// nope
				
			}

			@Override
			public void postSolve(Contact arg0, ContactImpulse arg1) {
				// no
				
			}

			@Override
			public void preSolve(Contact arg0, Manifold arg1) {
				// definitely not
				
			}
			
		});
	}
	
	public void addObject(SimObject object) {
		object.setWorld(this);
		this.allObjects.add(object);
		object.create();
		if (this.myCanvas != null)
			this.myCanvas.repaint();
	}
	
	public void removeObject(SimObject object) {
		this.physicsWorld.destroyBody(object.getBody());
		this.allObjects.remove(object);
	}
	
	private void performStep() {
		this.physicsWorld.step((float)(1.0/PHYSICS_FPS),6,2);
		for (SimObject object : this.allObjects) object.step();
		this.myCanvas.repaint();
	}
	
	public void performDraw(Graphics g) {
		for (SimObject obj : this.allObjects) {
			if (obj.getBody() != null || obj instanceof Gate) {
				obj.draw(g);
				if (obj.isMoveable() && !this.isRunning()) {
					float x = obj.getBody().getPosition().x*WorldManager.PHYSICS_SCALE;
					float y = obj.getBody().getPosition().y*WorldManager.PHYSICS_SCALE;
					Ellipse2D el = new Ellipse2D.Double(x-3.0,y-3.0,7,7);
					g.setColor(Color.GREEN);
					((Graphics2D)g).fill(el);
				}
			}
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
				/*try {
					Thread.sleep(20);
				} catch (InterruptedException exception) {
					// TODO Auto-generated catch-block stub.
					exception.printStackTrace();
				}*/
			}
		}
	}
	
	public SimObject getObject(Vec2 point) {
		for (SimObject obj : this.allObjects.descendingSet()) {
			if (obj.getBody() == null) continue;
			Fixture fix = obj.getBody().getFixtureList();
			if (fix.testPoint(point.mul(1.0f/WorldManager.PHYSICS_SCALE))) return obj;
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
		Thread runThread = new Thread(this);
		runThread.start();
	}
	
	public void stop() {
		this.setRunning(false);
		this.resetObjectPositions();
		this.myCanvas.repaint();
	}

	private void resetObjectPositions() {
		for (SimObject obj : this.allObjects) {
			obj.reset();
		}
	}

	@Override
	public void run() {
		this.stepLoop();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param wheelRotation
	 */
	public void handleWheel(int x, int y, int wheelRotation) {
		SimObject moving = this.getObject(new Vec2(x,y));
		if (moving == null) return;
		if (!moving.isMoveable()) return;
		moving.getBody().setTransform(moving.getBody().getPosition(),
				(float) (moving.getBody().getAngle()+wheelRotation*Math.PI/20.0f));
		if (moving.getAuxBody() != null) 
			moving.getAuxBody().setTransform(moving.getAuxBody().getPosition(),
					(float) (moving.getAuxBody().getAngle()+wheelRotation*Math.PI/20.0f));
		this.myCanvas.repaint();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param x
	 * @param y
	 * @param button
	 */
	public void handleDrag(int x, int y, int button) {
		if (this.running) return;
		if (this.holding == null) return;
		this.holding.getBody().setTransform(new Vec2(x,y).mul(
				1.0f/WorldManager.PHYSICS_SCALE),this.holding.getBody().getAngle());
		if (this.holding.getAuxBody() != null)
			this.holding.getAuxBody().setTransform(new Vec2(x,y).mul(
					1.0f/WorldManager.PHYSICS_SCALE),this.holding.getAuxBody().getAngle());
		this.holding.setStartAngle(this.holding.getBody().getAngle());
		this.myCanvas.repaint();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param x
	 * @param y
	 * @param button
	 */
	public void handleRelease(int x, int y, int button) {
		if (this.running) return;
		if (this.holding != null) this.holding.setStartPosition(x,y);
		this.holding = null;
		this.myCanvas.repaint();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param x
	 * @param y
	 * @param button
	 */
	public void handlePress(int x, int y, int button) {
		if (this.running) return;
		if (this.holding == null) this.holding = this.getObject(new Vec2(x,y));
		if (this.holding == null) return;
		if (!this.holding.isMoveable()) {
			this.holding = null;
			return;
		}
		if (button == MouseEvent.BUTTON3) {
			this.removeObject(this.holding);
			this.holding = null;
		}
		this.myCanvas.repaint();
	}
	
}
