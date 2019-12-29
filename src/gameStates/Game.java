package gameStates;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.DrawGame;
import object.Boom;
import object.Control1;
import object.Player;

public class Game extends State{
	//player 1
	private final int speed = 5, bomnb = 10, timeDie = 120, bomLenght = 3;
	
	private int map[][], width, height;
	private DrawGame drawgame;
	private Player p1;
	
	public Game(DrawGame drawgame) {
		initWorld("map.txt");
		this.drawgame = drawgame;
		p1 = new Player(speed, bomnb, timeDie, bomLenght, drawgame);
	}
	
	private void checkCollide(Player p) {
		int x=(int) (p.getY()/ drawgame.getAsset().getTitleW()),
		y=(int) (p.getX()/ drawgame.getAsset().getTitleH());
		System.out.println(x+ " "+y);
		if(x-1>=0)
		if( (p.getMove()==Control1.U) && (map[x-1][y] == 2) ) {
			p.setCollideU(true);
			p.setCollideD(false);
			p.setCollideR(false);
			p.setCollideL(false);
		}else
		if(x+1<bomLenght)
		if( (p.getMove()==Control1.D) && (map[x+1][y] == 2) ) {
			p.setCollideD(true);
			p.setCollideR(false);
			p.setCollideL(false);
			p.setCollideU(false);
		}else
		if(y-1>=0)
		if( (p.getMove()==Control1.L) && (map[x][y-1] == 2) ) {
			p.setCollideL(true);
			p.setCollideU(false);
			p.setCollideD(false);
			p.setCollideR(false);
		}else
		if(y+1<bomLenght)
		if( (p.getMove()==Control1.R) && (map[x][y+1] == 2) ) {
			p.setCollideR(true);
			p.setCollideL(false);
			p.setCollideU(false);
			p.setCollideD(false);
		}
	}
	
	private void checkCollide(Boom bom) {
		if(bom.isExploding() && !bom.isCheckedCollide()) {
			bom.setCheckedCollide(true);
			int y = bom.getX() / drawgame.getAsset().getTitleW(),
					x= bom.getY() / drawgame.getAsset().getTitleH();
			System.out.println(x+" "+y);
			for(int i=1;i< bom.getBomLenght();i++) {
				if(x-i>=0)
				if(map[x-i][y]==2) {
					map[x-i][y]=0;
					break;
				}else
					if(map[x-i][y]==3) {
						break;
					}else
						bom.setU(bom.getU()+1);
			}
			
			for(int i=1;i< bom.getBomLenght();i++) {
				if(x+i<10)
				if(map[x+i][y]==2) {
					map[x+i][y]=0;
					break;
				}else
					if(map[x+i][y]==3) {
						break;
					}else
						bom.setD(bom.getD()+1);
			}
			
			for(int i=1;i< bom.getBomLenght();i++) {
				if(y-i>=0)
				if(map[x][y-i]==2) {
					map[x][y-i]=0;
					break;
				}else
					if(map[x][y-i]==3) {
						break;
					}else
						bom.setL(bom.getL()+1);
			}
			
			for(int i=1;i< bom.getBomLenght();i++) {
				if(y+i<10)
				if(map[x][y+i]==2) {
					map[x][y+i]=0;
					break;
				}else
					if(map[x][y+i]==3) {
						break;
					}else
						bom.setR(bom.getR()+1);
			}
		}
	}
	
	@Override
	public void Update() {
		p1.update();
		
//		checkCollide(p1);
		
		for(Boom bom : p1.getLsBom()) {
			if(bom.isActive()) {
				map[bom.getY() / drawgame.getAsset().getTitleH()]
				[bom.getX() / drawgame.getAsset().getTitleW()]=1;
			}
			else
				map[bom.getY() / drawgame.getAsset().getTitleH()]
						[bom.getX() / drawgame.getAsset().getTitleW()]=0;
		}
		
		for(Boom bom : p1.getLsBom()) {
			checkCollide(bom);
		}
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
//					g.drawImage(drawgame.getAsset().getBush(),
//							j*drawgame.getAsset().getTitleW(),
//							i*drawgame.getAsset().getTitleH(),null);
					break;
				case 2:
//					g.drawImage(drawgame.getAsset().getGrass(),
//							j*drawgame.getAsset().getTitleW(),
//							i*drawgame.getAsset().getTitleH(),null);
					g.drawImage(drawgame.getAsset().getBox(),
							j*drawgame.getAsset().getTitleW()-15,
							i*drawgame.getAsset().getTitleH()-23,null);
					break;
				case 3: 
//					g.drawImage(drawgame.getAsset().getWall(),
//							j*drawgame.getAsset().getTitleW(),
//							i*drawgame.getAsset().getTitleH(),null);
					break;
				}
			}
		}
		
		p1.render(g);
//		g.drawImage(drawgame.getAsset().getP1(),0,0,null);
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
		map = new int[width][height];
		
		for(int i=0; i<height;i++) {
			for(int j=0; j<width;j++) {
				map[i][j] = Integer.parseInt(mat[i*height+j+2]);
			}
		}
	}
}
