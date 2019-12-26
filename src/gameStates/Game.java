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
	private final int speed = 5, bomnb = 3, timeDie = 120, bomLenght = 3;
	
	private int map[][], width, height;
	private DrawGame drawgame;
	private Player p1;
	
	public Game(DrawGame drawgame) {
		initWorld("map.txt");
		this.drawgame = drawgame;
		p1 = new Player(speed, bomnb, timeDie, bomLenght, drawgame);
	}
	
	private void checkCollide(Player p) {
		if( (p.getMove()==Control1.U) && (map[p.getY()% drawgame.getAsset().getTitleW()]
				[p.getX()% drawgame.getAsset().getTitleH()-1] == 2) ) {
			p.setCollide(true);
		}else
		
		if( (p.getMove()==Control1.D) && (map[p.getY()% drawgame.getAsset().getTitleW()]
				[p.getX()% drawgame.getAsset().getTitleH()+1] == 2) ) {
			p.setCollide(true);
		}else
		
		if( (p.getMove()==Control1.L) && (map[p.getY()% drawgame.getAsset().getTitleW()-1]
				[p.getX()% drawgame.getAsset().getTitleH()] == 2) ) {
			p.setCollide(true);
		}else
		
		if( (p.getMove()==Control1.R) && (map[p.getY()% drawgame.getAsset().getTitleW()+1]
				[p.getX()% drawgame.getAsset().getTitleH()] == 2) ) {
			p.setCollide(true);
		}else 
			p.setCollide(false);
	}
	
	private void checkCollide(Boom bom) {
		if(bom.isActive()) {
			int x =bom.getY()% drawgame.getAsset().getTitleW() ,
					y=bom.getX()% drawgame.getAsset().getTitleH();
			
			for(int i=0;i< bom.getBomLenght();i++) {
				if(map[x-1][y]==2) {
					break;
				}else
					if(map[x-1][y]==3) {
						break;
					}else
						bom.setU(bom.getU()+1);
			}
			
			for(int i=0;i< bom.getBomLenght();i++) {
				if(map[x+1][y]==2) {
					break;
				}else
					if(map[x+1][y]==3) {
						break;
					}else
						bom.setD(bom.getD()+1);
			}
			
			for(int i=0;i< bom.getBomLenght();i++) {
				if(map[x][y-1]==2) {
					break;
				}else
					if(map[x][y-1]==3) {
						break;
					}else
						bom.setL(bom.getL()+1);
			}
			
			for(int i=0;i< bom.getBomLenght();i++) {
				if(map[x][y+1]==2) {
					break;
				}else
					if(map[x][y+1]==3) {
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
			checkCollide(bom);
		}
	}

	@Override
	public void Render(Graphics g) {
		
		for(int i=0; i<height;i++) {
			for(int j=0; j<width;j++) {
				switch(map[i][j]) {
				case 0:
					g.drawImage(drawgame.getAsset().getGrass(),
							j*drawgame.getAsset().getTitleH(),
							i*drawgame.getAsset().getTitleW(),null);
					break;
				case 1:
					g.drawImage(drawgame.getAsset().getBush(),
							j*drawgame.getAsset().getTitleH(),
							i*drawgame.getAsset().getTitleW(),null);
					break;
				case 2:
					g.drawImage(drawgame.getAsset().getWall(),
							j*drawgame.getAsset().getTitleH(),
							i*drawgame.getAsset().getTitleW(),null);
					break;
				case 3: 
					g.drawImage(drawgame.getAsset().getBox(),
							j*drawgame.getAsset().getTitleH(),
							i*drawgame.getAsset().getTitleW(),null);
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
