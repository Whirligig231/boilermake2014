import java.awt.Graphics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author Steven.
 *         Created Feb 8, 2014.
 */
public class Gear extends SimObject {
	private Shape shape;
	private Body body, body2;
	private Vec2 startPosition;
	private double angle;
	
	public Gear(int x, int y,double startAngle){
		this.startPosition=new Vec2(x,y);
		this.angle=startAngle;
	}

	/* (non-Javadoc)
	 * @see SimObject#create()
	 */
	@Override
	public void create() {
		this.shape = new CircleShape();
		this.shape.setRadius(0.25f);
		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(0.75f,0.125f);
		PolygonShape shape3 = new PolygonShape();
		shape3.setAsBox(0.125f,0.75f);
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.position.set(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE));
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
		this.body.createFixture(shape2,5.0f);
		this.body.createFixture(shape3,5.0f);
		this.body.getFixtureList().setRestitution(0.5f);
		BodyDef def2 = new BodyDef();
		def2.type = BodyType.STATIC;
		def2.position.set(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE));
		def2.active = true;
		this.body2 = this.getWorld().getPhysicsWorld().createBody(def2);
		RevoluteJointDef def3 = new RevoluteJointDef();
		def3.bodyA = this.body;
		def3.bodyB = this.body2;
		def3.localAnchorA.set(0,0);
		def3.localAnchorB.set(0,0);
		this.getWorld().getPhysicsWorld().createJoint(def3);
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
		ImageUtility.drawImage(g,"Graphics/gear.png",
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
	
	@Override
	public Body getAuxBody() {
		return this.body2;
	}

	/* (non-Javadoc)
	 * @see SimObject#reset()
	 */
	@Override
	public void reset() {
		this.body.setTransform(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE),
				(float)this.angle);
		this.body.setLinearVelocity(new Vec2());
		this.body.setAngularVelocity(0.0f);	
		this.body2.setTransform(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE),
				(float)this.angle);
		this.body2.setLinearVelocity(new Vec2());
		this.body2.setAngularVelocity(0.0f);	
	}
	
	@Override
	public void setStartPosition(int x, int y) {
		this.startPosition = new Vec2(x,y);
	}

	@Override
	public void setStartAngle(float angle) {
		this.angle = angle;
	}

}
