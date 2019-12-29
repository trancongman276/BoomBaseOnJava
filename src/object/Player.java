package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import main.DrawGame;

public class Player {
	private int speed, bomnb, timeDie, bomLenght, x=100, y=100;
	private List<Boom> lsBom;
	private DrawGame drawgame;
	private Control1 move;
	private boolean collideU,collideD,collideL,collideR;
	private Rectangle bound;
	
	public Player(int _speed, int _bomnb, int _timeDie, int _bomLenght, DrawGame _drawgame) {
		speed = _speed;
		bomnb = _bomnb;
		timeDie = _timeDie;
		bomLenght = _bomLenght;
		drawgame = _drawgame;
		bound = new Rectangle();
		
		lsBom = new ArrayList<>();
		init();
	}
	
	private void init() {
		for(int i=0;i<bomnb;i++) {
			lsBom.add(new Boom(0, 0, bomLenght, drawgame));
		}
	}
	private void checkBoom(Boom bom) {
		for(Boom bom1 : lsBom) {
			if(bom1.isActive())
			if(Math.abs(bom.getX()-bom1.getX())/drawgame.getAsset().getTitleW()<bom.getBomLenght()
			&& bom.getY()==bom1.getY())
				bom.setCurrentTime(bom1.getCurrentTime());
			else 
				if(Math.abs(bom.getY()-bom1.getY())/drawgame.getAsset().getTitleH()<bom.getBomLenght()
						&& bom.getX()==bom1.getX())
					bom.setCurrentTime(bom1.getCurrentTime());
		}
		
	}
	public void update() {
			move = drawgame.getKeymanager().getMove();
			if(move != null) {
				switch(move) {
				case U:
					y-=speed;
					break;
				case D:
					y+=speed;
					break;
				case L:
					x-=speed;
					break;
				case R:
					x+=speed;
					break;
				}
			}	
		if(drawgame.getKeymanager().isPlaceBom()) {
			for(Boom bom : lsBom) {
				if(bom.getX() == ((int)(x / drawgame.getAsset().getTitleW())*drawgame.getAsset().getTitleW()) &&
						bom.getY() == ((int)(y / drawgame.getAsset().getTitleH())*drawgame.getAsset().getTitleH()))
					break;
				if(!bom.isActive()) {
					bom.setActive(true);
					bom.setX((int)(x / drawgame.getAsset().getTitleW())*drawgame.getAsset().getTitleW());
					bom.setY((int)(y / drawgame.getAsset().getTitleH())*drawgame.getAsset().getTitleH());
					checkBoom(bom);
					drawgame.getKeymanager().setPlaceBom(false);
					break;
				}
			}
			drawgame.getKeymanager().setPlaceBom(false);
		}
		
		for(Boom bom : lsBom) {
			if(bom.isActive()) {
				bom.update();
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(drawgame.getAsset().getP1(),x,y,null);
		for(Boom bom : lsBom) {
			if(bom.isActive()) {
				bom.render(g);
			}
		}
	}

	public List<Boom> getLsBom() {
		return lsBom;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBomnb() {
		return bomnb;
	}

	public void setBomnb(int bomnb) {
		this.bomnb = bomnb;
	}

	public int getTimeDie() {
		return timeDie;
	}

	public void setTimeDie(int timeDie) {
		this.timeDie = timeDie;
	}

	public int getBomLenght() {
		return bomLenght;
	}

	public void setBomLenght(int bomLenght) {
		this.bomLenght = bomLenght;
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


	public boolean isCollideU() {
		return collideU;
	}

	public void setCollideU(boolean collideU) {
		this.collideU = collideU;
	}

	public boolean isCollideD() {
		return collideD;
	}

	public void setCollideD(boolean collideD) {
		this.collideD = collideD;
	}

	public boolean isCollideL() {
		return collideL;
	}

	public void setCollideL(boolean collideL) {
		this.collideL = collideL;
	}

	public boolean isCollideR() {
		return collideR;
	}

	public void setCollideR(boolean collideR) {
		this.collideR = collideR;
	}

	public Control1 getMove() {
		return move;
	}
	

}
