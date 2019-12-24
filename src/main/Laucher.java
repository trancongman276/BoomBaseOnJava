package main;

public class Laucher {
	static DrawGame game;
	
	public static void main(String[] args) {
		game = new DrawGame("Uno");
		game.start();
	}
}
