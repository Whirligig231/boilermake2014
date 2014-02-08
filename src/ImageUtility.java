import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;


/**
 * TODO Put here a description of what this class does.
 *
 * @author schulzcc.
 *         Created Feb 8, 2014.
 */
public abstract class ImageUtility {
	
	public static void drawImage(Graphics g,String path,double x,double y,double angle) {
		if (path == null) return;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
		trans.translate(x,y);
		trans.rotate(angle);
		Image theImage = getImage(path);
		trans.translate(-theImage.getWidth(null)/2,-theImage.getHeight(null)/2);
		g2d.drawImage(getImage(path),trans,null);
	}

	private static Image getImage(String path) {
		ImageIcon myIcon = new ImageIcon(path);
		return myIcon.getImage();
	}

}
