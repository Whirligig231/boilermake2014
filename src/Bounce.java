import java.awt.Graphics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
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
public class Bounce extends SimObject {
	private int xcorrd;
	private int ycorrd;
	private Shape shape;
	private Vec2 startPosition;
	private Body body;
	
	public Bounce(int x,int y){
		this.xcorrd=x;
		this.ycorrd=y;
	}
	
	/* (non-Javadoc)
	 * @see SimObject#create()
	 */
	@Override
	public void create() {
		this.shape = new CircleShape();
		this.shape.setRadius(24.0f);
		BodyDef def = new BodyDef();
		def.type = BodyType.STATIC;
		def.position.set(this.startPosition);
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
		this.body.getFixtureList().setRestitution(1.5f);
	}

	/* (non-Javadoc)
	 * @see SimObject#step()
	 */
	@Override
	public void step() {
		// Nothing to do here
	}

	/* (non-Javadoc)
	 * @see SimObject#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		ImageUtility.drawImage(g,"Graphics/bumper.png",this.getBody().getPosition().x,
				this.getBody().getPosition().y,this.getBody().getAngle());
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
		// Not necessary for static stuff
	}

}