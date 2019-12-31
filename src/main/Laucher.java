package main;


public class Laucher {
	static DrawGame game;
	
	public static void main(String[] args) {
		game = new DrawGame("Boom");
		game.start();
	}
}
