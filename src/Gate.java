import java.awt.Graphics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public class Gate extends SimObject {
	
	private Vec2 position;
	private float angle;
	private boolean active;

	@Override
	public void create() {
		this.active = false;
	}

	@Override
	public void step() {
		// Check for the ball ...
		for (SimObject obj : this.getWorld().getAllObjects()) {
			if (!(obj instanceof RedBall)) continue;
			Vec2 rbPos = obj.getBody().getPosition();
			Vec2 rbVel = obj.getBody().getLinearVelocity();
			Vec2 rbNewPos = rbPos.add(rbVel.mul(1.0f/WorldManager.PHYSICS_FPS));
			Vec2 corner1 = this.position.add(MathHelper.polar(128.0f,this.angle));
			Vec2 corner2 = this.position.add(MathHelper.polar(-128.0f,this.angle));
			if (MathHelper.intersects(corner1,corner2,rbPos,rbNewPos)) {
				this.active = true;
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		String myImage;
		if (this.active) myImage = "Graphics/gate_1.png";
		else myImage = "Graphics/gate_0.png";
		ImageUtility.drawImage(g,myImage,this.position.x,
				this.position.y,this.angle);
	}

	@Override
	public Body getBody() {
		return null;
	}

	@Override
	public void reset() {
		this.active = false;
	}

}