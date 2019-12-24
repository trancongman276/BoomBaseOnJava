package gameStates;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.DrawGame;

public class Game extends State{
	
	private int map[][], width, height;
	private DrawGame drawgame;
	public Game(DrawGame drawgame) {
		initWorld("");
		this.drawgame = drawgame;
	}
	
	@Override
	public void Update() {
	}

	@Override
	public void Render(Graphics g) {
		for(int i=0; i<height;i++) {
			for(int j=0; j<width;j++) {
				switch(map[i][j]) {
				case 0:
					g.drawImage(drawgame.getAsset().getGrass(),i*height,j,null);
					break;
				case 1:
					g.drawImage(drawgame.getAsset().getBush(),i*height,j,null);
					break;
				case 2:
					g.drawImage(drawgame.getAsset().getWall(),i*height,j,null);
					break;
				case 3: 
					g.drawImage(drawgame.getAsset().getBox(),i*height,j,null);
					break;
				}
			}
		}
	}
	
	private void initWorld(String path) {
		StringBuilder sb = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
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
				map[i][j] = Integer.parseInt(mat[i*height+j]);
			}
		}
	}
}
