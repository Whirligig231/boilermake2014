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
	
	/**
	 * Returns the value of the field called 'active'.
	 * @return Returns the active.
	 */
	public boolean isActive() {
		return this.active;
	}
	public Gate(int x, int y,double startAngle){
		this.position=new Vec2(x,y);
		this.angle=(float) startAngle;
	}
	@Override
	public void create() {
		this.active = false;
	}

	@Override
	public void step() {
		// Check for the ball ...
		for (SimObject obj : this.getWorld().getAllObjects()) {
			if (!(obj instanceof RedBall)) continue;
			Vec2 rbPos = obj.getBody().getPosition().mul(WorldManager.PHYSICS_SCALE);
			Vec2 rbVel = obj.getBody().getLinearVelocity().mul(WorldManager.PHYSICS_SCALE);
			Vec2 rbNewPos = rbPos.add(rbVel.mul(1.0f/WorldManager.PHYSICS_FPS));
			Vec2 corner1 = this.position.add(MathHelper.polar(128.0f,this.angle));
			Vec2 corner2 = this.position.add(MathHelper.polar(-128.0f,this.angle));
			if (MathHelper.intersects(corner1,corner2,rbPos,rbNewPos)) {
				this.active = true;
				this.getWorld().checkWin();
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
	
	@Override
	public void setStartPosition(int x, int y) {
		this.position = new Vec2(x,y);
	}

	@Override
	public void setStartAngle(float angle) {
		this.angle = angle;
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}

	public String getName() {return "Gate";}

}
