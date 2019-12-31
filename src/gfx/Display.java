package gfx;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private String title;
	private JFrame jframe;
	private Canvas canvas;
	
	
	public Display(String Title) {
		title = Title;
		show();
	}
	
	private void show() {
		jframe = new JFrame(title);
		
		jframe.setResizable(false);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setUndecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(jframe.getSize());
		canvas.setMaximumSize(new Dimension(1920,1080));
		canvas.setMinimumSize(new Dimension(1920,1080));
		
		jframe.add(canvas);
		jframe.pack();
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return jframe;
	}
	
}
