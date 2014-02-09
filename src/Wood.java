import java.awt.Graphics;

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
public class Wood extends SimObject {
	private PolygonShape shape;
	private Body body;
	private Vec2 startPosition;
	private double angle;
	
	public Wood(int x, int y,double startAngle){
		this.startPosition=new Vec2(x,y);
		this.angle=startAngle;
	}
	
	@Override
	public void create() {
		this.shape = new PolygonShape();
		this.shape.setAsBox(0.75f,0.125f);
		BodyDef def = new BodyDef();
		def.type = BodyType.STATIC;
		def.position.set(this.startPosition.mul(1.0f/WorldManager.PHYSICS_SCALE));
		def.angle = (float) this.angle;
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
	}

	@Override
	public void step() {
		// Nothing really needs to be done here
	}

	@Override
	public void draw(Graphics g) {
		String imageName;
		if (!this.body.isActive()) imageName = "Graphics/char.png";
		else imageName = "Graphics/wood.png";
		ImageUtility.drawImage(g,imageName,
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
		this.body.setActive(true);
		this.body.setTransform(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE),
				(float) this.angle);
		this.setDepth(0);
	}
	
	@Override
	public void setStartPosition(int x, int y) {
		this.startPosition = new Vec2(x,y);
	}

	@Override
	public void setStartAngle(float angle) {
		this.angle = angle;
	}
	
	public String getName() {return "Wood";}
	
}
