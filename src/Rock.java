import java.awt.Graphics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

/**
 * 
 */

/**
 * TODO Put here a description of what this class does.
 *
 * @author Steven.
 *         Created Feb 8, 2014.
 */
public class Rock extends SimObject {
	private CircleShape shape;
	private Body body;
	private Vec2 startPosition;
	private double angle;
	
	public Rock(int x, int y,double startAngle){
		this.startPosition=new Vec2(x,y);
		this.angle=startAngle;
	}
	
	@Override
	public void create() {
		this.shape = new CircleShape();
		this.shape.setRadius(0.375f);
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.linearDamping = 1000.0f;
		def.angularDamping = 1000.0f;
		def.position.set(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE));
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
		this.body.getFixtureList().setRestitution(0.5f);
	}

	@Override
	public void step() {
		// Nothing really needs to be done here
	}
	
	@Override
	public void collideWith(SimObject other) {
		this.getBody().setLinearDamping(0.0f);
		this.getBody().setAngularDamping(0.0f);
	}

	@Override
	public void draw(Graphics g) {
		ImageUtility.drawImage(g,"Graphics/rock.png",
				this.getBody().getPosition().x*WorldManager.PHYSICS_SCALE,
				this.getBody().getPosition().y*WorldManager.PHYSICS_SCALE,
				this.getBody().getAngle());
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void reset() {
		this.body.setTransform(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE),0.0f);
		this.body.setLinearVelocity(new Vec2());
		this.body.setAngularVelocity(0.0f);
		this.getBody().setLinearDamping(1000.0f);
		this.getBody().setAngularDamping(1000.0f);
	}
	
	@Override
	public void setStartPosition(int x, int y) {
		this.startPosition = new Vec2(x,y);
	}

	@Override
	public void setStartAngle(float angle) {
		this.angle = angle;
	}
	
	public String getName() {return "Rock";}

}
