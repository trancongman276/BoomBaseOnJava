package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import gameStates.MainMenu;
import gameStates.State;
import gfx.Asset;
import gfx.Display;
import input.KeyBoardManager;
import input.MouseManager;

public class DrawGame implements Runnable{

	private String Title;
	private Display display;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private Asset asset;
	
	private boolean running;
	private MouseManager mousemanager;
	private KeyBoardManager keymanager;	
	private MainMenu menu;

	public DrawGame(String Title) {
		this.Title = Title;
		asset = new Asset();
		
		mousemanager = new MouseManager();
		keymanager = new KeyBoardManager();
		init();
	}
	
	private void init() {
		asset.init();
		display = new Display(Title);
//		menu = new MainMenu();
		menu = new MainMenu(this);
		display.getFrame().addMouseListener(mousemanager);
		display.getCanvas().addMouseListener(mousemanager);
		display.getFrame().addMouseMotionListener(mousemanager);
		display.getCanvas().addMouseMotionListener(mousemanager);
		
		display.getFrame().addKeyListener(keymanager);
		display.getCanvas().addKeyListener(keymanager);
		
		State.setState(menu);
//		System.out.println(Toolkit.getDefaultToolkit().getScreenResolution());
	}

	public void update() {
		if(State.getState()!=null) {
			State.getState().Update();
		}
//		System.out.println(mousemanager.getX()+ " " + mousemanager.getY());
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, display.getFrame().getWidth(),  display.getFrame().getHeight());
		
		if(State.getState()!=null) {
			State.getState().Render(g);
		}
		
		
		bs.show();
		g.dispose();
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		int fps = 60;
		double time_per_tick = 1000000000/fps;
		double delta = 0;
		long now;
		long last_time = System.nanoTime();
		int timer=0;
		int tick=0;
		
		while(running)
		{
			now = System.nanoTime();
			delta+=(now-last_time)/time_per_tick;
			timer+=(now-last_time);
			last_time=now;
			
			if(delta>=1) {
				update();
				render();
				tick++;
				delta--;
			}
			
			if(timer>=1000000000)
			{
				tick=0;
				timer=0;
			}
		}
		stop();
		
	}

	public synchronized void start() {
		if(!running) running = true;
		else return;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if(running) running =false;
		else return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public Asset getAsset() {
		return asset;
	}

	public MouseManager getMousemanager() {
		return mousemanager;
	}

	public KeyBoardManager getKeymanager() {
		return keymanager;
	}
	
}
