package object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.DrawGame;

public class Player {
	private int speed, bomnb, timeDie, bomLenght, x=100, y=100,k=0,t,type, timePush=0;
	private List<Boom> lsBom;
	private DrawGame drawgame;
	private Control1 move,move2;
	private boolean collideU,collideD,collideL,collideR, once=false,die=false,placeBom;
	private Rectangle bound;
	private BufferedImage[][] player;
	
	public Player(int _x, int _y,int _speed, int _bomnb, int _timeDie, int _bomLenght, DrawGame _drawgame, int t) {
		x=_x;
		y=_y;
		speed = _speed;
		bomnb = _bomnb;
		timeDie = _timeDie;
		bomLenght = _bomLenght;
		drawgame = _drawgame;
		type = t;
		bound = new Rectangle(x+36,y+69,51,51);
		lsBom = new ArrayList<>();
		 
		init();
	}
	
	private void init() {
		for(int i=0;i<bomnb;i++) {
			lsBom.add(new Boom(0, 0, bomLenght, drawgame));
		}
	}
	
	public void update() {
		if(!die) {
			bound.setBounds(x+36,y+69,51,51);                                                                                                                                                                                                                      
			if(type == 1)
				move = drawgame.getKeymanager().getMove();
			else move = drawgame.getKeymanager().getMove2();
				if(move != null) {
					switch(move) {
					case U:
						if(!collideU)
						y-=speed;
						break;
					case D:
						if(!collideD)
						y+=speed;
						break;
					case L:
						if(!collideL)
						x-=speed;
						break;
					case R:
						if(!collideR)
						x+=speed;
						break;
					default:
						break;
					}
				}	
				bound.setBounds(x+36,y+69,51,51);
				if(type == 1)
					placeBom = drawgame.getKeymanager().isPlaceBom();
				else placeBom = drawgame.getKeymanager().isPlaceBom2();
			if(placeBom) {
				for(Boom bom : lsBom) {
					if(bom.getX() == ((int)(x / drawgame.getAsset().getTitleW())*drawgame.getAsset().getTitleW()) &&
							bom.getY() == ((int)(y / drawgame.getAsset().getTitleH())*drawgame.getAsset().getTitleH()))
						break;
					if(!bom.isActive()) {
						bom.setActive(true);
						bom.setX((int)(bound.x / drawgame.getAsset().getTitleW())*drawgame.getAsset().getTitleW());
						bom.setY((int)(bound.y / drawgame.getAsset().getTitleH())*drawgame.getAsset().getTitleH());
//						checkBoom(bom);
						if(type == 1)
							drawgame.getKeymanager().setPlaceBom(false);
						else drawgame.getKeymanager().setPlaceBom2(false);
						
						once = false;
						break;
					}
				}
				if(type == 1)
					drawgame.getKeymanager().setPlaceBom(false);
				else drawgame.getKeymanager().setPlaceBom2(false);
			}
		}
			for(Boom bom : lsBom) {
				if(bom.isActive()) {
					bom.update();
				}
			}
	}
	
	public void render(Graphics g) {
		if(!die) {
			if(type == 1)
				player = drawgame.getAsset().getPlayer1();
			else player = drawgame.getAsset().getPlayer2();
		g.drawImage(drawgame.getAsset().getShadows(),x+27,y+105,null);
		if(move!=null) {
			if(move==Control1.L) {
				g.drawImage(player[1][k],x,y,null);
				if(t==5) {
					if(k==0) k=2; else k=0;
					t=0;
				}else t++;
			}else
			
				if(move==Control1.R) {
					g.drawImage(player[2][k],x,y,null);
					if(t==5) {
						if(k==0) k=2; else k=0;
						t=0;
					}else t++;
				}else
					if(move==Control1.U) {
						g.drawImage(player[3][k],x,y,null);
						if(t==5) {
							if(k==0) k=2; else k=0;
							t=0;
						}else t++;
					}else
						if(move==Control1.D) {
							g.drawImage(player[0][k],x,y,null);
							if(t==5) {
								if(k==0) k=2; else k=0;
								t=0;
							}else t++;
						}
			move2=move;
		}else {
			if(move2==Control1.L) {
				g.drawImage(player[1][1],x,y,null);
			}else
			
				if(move2==Control1.R) {
					g.drawImage(player[2][1],x,y,null);
				}else
					if(move2==Control1.U) {
						g.drawImage(player[3][1],x,y,null);
					}else
						if(move2==Control1.D | move2==null) {
							g.drawImage(player[0][1],x,y,null);
						}
		}
		}else {
			if(timeDie!=0) {
				g.drawImage(drawgame.getAsset().getDead(),x,y,null);
				timeDie--;
			}
		}
		//DrawBom
		for(Boom bom : lsBom) {
			if(bom.isActive()) {
				bom.render(g);
			}
		}
	}

	public int getTimePush() {
		return timePush;
	}

	public void setTimePush(int timePush) {
		this.timePush = timePush;
	}

	public void setDie(boolean die) {
		this.die = die;
	}

	public void setOnce(boolean once) {
		this.once = once;
	}

	public boolean isOnce() {
		return once;
	}

	public Control1 getMove2() {
		return move2;
	}

	public Rectangle getBound() {
		return bound;
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
		if(bomnb<this.bomnb) {
			for(int i=0;i<this.bomnb-bomnb;i++) {
				lsBom.remove(lsBom.size()-1);
			}
		}
			else
				for(int i=0;i<bomnb-this.bomnb;i++) {
					lsBom.add(new Boom(0,0,bomLenght,drawgame));
		}
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
		for(Boom bom: lsBom) {
			bom.setBomLenght(bomLenght);
		}
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
		if(type==1)
		return drawgame.getKeymanager().getMove();
		else return drawgame.getKeymanager().getMove2();
	}
	

}
