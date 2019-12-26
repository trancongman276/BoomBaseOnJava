package object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.DrawGame;

public class Player {
	private int speed, bomnb, timeDie, bomLenght, x=0, y=0;
	private List<Boom> lsBom;
	private DrawGame drawgame;
	private Control1 move;
	private boolean collide;
	
	public Player(int _speed, int _bomnb, int _timeDie, int _bomLenght, DrawGame _drawgame) {
		speed = _speed;
		bomnb = _bomnb;
		timeDie = _timeDie;
		bomLenght = _bomLenght;
		drawgame = _drawgame;
		lsBom = new ArrayList<>();
		init();
	}
	
	private void init() {
		for(int i=0;i<bomnb;i++) {
			lsBom.add(new Boom(0, 0, bomLenght, drawgame));
		}
	}
	
	public void update() {
		System.out.println(x + " "+ y);
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
			case placeBoom:
				for(Boom bom : lsBom) {
					if(!bom.isActive()) {
						bom.setActive(true);
						bom.setX(x % drawgame.getAsset().getTitleW());
						bom.setY(y % drawgame.getAsset().getTitleH());
						break;
						}
				}
				break;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(drawgame.getAsset().getP1(),x,y,null);
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

	public boolean isCollide() {
		return collide;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public Control1 getMove() {
		return move;
	}
	

}
