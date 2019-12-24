package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Asset {
	ImageLoader img;
	private BufferedImage boom, bush, wall, grass, box, water;
	
	public void init() {
		img = new ImageLoader();
		boom = img.LoadImage("");
		bush = img.LoadImage("");
		wall = img.LoadImage("");
		grass = img.LoadImage("");
		box = img.LoadImage("");
		water = img.LoadImage("");
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
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
