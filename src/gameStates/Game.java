package gameStates;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.DrawGame;
import object.Boom;
import object.Control1;
import object.Player;

public class Game extends State{
	//player 1
	private final int speed = 5, bomnb = 100, timeDie = 120, bomLenght = 3, timeCollide =30, timeOut=180;
	
	private int map[][], width, height;
	private DrawGame drawgame;
	private Player p1,p2;
	private List<Boom> bomls;
	private Boolean gameOver=false;
	private int timeout=0;
	
	public Game(DrawGame drawgame) {
		initWorld("map.txt");
		this.drawgame = drawgame;
		bomls = new ArrayList<>();
		p1 = new Player(100,100,speed, bomnb, timeDie, bomLenght, drawgame,1);
		p2 = new Player(500,500,speed, bomnb, timeDie, bomLenght, drawgame,2);
	}
	
	private void checkBoom() {
		int max=0;
		for(Boom bom : bomls) {
		for(Boom bom1 : bomls) {
			if(bom1.isActive()) {
				if((Math.abs(bom.getX()-bom1.getX())/drawgame.getAsset().getTitleW())<=bom.getBomLenght()
				&& bom.getY()==bom1.getY()) {
					if(max<bom1.getCurrentTime()) max=bom1.getCurrentTime();}
				else 
					if(Math.abs(bom.getY()-bom1.getY())/drawgame.getAsset().getTitleH()<=bom.getBomLenght()
							&& bom.getX()==bom1.getX()) {
						if(max<bom1.getCurrentTime()) max=bom1.getCurrentTime();}
			}
		}bom.setCurrentTime(max);
		}
		
	}
	
	private void checkCollide(Boom bom) {
		if(bom.isExploding() && !bom.isCheckedCollide()) {
			bom.setCheckedCollide(true);
			int y = bom.getX() / drawgame.getAsset().getTitleW(),
					x= bom.getY() / drawgame.getAsset().getTitleH();

			for(int i=1;i< bom.getBomLenght();i++) {
				if(x-i>=0) {
					if(x-i==0) break;
				if(map[x-i][y]==2 | map[x-1][y]==1) {
					map[x-i][y]=0;
					break;
				}else
					if(map[x-i][y]==3) {
						break;
					}else
						bom.setU(bom.getU()+1);
			}
				}
			
			for(int i=1;i< bom.getBomLenght();i++) {
				if(x+i==height-1) break;
				if(x+i<height) {
				if(map[x+i][y]==2 |map[x+i][y]==1) {
					map[x+i][y]=0;
					break;
				}else
					if(map[x+i][y]==3) {
						break;
					}else
						bom.setD(bom.getD()+1);
			}
			}
			
			for(int i=1;i< bom.getBomLenght();i++) {
				if(y-1==0) break;
				if(y-i>=0) {
				if(map[x][y-i]==2 | map[x][y-i]==1) {
					map[x][y-i]=0;
					break;
				}else
					if(map[x][y-i]==3) {
						break;
					}else
						bom.setL(bom.getL()+1);
			}}
			
			for(int i=1;i< bom.getBomLenght();i++) {
				if(y+1==width-1) break;
				if(y+i<width) {
				if(map[x][y+i]==2 | map[x][y+i]==1) {
					map[x][y+i]=0;
					break;
				}else
					if(map[x][y+i]==3) {
						break;
					}else
						bom.setR(bom.getR()+1);
			}}
		}
	}
	
	private void checkDie(Player p) {
		for(Boom bom : bomls) {
			if(bom.isActive() && bom.isExploding()) {
				int distanceX =bom.getX()/drawgame.getAsset().getTitleW()
						-(int)(p.getBound().x/drawgame.getAsset().getTitleW()),
					distanceY =bom.getY()/drawgame.getAsset().getTitleH()
						-(int)(p.getBound().y/drawgame.getAsset().getTitleH());
				
				if(distanceX>0) {//down
					if(distanceX<=bom.getD() && distanceY==0)
						p.setDie(true);gameOver=true;
					return;
				}else
					if(-distanceX<=bom.getU()&& distanceY==0) {
						p.setDie(true);gameOver=true;
						return;
					}
				
				if(distanceY>0) {//down
					if(distanceY<=bom.getL()&& distanceX==0)
						p.setDie(true);gameOver=true;
					return;
				}else
					if(-distanceY<=bom.getR()&& distanceX==0) {
						p.setDie(true);gameOver=true;
						return;
					}
			}
		}
	}
	
	private int getTitleX(int x) {
		return x / drawgame.getAsset().getTitleH();
	}
	
