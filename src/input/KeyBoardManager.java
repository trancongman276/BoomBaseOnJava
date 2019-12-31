package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import object.Control1;

public class KeyBoardManager implements KeyListener {
	
	private Control1 move, move2;
	private boolean isD,isS,isA,isW, placeBom;
	private boolean isD2,isS2,isA2,isW2, placeBom2;
	
	
	public void setPlaceBom2(boolean placeBom2) {
		this.placeBom2 = placeBom2;
	}

	public Control1 getMove2() {
		return move2;
	}

	public boolean isPlaceBom2() {
		return placeBom2;
	}

	public boolean isPlaceBom() {
		return placeBom;
	}

	public void setPlaceBom(boolean placeBom) {
		this.placeBom = placeBom;
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
		
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			placeBom = true;
		} 
		
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) { 
			move2 = Control1.R;
			isD2 = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) { 
			move2 = Control1.D;
			isS2 = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) { 
			move2 = Control1.L;
			isA2 = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP) { 
			move2 = Control1.U;
			isW2 = true;
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			placeBom2 = true;
		} 
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//P1
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
		
		//P2
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) 
			isD2 = false;
		
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) 
			isS2 = false;
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) 
			isA2 = false;
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP) 
			isW2 = false;
		
		if(!isA2 && !isS2 && !isD2 && !isW2)
			move2 = null;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

}
