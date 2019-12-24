package object;

import java.awt.Graphics;

import main.DrawGame;

public class Player {
	private int speed, bomnb, timeDie, bomLenght;
	private DrawGame drawgame;
	
	public Player(int _speed, int _bomnb, int _timeDie, int _bomLenght, DrawGame _drawgame) {
		speed = _speed;
		bomnb = _bomnb;
		timeDie = _timeDie;
		bomLenght = _bomLenght;
		drawgame = _drawgame;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
	}
}
