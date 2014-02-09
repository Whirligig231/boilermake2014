import java.awt.Graphics;

import org.jbox2d.dynamics.Body;


/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public class Flame extends SimObject {
	
	private int x;
	private int y;
	private float scale;

	public Flame(int x, int y) {
		this.x = x;
		this.y = y;
		this.setDepth(-100);
	}

	@Override
	public void create() {
		this.scale = 1.0f;
	}

	@Override
	public void step() {
		this.scale -= 0.02f;
		if (this.scale <= 0.0f) this.destroy();
	}

	@Override
	public void draw(Graphics g) {
		ImageUtility.drawImage(g,"Graphics/flame.png",this.x,this.y,0.0f,this.scale,this.scale);
	}

	@Override
	public Body getBody() {
		return null;
	}

	@Override
	public boolean resetDelete() {
		return true;
	}

	@Override
	public void setStartPosition(int x, int y) {
		// Not needed
	}

	@Override
	public void setStartAngle(float angle) {
		// Not needed
	}
	
	@Override
	public double getStartAngle() {
		return 0.0;
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public void reset() {
		// Nothing; will delete
	}
	
	public String getName() {return "Flame";}

}
