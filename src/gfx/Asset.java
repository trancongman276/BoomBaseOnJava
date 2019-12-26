package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Asset {
	ImageLoader img;
	private BufferedImage boom, bush, wall, grass, box, water, p1, p2;
	private int titleW,titleH;
	public void init() {
		img = new ImageLoader();
		boom = img.LoadImage("/objects/boom.png");
		bush = img.LoadImage("/objects/floor0.png");
		wall = img.LoadImage("/objects/floor0.png");
		grass = img.LoadImage("/objects/floor0.png");
		box = img.LoadImage("/objects/box.png");
		water = img.LoadImage("/objects/floor0.png");
		p1 = img.LoadImage("/objects/player.png");
		p2 = img.LoadImage("/objects/player.png");
		
		titleW = grass.getWidth();
		titleH = grass.getHeight();
	}

	public int getTitleW() {
		return titleW;
	}

	public int getTitleH() {
		return titleH;
	}

	public BufferedImage getP1() {
		return p1;
	}

	public BufferedImage getP2() {
		return p2;
	}

	public BufferedImage getWater() {
		return water;
	}

	public BufferedImage getBoom() {
		return boom;
	}

	public BufferedImage getBush() {
		return bush;
	}

	public BufferedImage getWall() {
		return wall;
	}

	public BufferedImage getGrass() {
		return grass;
	}

	public BufferedImage getBox() {
		return box;
	}
}

class ImageLoader {
	public BufferedImage LoadImage(String path) {
		try {
			return ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
