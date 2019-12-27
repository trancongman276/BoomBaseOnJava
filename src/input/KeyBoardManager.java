package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import object.Control1;

public class KeyBoardManager implements KeyListener {
	
	private Control1 move;
	private boolean isD,isS,isA,isW, placeBom;
	
	
	public boolean isPlaceBom() {
		return placeBom;
	}

	public Control1 getMove() {
		return move;
	}

	public void setMove(Control1 move) {
		this.move = move;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_D) {
			move = Control1.R;
			isD =true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			move = Control1.U;
			isW = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			move = Control1.L;
			isA = true;
		}

		if(arg0.getKeyCode() == KeyEvent.VK_S) {
			move = Control1.D;
			isS = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_D) 
			isD = false;
		
		if(arg0.getKeyCode() == KeyEvent.VK_S) 
			isS = false;
		
		if(arg0.getKeyCode() == KeyEvent.VK_A) 
			isA = false;
		
		if(arg0.getKeyCode() == KeyEvent.VK_W) 
			isW = false;
		
		if(!isA && !isS && !isD && !isW)
			move = null;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			placeBom = true;
		} else placeBom = false;
	}

}
