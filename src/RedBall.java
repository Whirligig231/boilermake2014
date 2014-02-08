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

	@Override
	public void create() {
		this.shape = new CircleShape();
		this.shape.setRadius(32.0f);
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.position.set(this.startPosition);
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
		ImageUtility.drawImage(g,"Graphics/ball.png",this.getBody().getPosition().x,
				this.getBody().getPosition().y,this.getBody().getAngle());
	}

	@Override
	public Body getBody() {
		return this.body;
	}

}
