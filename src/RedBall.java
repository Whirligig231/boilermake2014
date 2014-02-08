import java.awt.Graphics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;


/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public class RedBall extends SimObject {
	
	private Body body;
	private Shape shape;
	private Vec2 startPosition;
	
	public RedBall(int x, int y){
		this.startPosition=new Vec2(x,y);
	}

	@Override
	public void create() {
		this.shape = new CircleShape();
		this.shape.setRadius(0.25f);
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.position.set(this.startPosition.mul(1/WorldManager.PHYSICS_SCALE));
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
	}

	@Override
	public void step() {
		// Nothing really needs to be done here ... yet. *evil laugh*
	}

	@Override
	public void draw(Graphics g) {
		ImageUtility.drawImage(g,"Graphics/ball.png",
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
		this.body.setTransform(this.startPosition,0.0f);
		this.body.setLinearVelocity(new Vec2());
		this.body.setAngularVelocity(0.0f);
	}

}
