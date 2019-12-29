package object;

import java.awt.Graphics;

import main.DrawGame;

public class Boom {
	
	private final int timeKaboom = 180, timeShowWater = 60 + 180;
	private int x,y, bomLenght, currentTime=0,U,D,L,R;
	private DrawGame drawgame;
	private boolean active=false, exploding = false, checkedCollide=false;
	
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
			exploding = true;
			g.drawImage(drawgame.getAsset().getWater(),x,y,null);
			for(int i=0; i<=U;i++)
				g.drawImage(drawgame.getAsset().getWater(),
						x-drawgame.getAsset().getTitleW()*i,y,null);
			
			for(int i=0; i<=D;i++)
				g.drawImage(drawgame.getAsset().getWater(),
						x+drawgame.getAsset().getTitleW()*i,y,null);
			
			for(int i=0; i<=L;i++)
				g.drawImage(drawgame.getAsset().getWater(),
						x,y-drawgame.getAsset().getTitleW()*i,null);
			
			for(int i=0; i<=R;i++)
				g.drawImage(drawgame.getAsset().getWater(),
						x,y+drawgame.getAsset().getTitleW()*i,null);
		}
		else {
			active = false;
			exploding = false;
			checkedCollide = false;
			currentTime = 0;
			U=0;
			D=0;
			L=0;
			R=0;
		}
		
	}

	public boolean isCheckedCollide() {
		return checkedCollide;
	}

	public void setCheckedCollide(boolean checkCollide) {
		this.checkedCollide = checkCollide;
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public int getU() {
		return U;
	}

	public void setU(int u) {
		U = u;
	}

	public int getD() {
		return D;
	}

	public void setD(int d) {
		D = d;
	}

	public int getL() {
		return L;
	}

	public void setL(int l) {
		L = l;
	}

	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBomLenght() {
		return bomLenght;
	}

	public void setBomLenght(int bomLenght) {
		this.bomLenght = bomLenght;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
