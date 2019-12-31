package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Asset {
	ImageLoader img;
	private BufferedImage boom, bush, wall, grass, lose,
	box,moveBox, floor, sheetp2,sheetp1, shadows, dead,watersheet,mainmenu,hdsd;
	private BufferedImage[][] player1,player2,water;
	private BufferedImage[] intro;
	private int titleW,titleH;
	public void init() {
		img = new ImageLoader();
		boom = img.LoadImage("/objects/boom2.png");
		bush = img.LoadImage("/objects/floor0.png");
		wall = img.LoadImage("/objects/floor0.png");
		grass = img.LoadImage("/objects/floor0.png");
		floor = img.LoadImage("/objects/floor1.png");
		box = img.LoadImage("/objects/box.png");
		watersheet = img.LoadImage("/objects/watersheet.png");
		sheetp1 = img.LoadImage("/objects/sheetp1.png");
		sheetp2 = img.LoadImage("/objects/sheetp2.png");
		shadows = img.LoadImage("/objects/shadows.png");
		dead = img.LoadImage("/objects/dead.png");
		moveBox = img.LoadImage("/objects/box2.png");
		mainmenu = img.LoadImage("/map/menu_start.png");
		hdsd = img.LoadImage("/map/hdsd.png");
		lose = img.LoadImage("/map/lose.png");
		titleW = grass.getWidth();
		titleH = grass.getHeight();
		intro = new BufferedImage[241];
		for(int i=1;i<=241;i++) {
			intro[i-1]=img.LoadImage("/intro/intro ("+i+").jpg");
		}
		dataSheet();
	}

	private void dataSheet() {
		player1 = new BufferedImage[4][3];
		player2 = new BufferedImage[4][3];
		water = new BufferedImage[2][2];
		for(int i=0; i<4;i++) {
			for(int j=0; j<3;j++) {
				player1[i][j] = sheetp1.getSubimage(j*(sheetp1.getWidth()/3), i*(sheetp1.getHeight()/4), 
						sheetp1.getWidth()/3, sheetp1.getHeight()/4);
				player2[i][j] = sheetp2.getSubimage(j*(sheetp2.getWidth()/3), i*(sheetp2.getHeight()/4),
						sheetp2.getWidth()/3, sheetp2.getHeight()/4);
			}
		}
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				water[i][j] = watersheet.getSubimage(j*(watersheet.getWidth()/2), i*(watersheet.getHeight()/2),
						watersheet.getWidth()/2, watersheet.getHeight()/2);
			}
		}
	}
	
	public BufferedImage[] getIntro() {
		return intro;
	}

	public BufferedImage getLose() {
		return lose;
	}

	public BufferedImage getHdsd() {
		return hdsd;
	}

	public BufferedImage getMainmenu() {
		return mainmenu;
	}

	public BufferedImage getMoveBox() {
		return moveBox;
	}

	public BufferedImage getDead() {
		return dead;
	}

	public BufferedImage getShadows() {
		return shadows;
	}

	public BufferedImage[][] getPlayer1() {
		return player1;
	}

	public BufferedImage[][] getPlayer2() {
		return player2;
	}

	public BufferedImage getFloor() {
		return floor;
	}

	public int getTitleW() {
		return titleW;
	}

	public int getTitleH() {
		return titleH;
	}

	public BufferedImage[][] getWater() {
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
