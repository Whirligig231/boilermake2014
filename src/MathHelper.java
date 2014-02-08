import org.jbox2d.common.Vec2;


/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public abstract class MathHelper {
	
	public static boolean intersects(Vec2 start1, Vec2 end1, Vec2 start2, Vec2 end2) {
		Vec2 dir1 = end1.sub(start1);
		Vec2 dir2 = end2.sub(start2);
		if (Vec2.cross(dir1,dir2) == 0.0f) return false;
		return ((isClockwise(start1,start2,end1) ^ isClockwise(start1,start2,end2)) &&
				(isClockwise(end1,end2,start1) ^ isClockwise(end1,end2,start2)));
	}
	
	public static boolean isClockwise(Vec2 a, Vec2 b, Vec2 c) {
		return (Vec2.cross(c.sub(a),b.sub(a)) > 0.0f);
	}
	
	public static Vec2 polar(float r, float theta) {
		return new Vec2((float)(r*Math.cos(theta)),(float)(r*Math.sin(theta)));
	}

}
