import java.awt.Graphics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
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
public class Wall extends SimObject {
	
	private Body body;
	private PolygonShape shape;
	private Vec2 startPosition;
	private double angle;

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param x
	 * @param y
	 * @param angle
	 */
	public Wall(int x, int y, double angle) {
		this.startPosition = new Vec2(x,y);
		this.angle = angle;
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
		ImageUtility.drawImage(g,"Graphics/wall.png",this.getBody().getPosition().x,
				this.getBody().getPosition().y,this.getBody().getAngle());
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void reset() {
		// Static doesn't need reset
	}

}
