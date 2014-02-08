import java.awt.Graphics;
import java.util.Set;

import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
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
		def.angle = (float) this.angle;
		def.allowSleep = true;
		this.body = this.getWorld().getPhysicsWorld().createBody(def);
		this.body.createFixture(this.shape,5.0f);
	}
	/* (non-Javadoc)
	 * @see SimObject#step()
	 */
	@Override
	public void step() {
		// Do the raycasts
		final float totalForce = 25.0f;
		final int numberOfCasts = 10;
		Vec2 leftPosition = this.body.getPosition().add(MathHelper.polar(0.25f,
				(float) this.getBody().getAngle()));
		Vec2 rightPosition = this.body.getPosition().add(MathHelper.polar(0.25f,
				(float) (this.getBody().getAngle()+Math.PI)));
		Vec2 increment = rightPosition.sub(leftPosition).mul(1.0f/numberOfCasts);
		final Vec2 rayDirection = MathHelper.polar(1.0f,
				(float) (this.getBody().getAngle()-Math.PI/2.0f));
		Vec2 thisPosition = leftPosition;
		for (int i=0;i<=numberOfCasts;i++) {
			this.getWorld().getPhysicsWorld().raycast(new RayCastCallback() {

				@Override
				public float reportFixture(Fixture arg0, Vec2 arg1, Vec2 arg2,
						float arg3) {
					arg0.getBody().applyForce(rayDirection.mul(totalForce/numberOfCasts),arg1);
					return 0.0f;
				}
				
			},thisPosition,thisPosition.add(rayDirection.mul(20.0f)));
			thisPosition = thisPosition.add(increment);
		}
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
	
	@Override
	public void setStartPosition(int x, int y) {
		this.startPosition = new Vec2(x,y);
	}

	@Override
	public void setStartAngle(float angle) {
		this.angle = angle;
	}

}
