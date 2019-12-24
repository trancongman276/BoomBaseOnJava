package gfx;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Resize {
	public static double x,y;
	public static BufferedImage resize(BufferedImage ip) {
		//Get Screen scale
		GraphicsConfiguration asdf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		AffineTransform asfd2 = asdf.getDefaultTransform();
		
		double scaleX = 1/asfd2.getScaleX();
		double scaleY = 1/asfd2.getScaleY();
		
		x=scaleX;
		y=scaleY;
		
		BufferedImage op = new BufferedImage((int)(scaleX * ip.getWidth()),
				(int)(scaleY*ip.getHeight()),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) op.getGraphics();
		g.scale(scaleX, scaleY);
		g.drawImage(ip, 0, 0,null);
		g.dispose();
		
		return op;
	}
}
