import java.awt.Graphics;
import java.util.Set;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author Steven.
 *         Created Feb 8, 2014.
 */
public class Fan extends SimObject {
	private PolygonShape shape;
	private Body body;
	private Vec2 startPosition;
	private double angle;
	
	public Fan(int x, int y, double startAngle){
		this.startPosition=new Vec2(x,y);
		this.angle=startAngle;
	}
	
	/* (non-Javadoc)
	 * @see SimObject#create()
	 */
	@Override
	public void create() {
		this.shape = new PolygonShape();
		this.shape.setAsBox(0.5f,0.125f);
		BodyDef def = new BodyDef();
		def.type = BodyType.STATIC;
		def.position.set(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE));
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
	}
	/* (non-Javadoc)
	 * @see SimObject#step()
	 */
	@Override
	public void step() {
		// TODO Make it blow!
	}
	/* (non-Javadoc)
	 * @see SimObject#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		ImageUtility.drawImage(g,"Graphics/fan.png",
				this.getBody().getPosition().x*WorldManager.PHYSICS_SCALE,
				this.getBody().getPosition().y*WorldManager.PHYSICS_SCALE,
				this.getBody().getAngle());
	}
	/* (non-Javadoc)
	 * @see SimObject#getBody()
	 */
	@Override
	public Body getBody() {
		return this.body;
	}
	/* (non-Javadoc)
	 * @see SimObject#reset()
	 */
	@Override
	public void reset() {
		// Nada.
	}

}
