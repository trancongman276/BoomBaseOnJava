package gameStates;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.DrawGame;

public class MainMenu extends State{

	private DrawGame drawgame;
	private Rectangle startbt,helpbt;
	private Boolean help=false;
	private int i;
	public MainMenu(DrawGame _drawgame) {
		drawgame = _drawgame;
		startbt = new Rectangle(1560,786,322,298);
		helpbt = new Rectangle(0,892,244,184);
	}
	
	@Override
	public void Update() {
		if(i<241) i++;
		// TODO Auto-generated method stub
		if(startbt.contains(drawgame.getMousemanager().getX(),drawgame.getMousemanager().getY())) {
			if(drawgame.getMousemanager().isClicked()) {
				State.setState(new Game(drawgame));
			}
		}
		
		if(helpbt.contains(drawgame.getMousemanager().getX(),drawgame.getMousemanager().getY())) {
			if(drawgame.getMousemanager().isClicked()) {
				help=true;
			}
		}
	}

	@Override
	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		if(i<241)
			g.drawImage(drawgame.getAsset().getIntro()[i], 0, 0, null);
		else {
			if(!help)
			g.drawImage(drawgame.getAsset().getMainmenu(), 0, 0, null);
			else
				g.drawImage(drawgame.getAsset().getHdsd(), 0, 0, null);
		}
	}

}