	private int getTitleY(int y) {
		return y / drawgame.getAsset().getTitleW();
	}
	private void checkCollide(Player p1) {
		int x=getTitleX(p1.getBound().y),
			x2=getTitleX(p1.getBound().y+p1.getBound().height),
		y=getTitleY(p1.getBound().x),
		y2 = getTitleY(p1.getBound().x+p1.getBound().width),
				yR = getTitleY(p1.getBound().x+p1.getBound().width+5),
				yL = getTitleY(p1.getBound().x-5),
				xU = getTitleX(p1.getBound().y-5),
				xD = getTitleX(p1.getBound().y+p1.getBound().height+5);
		if(p1.getMove()==null) p1.setTimePush(0);
		if(x2<height) {
			if(x2==height) {
				p1.setCollideL(false);
				p1.setCollideD(true);
				p1.setCollideU(false);
				p1.setCollideR(false);
				return;
			}
			if(x==0) {
				p1.setCollideL(false);
				p1.setCollideD(false);
				p1.setCollideU(true);
				p1.setCollideR(false);
				return;
			}
			if(y==0) {
				p1.setCollideL(true);
				p1.setCollideD(false);
				p1.setCollideU(false);
				p1.setCollideR(false);
				return;
			}
			if(y2==width) {
				p1.setCollideL(false);
				p1.setCollideD(false);
				p1.setCollideU(false);
				p1.setCollideR(true);
				return;
			}
			if(map[x][yL]==2 | map[x2][yL]==2 && p1.getMove()==Control1.L) { //Top-bot/Left
				p1.setCollideL(true);
				p1.setCollideD(false);
				p1.setCollideU(false);
				p1.setCollideR(false);
				return;
			}
			if(map[x][yR]==2 | map[x2][yR]==2 && p1.getMove()==Control1.R) {//right
				p1.setCollideR(true);
				p1.setCollideL(false);
				p1.setCollideD(false);
				p1.setCollideU(false);
				return;
			}
		    if(map[xU][y]==2 | map[xU][y2]==2 && p1.getMove()==Control1.U) {//up
				p1.setCollideR(false);
				p1.setCollideL(false);
				p1.setCollideD(false);
				p1.setCollideU(true);	
				return;
		    }
			 if(map[xD][y2]==2 | map[xD][y]==2 && p1.getMove()==Control1.D) {//down
				p1.setCollideR(false);
				p1.setCollideL(false);
				p1.setCollideD(true);
				p1.setCollideU(false);	
				return;
			}
			 
			 if(map[x][yL]==3 | map[x2][yL]==3 && p1.getMove()==Control1.L) { //Top-bot/Left
					p1.setCollideL(true);
					p1.setCollideD(false);
					p1.setCollideU(false);
					p1.setCollideR(false);
					return;
				}
				if(map[x][yR]==3 | map[x2][yR]==3 && p1.getMove()==Control1.R) {//right
					p1.setCollideR(true);
					p1.setCollideL(false);
					p1.setCollideD(false);
					p1.setCollideU(false);
					return;
				}
			    if(map[xU][y]==3 | map[xU][y2]==3 && p1.getMove()==Control1.U) {//up
					p1.setCollideR(false);
					p1.setCollideL(false);
					p1.setCollideD(false);
					p1.setCollideU(true);	
					return;
			    }
				 if(map[xD][y2]==3 | map[xD][y]==3 && p1.getMove()==Control1.D) {//down
					p1.setCollideR(false);
					p1.setCollideL(false);
					p1.setCollideD(true);
					p1.setCollideU(false);	
					return;
				}
			 
			 if(map[x][yL]==1 | map[x2][yL]==1 && p1.getMove()==Control1.L) { //Top-bot/Left
					p1.setCollideL(true);
					p1.setCollideD(false);
					p1.setCollideU(false);
					p1.setCollideR(false);
					if(map[x][yL-1]==0 && map[x2][yL-1]==0) {
						p1.setTimePush(p1.getTimePush()+1);
						if(p1.getTimePush()>=timeCollide) {
							if(map[x][yL]==1) {
								map[x][yL-1]=1;
								map[x][yL]=0;
							}else if(map[x2][yL]==1) {
								map[x2][yL-1]=1;
								map[x2][yL]=0;
							}
							p1.setTimePush(0);
						}
					}
					return;
				}
				if(map[x][yR]==1 | map[x2][yR]==1 && p1.getMove()==Control1.R) {//right
					p1.setCollideR(true);
					p1.setCollideL(false);
					p1.setCollideD(false);
					p1.setCollideU(false);
					if(map[x][yR+1]==0 && map[x2][yR+1]==0) {
						p1.setTimePush(p1.getTimePush()+1);
						if(p1.getTimePush()>=timeCollide) {
							if(map[x][yR]==1) {
								map[x][yR+1]=1;
								map[x][yR]=0;
							}else if(map[x2][yR]==1) {
								map[x2][yR+1]=1;
								map[x2][yL]=0;
							}
							p1.setTimePush(0);
						}
					}
					return;
				}
			    if(map[xU][y]==1 | map[xU][y2]==1 && p1.getMove()==Control1.U) {//up
					p1.setCollideR(false);
					p1.setCollideL(false);
					p1.setCollideD(false);
					p1.setCollideU(true);
					if(map[xU-1][y]==0 && map[xU-1][y]==0) {
						p1.setTimePush(p1.getTimePush()+1);
						if(p1.getTimePush()>=timeCollide) {
							if(map[xU][y]==1) {
								map[xU-1][y]=1;
								map[xU][y]=0;
							}else if(map[xU][y2]==1) {
								map[xU-1][y2]=1;
								map[xU][y2]=0;
							}
							p1.setTimePush(0);
						}
					}
					return;
			    }
				 if(map[xD][y2]==1 | map[xD][y]==1 && p1.getMove()==Control1.D) {//down
					p1.setCollideR(false);
					p1.setCollideL(false);
					p1.setCollideD(true);
					p1.setCollideU(false);	
					if(map[xD+1][y]==0 && map[xD+1][y2]==0) {
						p1.setTimePush(p1.getTimePush()+1);
						if(p1.getTimePush()>=timeCollide) {
							if(map[xD][y]==1) {
								map[xD+1][y]=1;
								map[xD][y]=0;
							}else if(map[xD][y2]==1) {
								map[xD+1][y2]=1;
								map[xD][y2]=0;
							}
							p1.setTimePush(0);
						}
					}
					return;
				 }
				 
		}
		
		for(Boom bom : bomls) {
			if(bom.isActive()) {
				
			int xb = bom.getY() / drawgame.getAsset().getTitleH(),
				yb = bom.getX() / drawgame.getAsset().getTitleW();
			if(!p1.isOnce() && bom.getBounds().contains(p1.getBound().getCenterX(), p1.getBound().getCenterY())) {
				p1.setOnce(true);
				return;
			}else if(bom.getBounds().contains(p1.getBound().getCenterX(), p1.getBound().getCenterY()))
				p1.setOnce(false);
			if(p1.isOnce() &&(x==xb && yL==yb) | (x2==xb && yL==yb) && p1.getMove()==Control1.L) {
				p1.setCollideR(false);
				p1.setCollideL(true);
				p1.setCollideD(false);
				p1.setCollideU(false);
				return;
			}
			
			if(p1.isOnce() &&(x==xb  && yR==yb) | (yR==yb && x2==xb) && p1.getMove()==Control1.R) {
				p1.setCollideR(true);
				p1.setCollideL(false);
				p1.setCollideD(false);
				p1.setCollideU(false);
				return;
			}
			
			if(p1.isOnce() &&(xU==xb  && y==yb) | (y2==yb && xU==xb) && p1.getMove()==Control1.U) {
				p1.setCollideR(false);
				p1.setCollideL(false);
				p1.setCollideD(false);
				p1.setCollideU(true);
				return;
			}
			
			if(p1.isOnce() && (xD==xb  && y==yb) | (y2==yb && xD==xb) && p1.getMove()==Control1.D) {
				p1.setCollideR(false);
				p1.setCollideL(false);
				p1.setCollideD(true);
				p1.setCollideU(false);
				return;
			}
			
		}
		}
		if(p1.getMove()==p1.getMove2()) {
			p1.setCollideR(false);
			p1.setCollideL(false);
			p1.setCollideD(false);
			p1.setCollideU(false);
		}
	}
	
