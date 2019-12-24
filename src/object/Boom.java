package object;

import java.awt.Graphics;

import main.DrawGame;

public class Boom {
	
	private final int timeKaboom = 180, timeShowWater = 60 + 180;
	private int x,y, bomLenght, currentTime=0;
	private DrawGame drawgame;
	
	public Boom(int _x, int _y, int _bomLenght, DrawGame _drawgame) {
		x=_x;
		y=_y;
		bomLenght = _bomLenght;
		drawgame = _drawgame;
	}
	
	public void update() {
		currentTime++;
	}
	
	public void render(Graphics g) {
		if(currentTime<=timeKaboom) {
			g.drawImage(drawgame.getAsset().getBoom(),x,y,null);
		} else if(currentTime<=timeShowWater) {
			g.drawImage(drawgame.getAsset().getWater(),x,y,null);
		}
	}
	
	private void checkCollide() {
		
	}
}
