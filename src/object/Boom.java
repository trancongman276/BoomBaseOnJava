package object;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.DrawGame;

public class Boom {
	
	private final int timeKaboom = 180, timeShowWater = 60 + timeKaboom;
	private int x,y, bomLenght, currentTime=0,U,D,L,R;
	private DrawGame drawgame;
	private boolean active=false, exploding = false, checkedCollide=false;
	private Rectangle bounds;
	
	public Boom(int _x, int _y, int _bomLenght, DrawGame _drawgame) {
		x=_x;
		y=_y;
		bomLenght = _bomLenght;
		drawgame = _drawgame;
		bounds = new Rectangle();
	}
	
	public void update() {
		currentTime++;
		bounds.setBounds(x,y,drawgame.getAsset().getBoom().getHeight(),
				drawgame.getAsset().getBoom().getWidth());
	}
	
	public void render(Graphics g) {
		if(currentTime<=timeKaboom) {
			g.drawImage(drawgame.getAsset().getBoom(),x,y,null);
		} else if(currentTime<=timeShowWater) {
			exploding = true;
			g.drawImage(drawgame.getAsset().getWater()[0][0],x,y,null);
			for(int i=1; i<=L;i++)
				g.drawImage(drawgame.getAsset().getWater()[0][0],
						x-drawgame.getAsset().getTitleW()*i,y,null);
			
			for(int i=1; i<=R;i++)
				g.drawImage(drawgame.getAsset().getWater()[0][1],
						x+drawgame.getAsset().getTitleW()*i,y,null);
			
			for(int i=1; i<=U;i++)
				g.drawImage(drawgame.getAsset().getWater()[1][1],
						x,y-drawgame.getAsset().getTitleH()*i,null);
			
			for(int i=1; i<=D;i++)
				g.drawImage(drawgame.getAsset().getWater()[1][0],
						x,y+drawgame.getAsset().getTitleH()*i,null);
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

	public Rectangle getBounds() {
		return bounds;
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