	@Override
	public void Update() {
		bomls.clear();
		bomls.addAll(p1.getLsBom());
		bomls.addAll(p2.getLsBom());
		
		checkCollide(p1);
		checkCollide(p2);
		p1.update();
		p2.update();
		for(Boom bom : bomls) {
			checkCollide(bom);
		}
		checkDie(p1);
		checkDie(p2);
		checkBoom();
	}

	@Override
	public void Render(Graphics g) {
		
			for(int i=0; i<height;i++) {
				for(int j=0; j<width;j++) {
					if((j+i)%2==0)
					g.drawImage(drawgame.getAsset().getGrass(), 
							j*drawgame.getAsset().getTitleW(),
							i*drawgame.getAsset().getTitleH(),null);
					else
						g.drawImage(drawgame.getAsset().getFloor(), 
								j*drawgame.getAsset().getTitleW(),
								i*drawgame.getAsset().getTitleH(),null);
				}
			}
			
			for(int i=0; i<height;i++) {
				for(int j=0; j<width;j++) {
					switch(map[i][j]) {
					case 0:
	//					g.drawImage(drawgame.getAsset().getGrass(),
	//							j*drawgame.getAsset().getTitleW(),
	//							i*drawgame.getAsset().getTitleH(),null);
						break;
					case 1:
						g.drawImage(drawgame.getAsset().getMoveBox(),
								j*drawgame.getAsset().getTitleW()-15,
								i*drawgame.getAsset().getTitleH()-23,null);
						break;
					case 2:
							g.drawImage(drawgame.getAsset().getBox(),
									j*drawgame.getAsset().getTitleW()-15,
									i*drawgame.getAsset().getTitleH()-23,null);
						break;
					case 3: 
						g.drawImage(drawgame.getAsset().getBox(),
						j*drawgame.getAsset().getTitleW()-15,
						i*drawgame.getAsset().getTitleH()-23,null);
						break;
					}
				}
			}
			 drawUp(p1,g);
			 drawUp(p2,g);
	if(gameOver)  {
		if(timeout!=timeOut)
			timeout++;
		else State.setState(new MainMenu(drawgame));
		g.drawImage(drawgame.getAsset().getLose(),0,0,null);
	}
	}
	private void drawUp(Player p1,Graphics g) {
		int x=getTitleX(p1.getBound().y+p1.getBound().height)+1,
				 
				y=getTitleY(p1.getBound().x),
				y2=getTitleY(p1.getBound().x+p1.getBound().width);
		 if(x>=height) return;
		if(map[x][y]==2 |map[x][y2]==2) {
			p1.render(g);
			for(int i=x;i<height;i++) {
				if(map[i][y]==2 && y==y2) {
					g.drawImage(drawgame.getAsset().getBox(),
							y*drawgame.getAsset().getTitleW()-15,
							i*drawgame.getAsset().getTitleH()-23,null);
				}else {
					if(map[i][y]==2)
						g.drawImage(drawgame.getAsset().getBox(),
								y*drawgame.getAsset().getTitleW()-15,
								i*drawgame.getAsset().getTitleH()-23,null);
					if(map[i][y2]==2)
						g.drawImage(drawgame.getAsset().getBox(),
								y2*drawgame.getAsset().getTitleW()-15,
								i*drawgame.getAsset().getTitleH()-23,null);
					if(map[i][y]!=2 && map[i][y2]!=2)
						break;
				}
			}
		}else
				if(map[x][y]==1 |map[x][y2]==1) {
					p1.render(g);
					for(int i=x;i<height;i++) {
						if(map[i][y]==1 && y==y2) {
							g.drawImage(drawgame.getAsset().getMoveBox(),
									y*drawgame.getAsset().getTitleW()-15,
									i*drawgame.getAsset().getTitleH()-23,null);
						}else {
							if(map[i][y]==1)
								g.drawImage(drawgame.getAsset().getMoveBox(),
										y*drawgame.getAsset().getTitleW()-15,
										i*drawgame.getAsset().getTitleH()-23,null);
							if(map[i][y2]==1)
								g.drawImage(drawgame.getAsset().getMoveBox(),
										y2*drawgame.getAsset().getTitleW()-15,
										i*drawgame.getAsset().getTitleH()-23,null);
							if(map[i][y]!=1 && map[i][y2]!=1)
								break;
						}
					}
		}else
			p1.render(g);
	}
	private void initWorld(String path) {
		StringBuilder sb = new StringBuilder();
		
		try {
			File file = new File(
					getClass().getClassLoader().getResource("map.txt").getFile());
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine())!=null)
				sb.append(line+"\n");
			br.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		
		String[] mat = sb.toString().split("\\s+");
		width = Integer.parseInt(mat[0]);
		height=Integer.parseInt(mat[1]);
		map = new int[height][width];
		
		for(int i=0; i<height;i++) {
			for(int j=0; j<width;j++) {
				map[i][j] = Integer.parseInt(mat[i*width+j+2]);
			}
		}
		System.out.println(width + "  "+ height);
		for(int i=0; i<height;i++) {
			for(int j=0; j<width;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
